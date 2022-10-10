package com.mumu.novel.service;

import java.io.IOException;

import com.mumu.novel.core.common.resp.RestResp;
import com.mumu.novel.dto.resp.ImgVerifyCodeRespDto;

/**
 * 资源（图片/视频/文档）相关服务类
 *
 * @author mumu
 * @date 2022/10/10
 */
public interface ResourceService {

    /**
     * 获取验证码图片
     */
    RestResp<ImgVerifyCodeRespDto> getImgVerifyCode() throws IOException;

}
