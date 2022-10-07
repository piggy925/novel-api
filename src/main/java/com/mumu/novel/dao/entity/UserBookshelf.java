package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 用户书架
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("user_bookshelf")
@Data
public class UserBookshelf implements Serializable {
    private static final long serialVersionUID = 6141938908973292301L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 小说ID
     */
    private Long bookId;

    /**
     * 上一次阅读的章节内容表ID
     */
    private Long preContentId;

    /**
     * 创建时间;
     */
    private Date createTime;

    /**
     * 更新时间;
     */
    private Date updateTime;
}