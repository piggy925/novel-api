package com.mumu.novel.core.auth;

import lombok.experimental.UtilityClass;

/**
 * 用户信息持有类
 * 
 * @author mumu
 * @date 2022/10/16
 */
@UtilityClass
public class UserHolder {

    /**
     * 当前线程用户id
     */
    private static final ThreadLocal<Long> userIdTL = new ThreadLocal<>();

    public void setUserId(Long id) {
        userIdTL.set(id);
    }

    public Long getUserId() {
        return userIdTL.get();
    }

    public void clearAll() {
        userIdTL.remove();
    }
}
