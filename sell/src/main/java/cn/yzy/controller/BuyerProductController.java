package cn.yzy.controller;

import cn.yzy.VO.ProductInfoVO;
import cn.yzy.VO.ProductVO;
import cn.yzy.VO.ResultVO;
import cn.yzy.dataobject.ProductCategory;
import cn.yzy.dataobject.ProductInfo;
import cn.yzy.service.CategoryService;
import cn.yzy.service.ProductService;
import cn.yzy.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResultVO list(){
//
//    //1.查询所有的上架商品
//        List<ProductInfo> productInfosList = productService.findUpAll();
//
//    //2.查询类目（一次性查询）
//        /**
//        List<Integer> categoryTypeList = new ArrayList<>();
//        //传统方法
//
//        for(ProductInfo productInfo : productInfosList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }*/
//        //精简做法（java8 lambda表达式）
//        List<Integer> categoryTypeList = productInfosList.stream().
//                                        map(e -> e.getCategoryType()).
//                                        collect(Collectors.toList());
//        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
//        //3.数据拼装
//        List<ProductVO> productVOList = new ArrayList<>();
//        for(ProductCategory productCategory : productCategoryList){
//            ProductVO productVO = new ProductVO();
//            productVO.setCategoryType(productCategory.getCategoryType());
//            productVO.setCategoryName(productCategory.getCategoryName());
//            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
//            for(ProductInfo productInfo : productInfosList){
//                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
//                    ProductInfoVO productInfoVO = new ProductInfoVO();
//                    BeanUtils.copyProperties(productInfo,productInfoVO);
//                    productInfoVOList.add(productInfoVO);
//                }
//            }
//            productVO.setProductInfoVOList(productInfoVOList);
//            productVOList.add(productVO);
//        }
//
//        ResultVO resultVO = new ResultVO();
//        resultVO.setCode(0);
//        resultVO.setMsg("成功");
//        resultVO.setData(productVOList);
//        return resultVO;

        //1.查询类目


        //2.根据类目type查询商品详情

        List<ProductInfo> productInfoList = productService.findUpAll();
        List<Integer> categoryTypeList = new ArrayList<>();
        for(ProductInfo productInfo : productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                if(productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    productInfoVO.setProductId(productInfo.getProductId());
                    productInfoVO.setProductName(productInfo.getProductName());
                    productInfoVO.setProductDescription(productInfo.getProductDescription());
                    productInfoVO.setProductIcon(productInfo.getProductIcon());
                    productInfoVO.setProductPrice(productInfo.getProductPrice());
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);

        }

        return ResultVOUtil.success(productVOList);
    }
}
