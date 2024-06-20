package com.example.gerenciador_slots_de_tempo.repository;

import com.example.gerenciador_slots_de_tempo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> findByProfessionalIdAndStartTimeBetween(UUID professionalId,
                                                              LocalTime start,
                                                              LocalTime end);
}
