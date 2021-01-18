package cn.yzy.repostitory;

import cn.yzy.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    private final String OPENID = "110110";
    @Test
    public void saveTest(){
        OrderMaster master = new OrderMaster();
        master.setOrderId("1234567");
        master.setBuyerName("师兄");
        master.setBuyerPhone("12345678912");
        master.setBuyerAddress("慕课网");
        master.setBuyerOpenid(OPENID);
        master.setOrderAmount(new BigDecimal(2.5));
        OrderMaster save = orderMasterRepository.save(master);
        Assert.assertNotNull(save);
    }
    @Test
    void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(1,3);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0,result.getTotalElements());
    }
}