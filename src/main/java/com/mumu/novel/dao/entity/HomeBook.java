package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 小说推荐
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("home_book")
@Data
public class HomeBook implements Serializable {
    private static final long serialVersionUID = -2017011959604841182L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 推荐类型;0-轮播图 1-顶部栏 2-本周强推 3-热门推荐 4-精品推荐
     */
    private Byte type;

    /**
     * 推荐排序
     */
    private Byte sort;

    /**
     * 推荐小说ID
     */
    private Long bookId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}