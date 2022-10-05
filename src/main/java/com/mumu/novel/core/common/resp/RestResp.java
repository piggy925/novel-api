package com.mumu.novel.core.common.resp;

import java.util.Objects;

import com.mumu.novel.core.common.constant.ErrorCodeEnum;

import lombok.Getter;

/**
 * @author mumu
 * @date 2022/10/05
 */
@Getter
public class RestResp<T> {
    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    private RestResp() {
        this.code = ErrorCodeEnum.OK.getCode();
        this.message = ErrorCodeEnum.OK.getDesc();
    }

    private RestResp(ErrorCodeEnum code) {
        this.code = code.getCode();
        this.message = code.getDesc();
    }

    private RestResp(T data) {
        this.data = data;
    }

    /**
     * 处理成功 - 无数据返回
     */
    public static RestResp<Void> ok() {
        return new RestResp<>();
    }

    /**
     * 处理成功 - 有数据返回
     */
    public static <T> RestResp<T> ok(T data) {
        return new RestResp<>(data);
    }

    /**
     * 处理失败 - 通用
     */
    public static RestResp<Void> fail(ErrorCodeEnum code) {
        return new RestResp<>(code);
    }

    /**
     * 处理失败 - 系统错误
     */
    public static RestResp<Void> error() {
        return new RestResp<>(ErrorCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 判断请求是否成功
     */
    public boolean isOk() {
        return Objects.equals(this.code, ErrorCodeEnum.OK.getCode());
    }
}
