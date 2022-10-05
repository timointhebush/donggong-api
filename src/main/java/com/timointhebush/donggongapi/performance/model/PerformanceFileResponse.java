package com.timointhebush.donggongapi.performance.model;

import com.timointhebush.donggongapi.file.model.FileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceFileResponse {
    private Long id;
    private Long performanceId;
    private String key;
    private String name;
    private FileType type;
    private Long fileSize;
    private String filePath;
    private Instant createdAt;
    private Instant updatedAt;

    public static PerformanceFileResponse from(PerformanceFile performanceFile) {
        return PerformanceFileResponse.builder()
                .id(performanceFile.getId())
                .performanceId(performanceFile.getPerformance().getId())
                .key(performanceFile.getKey())
                .name(performanceFile.getName())
                .type(performanceFile.getType())
                .fileSize(performanceFile.getFileSize())
                .filePath(performanceFile.getFilePath())
                .createdAt(performanceFile.getCreatedAt())
                .updatedAt(performanceFile.getUpdatedAt())
                .build();
    }
}
