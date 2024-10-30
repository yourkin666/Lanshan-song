package com.example.lanshansong.Entity.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author yourkin666
 * @date 2024/10/29/12:09
 * @description
 */
@Data
public class FindPWVO {

    @Email(message = "邮箱格式不正确")
    String email;

    @NotNull(message = "code不能为空")
    Integer code;

    @NotBlank(message = "新密码不能为空")
    String newPassword;
}