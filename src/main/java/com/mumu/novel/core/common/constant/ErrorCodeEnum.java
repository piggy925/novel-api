package com.mumu.novel.core.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举类
 *
 * @author mumu
 * @date 2022/10/05
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
    /**
     * 请求成功
     */
    OK("000000", "请求成功"),

    /**
     * 一级错误码 - 用户端错误
     */
    USER_ERROR("A0001", "用户端错误"),

    /**
     * 二级错误码 - 用户注册错误
     */
    USER_REGISTER_ERROR("A0100", "用户注册错误"),

    /**
     * 二级错误码 - 用户未同意隐私协议
     */
    USER_NO_AGREE_PRIVATE_ERROR("A0101", "用户未同意隐私协议"),

    /**
     * 二级错误码 - 注册国家或地区受限
     */
    USER_REGISTER_AREA_LIMIT_ERROR("A0102", "注册国家或地区受限"),

    /**
     * 二级错误码 - 用户请求参数错误
     */
    USER_REQUEST_PARAM_ERROR("A0400", "用户请求参数错误"),

    /**
     * 一级错误码 - 系统错误
     */
    SYSTEM_ERROR("B0001", "系统执行出错"),

    /**
     * 二级错误码 - 系统执行超时
     */
    SYSTEM_TIMEOUT_ERROR("B0100", "系统执行超时"),

    /**
     * 一级错误码 - 调用第三方服务出错
     */
    THIRD_SERVICE_ERROR("C0001", "调用第三方服务出错"),

    /**
     * 一级错误码 - 中间件服务出错
     */
    MIDDLEWARE_SERVICE_ERROR("C0100", "中间件服务出错");

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态描述
     */
    private String desc;
}
