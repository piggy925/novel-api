package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 小说类别
 *
 * @author mumu
 * @date 2022/10/07
 */

@TableName("book_category")
@Data
public class BookCategory implements Serializable {
    private static final long serialVersionUID = 7283908097613685626L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 作品方向;0-男频 1-女频
     */
    private Byte workDirection;

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