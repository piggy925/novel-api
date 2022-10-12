package com.mumu.novel.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 响应 dto - 用户登录
 *
 * @author mumu
 * @date 2022/10/12
 */
@Data
@Builder
public class UserLoginRespDto {

    /**
     * 用户 id
     */
    @Schema(description = "用户ID")
    private Long uid;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String nickName;

    /**
     * token
     */
    @Schema(description = "用户token")
    private String token;

}
