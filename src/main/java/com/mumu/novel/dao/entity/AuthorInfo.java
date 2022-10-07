package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 作者信息
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("author_info")
@Data
public class AuthorInfo implements Serializable {
    private static final long serialVersionUID = 3737062239354833232L;

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
     * 邀请码
     */
    private String inviteCode;

    /**
     * 笔名
     */
    private String penName;

    /**
     * 手机号码
     */
    private String telPhone;

    /**
     * QQ或微信账号
     */
    private String chatAccount;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 作品方向;0-男频 1-女频
     */
    private Byte workDirection;

    /**
     * 0：正常;1-封禁
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}