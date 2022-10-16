package com.mumu.novel.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.resp.ImgVerifyCodeRespDto;
import com.mumu.novel.manager.cache.VerifyCodeManager;
import com.mumu.novel.service.ResourceService;

import lombok.RequiredArgsConstructor;

/**
 * 资源（图片/视频/文档）相关服务类
 *
 * @author mumu
 * @date 2022/10/10
 */
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final VerifyCodeManager verifyCodeManager;

    /**
     * 获取验证码图片
     */
    @Override
    public RestResp<ImgVerifyCodeRespDto> getImgVerifyCode() throws IOException {
        String sessionId = IdWorker.get32UUID();
        return RestResp.ok(ImgVerifyCodeRespDto.builder()
            .sessionId(sessionId)
            .img(verifyCodeManager.genVerifyCodeImg(sessionId))
            .build());
    }

}
