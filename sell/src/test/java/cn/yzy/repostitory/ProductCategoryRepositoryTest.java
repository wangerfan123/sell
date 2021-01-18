package cn.yzy.repostitory;

import cn.yzy.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;
    
    @Test
    public void findOneTest(){

        Optional<ProductCategory> productCategory = repository.findById(1);//查询
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional //在这里的事务表示，不论成功都要回滚，专用于测试
    public void saveTest(){

        ProductCategory productCategory = new ProductCategory("男生最爱",4);
        ProductCategory save = repository.save(productCategory); //插入
        Assert.assertNotNull(save);
    }
    @Test
    public void findByCategoryTypeIn(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());//结果等于0则失败
    }
}