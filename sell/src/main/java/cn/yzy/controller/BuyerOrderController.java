package cn.yzy.controller;

import cn.yzy.VO.ResultVO;
import cn.yzy.converter.OrderForm2OrderDTOConverter;
import cn.yzy.dto.OrderDTO;
import cn.yzy.enums.ResultEnum;
import cn.yzy.exception.SellException;
import cn.yzy.form.OrderFrom;
import cn.yzy.service.OrderService;
import cn.yzy.utils.ResultVOUtil;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    //创建订单
    //@Valid : 在实体的属性上添加校验规则，在API接收数据时添加@Valid注解，这时你的实体将会开启一个校验的功能。
    //Spring验证的错误返回  ·····>  BindingResult 
    //@Valid 和 BindingResult 是一一对应的，如果有多个@Valid，那么每个@Valid后面跟着的BindingResult就是这个@Valid的验证结果，顺序不能乱
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderFrom orderFrom,
                                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确,orderForm={}",orderFrom);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderFrom);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单]购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String ,String > map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVOUtil.success(map);

    }
    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)) {
            log.error("[查询订单列表] openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = PageRequest.of(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
//        List<OrderDTO> content = orderDTOPage.getContent();
        //List<T> getContent();     //将所有数据返回为List
        return ResultVOUtil.success(orderDTOPage.getContent());

    }
    //订单详情

    //取消订单
}
