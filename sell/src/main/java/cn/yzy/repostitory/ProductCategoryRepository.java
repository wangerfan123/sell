package cn.yzy.repostitory;

import cn.yzy.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>, JpaSpecificationExecutor<ProductCategory> {

    //根据type组成的list进行查询
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
