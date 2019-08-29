package com.Jlink.vo;

import com.Jlink.entity.User;
import lombok.Data;

@Data
public class LoginObject {
    public LoginObject(User user, String token){
        this.user = user;
        this.token = token;
    }
    private User user;
    private String token;
}
