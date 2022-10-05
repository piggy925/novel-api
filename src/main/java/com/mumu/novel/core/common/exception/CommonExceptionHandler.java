package com.mumu.novel.core.common.exception;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mumu.novel.core.common.constant.ErrorCodeEnum;
import com.mumu.novel.core.common.resp.RestResp;

import lombok.extern.slf4j.Slf4j;

/**
 * 通用异常处理类
 *
 * @author mumu
 * @date 2022/10/05
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    /**
     * 数据校验异常处理
     */
    @ExceptionHandler(BindException.class)
    public RestResp<Void> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        return RestResp.fail(ErrorCodeEnum.USER_REQUEST_PARAM_ERROR);
    }

    /**
     * 业务异常处理
     */
    @ExceptionHandler(BusinessException.class)
    public RestResp<Void> handleBindException(BusinessException e) {
        log.error(e.getMessage(), e);
        return RestResp.fail(e.getErrorCodeEnum());
    }

    /**
     * 系统异常处理
     */
    @ExceptionHandler(Exception.class)
    public RestResp<Void> handleBindException(Exception e) {
        log.error(e.getMessage(), e);
        return RestResp.error();
    }

}
