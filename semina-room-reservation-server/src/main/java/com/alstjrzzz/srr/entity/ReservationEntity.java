package com.alstjrzzz.srr.entity;

import com.alstjrzzz.srr.domain.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    public static ReservationEntity from(Reservation reservation) {

        return ReservationEntity.builder()
                .nickname(reservation.getNickname())
                .reservationId(reservation.getReservationId())
                .studentName(reservation.getStudentName())
                .studentId(reservation.getStudentId())
                .phoneNumber(reservation.getPhoneNumber())
                .purpose(reservation.getPurpose())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();
    }

    public Reservation toReservation() {

        return Reservation.builder()
                .nickname(nickname)
                .reservationId(reservationId)
                .studentName(studentName)
                .studentId(studentId)
                .phoneNumber(phoneNumber)
                .purpose(purpose)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
