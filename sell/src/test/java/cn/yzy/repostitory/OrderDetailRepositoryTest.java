package cn.yzy.repostitory;

import cn.yzy.dataobject.OrderDetail;
import cn.yzy.dataobject.OrderMaster;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456710");
        orderDetail.setOrderId("1111111");
        orderDetail.setProductIcon("http://aaa.jpg");
        orderDetail.setProductId("1111222");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(1.1));
        orderDetail.setProductQuantity(3);
        OrderDetail save = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(save);
    }
    @Test
    void findByOrderId() {
        List<OrderDetail> byOrderId = orderDetailRepository.findByOrderId("1111111");
        Assert.assertNotEquals(0,byOrderId.size());
    }
}