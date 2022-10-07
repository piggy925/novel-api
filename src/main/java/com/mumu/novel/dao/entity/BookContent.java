package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 小说内容
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("book_content")
@Data
public class BookContent implements Serializable {
    private static final long serialVersionUID = 2699053252850069030L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 章节ID
     */
    private Long chapterId;

    /**
     * 小说章节内容
     */
    private String content;

    private Date createTime;

    private Date updateTime;
}