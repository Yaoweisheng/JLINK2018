package com.Jlink.vo;

import lombok.Data;

@Data
public class UserChangePasswordObject {
    private Long userId;
    private String password;
    private String newPassword;
}
