package com.timointhebush.donggongapi.model.response;

import com.timointhebush.donggongapi.model.Performance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceResponse {
    private Long id;
    private AccountResponse host;
    private String title;
    private String organizationName;
    private String contact;
    private String location;
    private Long totalTicket;
    private Long remainingTicket;
    private Long ticketPrice;
    private String bankAccountInfo;
    private String description;
    private Instant startDateTime;
    private Instant endDateTime;
    private Instant createdAt;
    private Instant updatedAt;

    public static PerformanceResponse from(Performance performance) {
        return PerformanceResponse.builder()
                .id(performance.getId())
                .host(AccountResponse.from(performance.getHost()))
                .title(performance.getTitle())
                .organizationName(performance.getOrganizationName())
                .contact(performance.getContact())
                .location(performance.getLocation())
                .totalTicket(performance.getTotalTicket())
                .remainingTicket(performance.getRemainingTicket())
                .ticketPrice(performance.getTicketPrice())
                .bankAccountInfo(performance.getBankAccountInfo())
                .description(performance.getDescription())
                .startDateTime(performance.getStartDateTime())
                .endDateTime(performance.getEndDateTime())
                .createdAt(performance.getCreatedAt())
                .updatedAt(performance.getUpdatedAt())
                .build();
    }
}
