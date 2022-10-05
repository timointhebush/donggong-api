package com.timointhebush.donggongapi.performance.model;

import com.timointhebush.donggongapi.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Account host;

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

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
