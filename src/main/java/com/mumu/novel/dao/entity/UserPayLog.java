package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 用户充值记录
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("user_pay_log")
@Data
public class UserPayLog implements Serializable {
    private static final long serialVersionUID = 6061609479341807864L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 充值用户ID
     */
    private Long userId;

    /**
     * 充值方式;0-支付宝 1-微信
     */
    private Byte payChannel;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 充值金额;单位：分
     */
    private Integer amount;

    /**
     * 充值商品类型;0-屋币 1-包年VIP
     */
    private Byte productType;

    /**
     * 充值商品ID
     */
    private Long productId;

    /**
     * 充值商品名;示例值：屋币
     */
    private String productName;

    /**
     * 充值商品值;示例值：255
     */
    private Integer productValue;

    /**
     * 充值时间
     */
    private Date payTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}