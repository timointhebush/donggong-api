package com.timointhebush.donggongapi.auth.service;

import com.timointhebush.donggongapi.auth.model.OAuth2Attributes;
import com.timointhebush.donggongapi.account.model.SessionUser;
import com.timointhebush.donggongapi.account.model.Account;
import com.timointhebush.donggongapi.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final AccountService accountService;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuth2Attributes oAuth2Attribute = OAuth2Attributes.of(
                registrationId, userNameAttributeName, oAuth2User.getAttributes()
        );
        Account account = saveOrUpdate(oAuth2Attribute);
        httpSession.setAttribute("user", new SessionUser(account));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(account.getRoleKey())),
                oAuth2Attribute.getAttributes(),
                oAuth2Attribute.getNameAttributeKey()
        );
    }

    private Account saveOrUpdate(OAuth2Attributes attributes) {
        Account account = accountService.findByEmail(attributes.getEmail())
                .map(entity -> entity.updateNameAndPicture(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toAccount());
        return accountService.save(account);
    }
}
