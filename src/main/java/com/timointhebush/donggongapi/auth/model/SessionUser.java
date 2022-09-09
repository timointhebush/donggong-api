package com.timointhebush.donggongapi.auth.model;

import com.timointhebush.donggongapi.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class SessionUser implements Serializable{
    private String email;
    private String name;
    private String picture;

    public SessionUser(Account account) {
        this.name = account.getName();
        this.email = account.getEmail();
        this.picture = account.getPicture();
    }
}
