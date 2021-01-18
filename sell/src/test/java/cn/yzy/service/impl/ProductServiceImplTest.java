package cn.yzy.service.impl;


import cn.yzy.dataobject.ProductInfo;
import cn.yzy.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Test
    public void findOne() {
        ProductInfo one = productService.findOne("123456");
        System.out.println(one);
        Assert.assertEquals("123456",one.getProductId());
    }

    @Test
    void findUpAll() {
        List<ProductInfo> upAll = productService.findUpAll();
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    void findAll() throws Exception{
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> all = productService.findAll(request);
//        System.out.println(all.getTotalElements());
        Assert.assertNotEquals(0,all.getTotalElements());
    }

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://.jpg");
        productInfo.setProductStatus(ProductStatusEnum.Down.getCode());
        productInfo.setCategoryType(2);
        ProductInfo save = productService.save(productInfo);
        Assert.assertNotNull(save);
    }
}