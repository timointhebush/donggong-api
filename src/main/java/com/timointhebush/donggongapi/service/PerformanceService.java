package com.timointhebush.donggongapi.service;

import com.timointhebush.donggongapi.model.Account;
import com.timointhebush.donggongapi.model.Performance;
import com.timointhebush.donggongapi.model.PerformanceFile;
import com.timointhebush.donggongapi.model.input.PerformanceCreateInput;
import com.timointhebush.donggongapi.model.response.PerformanceCreateResponse;
import com.timointhebush.donggongapi.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final PerformanceFileService performanceFileService;

    @Transactional
    public PerformanceCreateResponse create(PerformanceCreateInput input, Account host) {
        log.info("PFMC:CRTE:____:: PerformanceService 새로운 공연 생성 - {}", input);
        Performance performance = performanceRepository.save(input.toPerformance(host));
        PerformanceFile poster = performanceFileService.create(input.getPosterFileInput(), performance);
        PerformanceFile information = performanceFileService.create(input.getInfoFileInput(), performance);
        return PerformanceCreateResponse.of(performance, poster, information);
    }
}
