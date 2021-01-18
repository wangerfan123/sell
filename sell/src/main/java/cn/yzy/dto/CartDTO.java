package cn.yzy.dto;

import lombok.Data;

/**
 * 购物车
 * DTO是web层与服务层之间传递数据的对象，
 */
@Data
public class CartDTO {
    //商品id
    private String productId;
    //商品数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
