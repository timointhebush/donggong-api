package com.timointhebush.donggongapi.controller;

import com.timointhebush.donggongapi.auth.annotation.LoginUser;
import com.timointhebush.donggongapi.model.Account;
import com.timointhebush.donggongapi.model.ResponseData;
import com.timointhebush.donggongapi.model.SessionUser;
import com.timointhebush.donggongapi.model.input.PerformanceCreateInput;
import com.timointhebush.donggongapi.model.request.PerformanceCreateRequest;
import com.timointhebush.donggongapi.model.response.PerformanceCreateResponse;
import com.timointhebush.donggongapi.service.AccountService;
import com.timointhebush.donggongapi.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/performance")
@Slf4j
public class PerformanceController {
    private final PerformanceService performanceService;
    private final AccountService accountService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseData<PerformanceCreateResponse>> create(
            PerformanceCreateRequest performanceCreateRequest,
            MultipartFile posterFile,
            MultipartFile informationFile,
            @LoginUser SessionUser sessionUser
    ) {
        log.info("PFMC:CRTE:____:: PerformanceController 새로운 공연 등록 - {}", performanceCreateRequest);
        Account account = accountService.findBySessionUser(sessionUser);
        PerformanceCreateInput input = PerformanceCreateInput.of(performanceCreateRequest, posterFile, informationFile);
        PerformanceCreateResponse response = performanceService.create(input, account);
        return new ResponseEntity<>(new ResponseData<>(response), HttpStatus.OK);
    }
}
