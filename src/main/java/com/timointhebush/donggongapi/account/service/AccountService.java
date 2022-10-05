package com.timointhebush.donggongapi.account.service;

import com.timointhebush.donggongapi.account.exception.AccountNotFoundException;
import com.timointhebush.donggongapi.account.model.Account;
import com.timointhebush.donggongapi.account.model.SessionUser;
import com.timointhebush.donggongapi.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Account findBySessionUser(SessionUser user) {
        log.info("ACCT:READ:USER:: 세션 유저 정보로 계정 조회 - {}", user);
        return accountRepository.findById(user.getId())
                .orElseThrow(() -> new AccountNotFoundException("계정을 찾을 수 없습니다. : " + user));
    }
}
