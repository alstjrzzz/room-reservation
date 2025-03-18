package com.alstjrzzz.srr.dto.reservation;

import com.alstjrzzz.srr.domain.Reservation;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReservationResponseDTO {

    private String nickname;
    private Long reservationId;
    private String purpose;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static ReservationResponseDTO from(Reservation reservation) {

        return ReservationResponseDTO.builder()
                .nickname(reservation.getNickname())
                .reservationId(reservation.getReservationId())
                .purpose(reservation.getPurpose())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();
    }
}
