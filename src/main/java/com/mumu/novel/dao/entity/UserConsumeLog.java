package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 用户消费记录
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("user_consume_log")
@Data
public class UserConsumeLog implements Serializable {
    private static final long serialVersionUID = 4551372063781144278L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 消费用户ID
     */
    private Long userId;

    /**
     * 消费使用的金额;单位：屋币
     */
    private Integer amount;

    /**
     * 消费商品类型;0-小说VIP章节
     */
    private Byte productType;

    /**
     * 消费的的商品ID;例如：章节ID
     */
    private Long productId;

    /**
     * 消费的的商品名;例如：章节名
     */
    private String producName;

    /**
     * 消费的的商品值;例如：1
     */
    private Integer producValue;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}