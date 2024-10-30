package com.example.lanshansong.Entity.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author yourkin666
 * @date 2024/10/29/12:08
 * @description
 */
@Data
public class RegisterVO {

    @Email(message = "邮箱不可为空")
    private String email;
    @NotBlank(message = "密码不可为空")
    private String password;
    @NotBlank(message = "验证码不可为空")
    private String code;
    @NotBlank(message = "uuid不可为空")
    private String uuid;
    @NotBlank(message = "用户名不可为空")
    private String nickName;
}
