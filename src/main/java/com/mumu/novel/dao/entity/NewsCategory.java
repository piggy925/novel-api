package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 新闻类别
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("news_category")
@Data
public class NewsCategory implements Serializable {
    private static final long serialVersionUID = 7494537802037764645L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类别名
     */
    private String name;

    /**
     * 排序
     */
    private Byte sort;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}