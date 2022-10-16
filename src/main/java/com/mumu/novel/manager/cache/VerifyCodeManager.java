package com.mumu.novel.manager.cache;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.mumu.novel.core.common.util.ImgVerifyCodeUtils;
import com.mumu.novel.core.constant.CacheConsts;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 验证码管理
 *
 * @author mumu
 * @date 2022/10/10
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class VerifyCodeManager {

    private final StringRedisTemplate stringRedisTemplate;

    public String genVerifyCodeImg(String sessionId) throws IOException {
        // 验证码位数
        int verifyCodeLength = 4;
        String verifyCode = ImgVerifyCodeUtils.genRandomVerifyCode(verifyCodeLength);
        String img = ImgVerifyCodeUtils.genVerifyCodeImg(verifyCode);
        stringRedisTemplate.opsForValue().set(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId, verifyCode,
            Duration.ofMinutes(5));
        return img;
    }

    /**
     * 校验验证码是否有效
     */
    public boolean isVerifyCodeValid(String sessionId, String verifyCode) {
        return Objects.equals(stringRedisTemplate.opsForValue().get(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId), verifyCode);
    }

    /**
     * 删除缓存中的验证码
     */
    public void removeImgVerifyCode(String sessionId) {
        stringRedisTemplate.delete(CacheConsts.IMG_VERIFY_CODE_CACHE_KEY + sessionId);
    }

}
