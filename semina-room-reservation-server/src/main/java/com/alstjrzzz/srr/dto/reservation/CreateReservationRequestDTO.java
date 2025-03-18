package com.alstjrzzz.srr.dto.reservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateReservationRequestDTO {

    @NotBlank
    private String nickname;

    @NotBlank
    private String studentName;

    @NotNull
    private Integer studentId;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String purpose;

    @NotBlank
    private LocalDateTime startTime;

    @NotBlank
    private LocalDateTime endTime;
}
