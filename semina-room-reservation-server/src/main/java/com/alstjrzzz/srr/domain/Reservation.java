package com.alstjrzzz.srr.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Reservation {

    private Long reservationId;
    private String nickname;
    private String studentName;
    private Integer studentId;
    private String phoneNumber;
    private String purpose;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
