package com.timointhebush.donggongapi.model.response;

import com.timointhebush.donggongapi.model.Performance;
import com.timointhebush.donggongapi.model.PerformanceFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceCreateResponse {
    private PerformanceResponse performance;
    private PerformanceFileResponse posterFile;
    private PerformanceFileResponse informationFile;

    public static PerformanceCreateResponse of(Performance performance, PerformanceFile posterFile, PerformanceFile informationFile) {
        return PerformanceCreateResponse.builder()
                .performance(PerformanceResponse.from(performance))
                .posterFile(PerformanceFileResponse.from(posterFile))
                .informationFile(PerformanceFileResponse.from(informationFile))
                .build();
    }
}
