package com.timointhebush.donggongapi.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;

    public static AccountResponse from(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .email(account.getEmail())
                .picture(account.getPicture())
                .role(account.getRole())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }
}