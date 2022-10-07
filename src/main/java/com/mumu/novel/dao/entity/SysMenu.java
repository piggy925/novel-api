package com.mumu.novel.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 系统菜单
 *
 * @author mumu
 * @date 2022/10/07
 */
@TableName("sys_menu")
@Data
public class SysMenu implements Serializable {
    private static final long serialVersionUID = -1401861624079527214L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单ID
     * 一级菜单为0
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 类型
     * 0-目录   1-菜单
     */
    private Byte type;

    /**
     * 菜单图标
     */
    private String icon;

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