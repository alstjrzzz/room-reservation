package com.alstjrzzz.srr.controller;

import com.alstjrzzz.srr.dto.reservation.CancelReservationRequestDTO;
import com.alstjrzzz.srr.dto.reservation.CreateReservationRequestDTO;
import com.alstjrzzz.srr.dto.reservation.GetReservationsResponseDTO;
import com.alstjrzzz.srr.dto.reservation.ReservationResponseDTO;
import com.alstjrzzz.srr.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/api/reservation")
    public ResponseEntity<String> createReservation(@Valid @RequestBody CreateReservationRequestDTO requestDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("입력값이 잘못되었습니다.");
        }

        reservationService.reservation(requestDTO);

        return ResponseEntity.ok("예약이 완료되었습니다.");
    }

    @DeleteMapping("/api/reservation")
    public ResponseEntity<String> cancelReservation(@Valid @RequestBody CancelReservationRequestDTO requestDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("입력값이 잘못되었습니다.");
        }

        reservationService.cancelReservation(requestDTO);

        return ResponseEntity.ok("예약이 취소되었습니다.");
    }

    @GetMapping("/api/reservation")
    public ResponseEntity<GetReservationsResponseDTO> getReservations() {

        List<ReservationResponseDTO> reservationList = reservationService.findAll().stream()
                .map(ReservationResponseDTO::from)
                .collect(Collectors.toList());

        GetReservationsResponseDTO getReservationsResponseDTO = GetReservationsResponseDTO.builder()
                .reservationList(reservationList)
                .build();

        return ResponseEntity.ok().body(getReservationsResponseDTO);
    }
}
