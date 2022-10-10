package com.mumu.novel.dto.req;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 请求 dto - 用户注册
 *
 * @author mumu
 * @date 2022/10/10
 */
@Data
public class UserRegisterReqDto {

    /**
     * 电话
     */
    @NotBlank(message = "手机号不能为空！")
    @Schema(description = "手机号", required = true)
    @Pattern(regexp = "^1[3|4|5|6|7|8|9][0-9]{9}$", message = "手机号格式不正确！")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空！")
    @Schema(description = "密码", required = true)

    private String password;

    /**
     * 验证代码
     */
    @NotBlank(message = "验证码不能为空！")
    @Schema(description = "验证码", required = true)
    @Pattern(regexp = "^\\d{4}$", message = "验证码格式不正确！")
    private String velCode;

    /**
     * 会话标识
     * 用于标识图形验证码属于哪个会话
     */
    @NotBlank
    @Length(min = 32,max = 32)
    @Schema(description = "sessionId", required = true)
    private String sessionId;

}
