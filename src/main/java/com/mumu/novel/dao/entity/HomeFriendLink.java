package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 友情链接
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("home_friend_link")
@Data
public class HomeFriendLink implements Serializable {
    private static final long serialVersionUID = 1768850118001019545L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 链接名
     */
    private String linkName;

    /**
     * 链接url
     */
    private String linkUrl;

    /**
     * 排序号
     */
    private Byte sort;

    /**
     * 是否开启;0-不开启 1-开启
     */
    private Byte isOpen;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}