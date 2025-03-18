package com.alstjrzzz.srr.dto.reservation;

import lombok.Builder;

import java.util.List;

@Builder
public class GetReservationsResponseDTO {

    private List<ReservationResponseDTO> reservationList;
}
