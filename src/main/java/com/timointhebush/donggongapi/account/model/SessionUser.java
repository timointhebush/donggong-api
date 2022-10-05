package com.timointhebush.donggongapi.account.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
public class SessionUser implements Serializable{
    private Long id;
    private String email;
    private String name;
    private String picture;
    private Role role;
    private String createdAt;
    private String updatedAt;

    public SessionUser(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.email = account.getEmail();
        this.picture = account.getPicture();
        this.role = account.getRole();
        this.createdAt = account.getCreatedAt().toString();
        this.updatedAt = account.getUpdatedAt().toString();
    }
}
