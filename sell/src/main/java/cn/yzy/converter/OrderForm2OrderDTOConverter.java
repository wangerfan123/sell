package cn.yzy.converter;

import cn.yzy.dataobject.OrderDetail;
import cn.yzy.dto.OrderDTO;
import cn.yzy.enums.ResultEnum;
import cn.yzy.exception.SellException;
import cn.yzy.form.OrderFrom;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderFrom orderFrom){
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderFrom.getName());
        orderDTO.setBuyerPhone(orderFrom.getPhone());
        orderDTO.setBuyerAddress(orderFrom.getAddress());
        orderDTO.setBuyerOpenid(orderFrom.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            ////TypeToken的构造方法是protected修饰的,所以上面才会写成new TypeToken<List<String>>() {}.getType()
            // 而不是 new TypeToken<List<String>>().getType()
            orderDetailList = gson.fromJson(orderFrom.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                        //匿名内部类  使用匿名内部类还有个前提条件：必须继承一个父类或实现一个接口
                        //这里只是继承了TypeToken类 由于protected可以被子类访问,所以通过子类来访问getType()方法
                    }
                    .getType());
        }catch (Exception e){
            log.error("[对象转换]错误,string={}",orderFrom.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;

    }
}
