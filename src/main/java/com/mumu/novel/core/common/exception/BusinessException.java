package com.mumu.novel.core.common.exception;

import com.mumu.novel.core.common.constant.ErrorCodeEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常类
 *
 * @author mumu
 * @date 2022/10/05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private final ErrorCodeEnum errorCodeEnum;

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        // 不调用父类 Throwable 的 fillInStackTrace() 方法生成栈追踪信息，提高应用性能
        super(errorCodeEnum.getDesc(), null, false, false);
        this.errorCodeEnum = errorCodeEnum;
    }

}
