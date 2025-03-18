package com.alstjrzzz.srr.service;

import com.alstjrzzz.srr.domain.Reservation;
import com.alstjrzzz.srr.dto.reservation.CancelReservationRequestDTO;
import com.alstjrzzz.srr.dto.reservation.CreateReservationRequestDTO;
import com.alstjrzzz.srr.entity.ReservationEntity;
import com.alstjrzzz.srr.exception.DuplicateReservationException;
import com.alstjrzzz.srr.exception.InvalidReservationIdException;
import com.alstjrzzz.srr.exception.InvalidReservationTimeException;
import com.alstjrzzz.srr.exception.InvalidStudentInfoException;
import com.alstjrzzz.srr.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public void reservation(CreateReservationRequestDTO requestDTO) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime maxReservationTime = now.plusDays(7);
        LocalDateTime minAllowedStartTime = now.minusHours(1);

        if (requestDTO.getEndTime().isBefore(requestDTO.getStartTime()) ||
            requestDTO.getStartTime().isBefore(minAllowedStartTime) ||
            requestDTO.getEndTime().isBefore(now) ||
            requestDTO.getStartTime().isAfter(maxReservationTime) ||
            requestDTO.getEndTime().isAfter(maxReservationTime)) {

            throw new InvalidReservationTimeException("예약 시간이 올바르지 않습니다.");
        }

        if (!isReservationTimeAvailable(requestDTO.getStartTime(), requestDTO.getEndTime())) {
            throw new DuplicateReservationException("이미 예약된 시간입니다.");
        }

        Reservation reservation = Reservation.builder().nickname(requestDTO.getNickname())
                .studentName(requestDTO.getStudentName())
                .studentId(requestDTO.getStudentId())
                .phoneNumber(requestDTO.getPhoneNumber())
                .purpose(requestDTO.getPurpose())
                .startTime(requestDTO.getStartTime())
                .endTime(requestDTO.getEndTime())
                .build();

        reservationRepository.save(ReservationEntity.from(reservation));
    }

    public void cancelReservation(CancelReservationRequestDTO requestDTO) {

        Long reservationId = requestDTO.getReservationId();
        Integer studentId = requestDTO.getStudentId();
        String studentName = requestDTO.getStudentName();

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new InvalidReservationIdException("존재하지 않는 예약 ID입니다."))
                .toReservation();

        if (reservation.getStudentId().equals(studentId) &&
            reservation.getStudentName().equals(studentName)) {

            reservationRepository.delete(ReservationEntity.from(reservation));
        }
        else {
            throw new InvalidStudentInfoException("입력하신 학번 또는 암호가 올바르지 않습니다.");
        }
    }

    public List<Reservation> findAll() {

        return reservationRepository.findAll()
                .stream()
                .map(ReservationEntity::toReservation)
                .collect(Collectors.toList());
    }

    public boolean isReservationTimeAvailable(LocalDateTime startTime, LocalDateTime endTime) {

        return reservationRepository.findConflictingReservations(startTime, endTime).isEmpty();
    }
}
