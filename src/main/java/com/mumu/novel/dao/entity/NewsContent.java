package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 新闻内容
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("news_content")
@Data
public class NewsContent implements Serializable {
    private static final long serialVersionUID = -3042570027172892618L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 新闻ID
     */
    private Long newsId;

    /**
     * 新闻内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}