package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 作家邀请码
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("author_code")
@Data
public class AuthorCode implements Serializable {
    private static final long serialVersionUID = -8093197835242039565L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 有效时间
     */
    private Date validityTime;

    /**
     * 是否使用过;0-未使用 1-使用过
     */
    private Byte isUsed;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}