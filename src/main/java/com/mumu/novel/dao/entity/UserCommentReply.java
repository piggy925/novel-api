package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 用户评论回复
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("user_comment_reply")
@Data
public class UserCommentReply implements Serializable {
    private static final long serialVersionUID = -8194183037686345718L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 评论ID
     */
    private Long commentId;

    /**
     * 回复用户ID
     */
    private Long userId;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 审核状态;0-待审核 1-审核通过 2-审核不通过
     */
    private Byte auditStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}