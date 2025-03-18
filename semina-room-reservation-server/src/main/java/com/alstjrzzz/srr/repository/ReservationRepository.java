package com.alstjrzzz.srr.repository;

import com.alstjrzzz.srr.domain.Reservation;
import com.alstjrzzz.srr.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    //void saveReservation(ReservationEntity reservationEntity);

    Optional<ReservationEntity> findById(Long reservationId);

    //void deleteReservation(Long reservationId);

    List<ReservationEntity> findAll();

    @Query("SELECT r FROM ReservationEntity r " +
            "WHERE (r.startTime < :endTime AND r.endTime > :startTime) " +
            "   OR (r.startTime >= :startTime AND r.endTime <= :endTime)")
    List<ReservationEntity> findConflictingReservations(@Param("startTime") LocalDateTime startTime,
                                                        @Param("endTime") LocalDateTime endTime);
}
