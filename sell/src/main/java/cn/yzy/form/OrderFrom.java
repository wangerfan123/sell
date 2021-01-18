package cn.yzy.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class OrderFrom {
    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名毕填")
    private String name;
    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号毕填")
    private String phone;
    /**
     * 买家地址
     */
    @NotEmpty(message = "地址毕填")
    private String address;
    /**
     * 买家微信openid
     */
    @NotEmpty(message = "openid毕填")
    private String openid;
    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
