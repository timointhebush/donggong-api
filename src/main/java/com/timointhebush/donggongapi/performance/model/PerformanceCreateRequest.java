package com.timointhebush.donggongapi.performance.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PerformanceCreateRequest {
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
}
