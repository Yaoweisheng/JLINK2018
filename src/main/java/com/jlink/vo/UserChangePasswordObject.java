package com.jlink.vo;

import lombok.Data;

@Data
public class UserChangePasswordObject {
    private Integer userId;
    private String password;
    private String newPassword;
}
