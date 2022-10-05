package com.timointhebush.donggongapi.performance.model;

import com.timointhebush.donggongapi.account.model.Account;
import com.timointhebush.donggongapi.file.model.FileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@AllArgsConstructor
@Getter
@ToString
public class PerformanceCreateInput {
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

    private PerformanceFileCreateInput posterFileInput;

    private PerformanceFileCreateInput infoFileInput;

    public static PerformanceCreateInput of(
            PerformanceCreateRequest performanceCreateRequest,
            MultipartFile posterFile,
            MultipartFile informationFile
    ) {

        return new PerformanceCreateInput(
                performanceCreateRequest.getTitle(),
                performanceCreateRequest.getOrganizationName(),
                performanceCreateRequest.getContact(),
                performanceCreateRequest.getLocation(),
                performanceCreateRequest.getTotalTicket(),
                performanceCreateRequest.getRemainingTicket(),
                performanceCreateRequest.getTicketPrice(),
                performanceCreateRequest.getBankAccountInfo(),
                performanceCreateRequest.getDescription(),
                performanceCreateRequest.getStartDateTime(),
                performanceCreateRequest.getEndDateTime(),
                PerformanceFileCreateInput.of(posterFile, FileType.PERFORMANCE_POSTER),
                PerformanceFileCreateInput.of(informationFile, FileType.PERFORMANCE_INFORMATION)
        );
    }

    public Performance toPerformance(Account host) {
        return Performance.builder()
                .host(host)
                .title(title)
                .organizationName(organizationName)
                .contact(contact)
                .location(location)
                .totalTicket(totalTicket)
                .remainingTicket(remainingTicket)
                .ticketPrice(ticketPrice)
                .bankAccountInfo(bankAccountInfo)
                .description(description)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .build();
    }
}
