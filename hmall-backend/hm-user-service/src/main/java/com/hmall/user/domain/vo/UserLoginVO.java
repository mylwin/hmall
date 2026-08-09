package com.hmall.user.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户登录结果
 */
@Data
@Schema(description = "用户登录结果")
public class UserLoginVO {
    @Schema(description = "token")
    private String token;
    @Schema(description = "用户id")
    private Long userId;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "余额")
    private Integer balance;
}
