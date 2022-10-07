package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 用户反馈
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("user_feedback")
@Data
public class UserFeedback implements Serializable {
    private static final long serialVersionUID = -2577193963120642166L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 反馈用户id
     */
    private Long userId;

    /**
     * 反馈内容
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