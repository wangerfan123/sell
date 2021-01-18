package cn.yzy.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import java.util.Date;


/**
 * 类目
 * 表名product_category
 */
@Entity
//@DynamicUpdate //动态更新时间
@Data //配合lombok插件，自动生成getter、setter和tostring方法
public class ProductCategory {
    //类目id
    @Id  //标注用于声明一个实体类的属性映射为数据库的主键列
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增类型
    private Integer categoryId;
    //类目名字
    private String categoryName;
    //类名编号
    private Integer categoryType;

    //无惨构造函数
    public ProductCategory(){

    }
    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
