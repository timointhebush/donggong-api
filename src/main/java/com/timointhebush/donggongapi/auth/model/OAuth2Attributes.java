package com.timointhebush.donggongapi.auth.model;

import com.timointhebush.donggongapi.auth.exception.UnknownProviderException;
import com.timointhebush.donggongapi.model.Account;
import com.timointhebush.donggongapi.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class OAuth2Attributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    public static OAuth2Attributes of(String provider, String attributeKey, Map<String, Object> attributes) {
        if (provider.equals("google")) {
            return ofGoogle(attributeKey, attributes);
        }
        throw new UnknownProviderException("알 수 없는 oAuth 제공자입니다.");
    }

    private static OAuth2Attributes ofGoogle(String attributeKey, Map<String, Object> attributes) {
        return new OAuth2Attributes(
                attributes,
                attributeKey,
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                (String) attributes.get("picture")
        );
    }

    public Account toAccount() {
        return Account.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.USER)
                .build();
    }
}
