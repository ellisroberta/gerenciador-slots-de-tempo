package com.example.gerenciador_slots_de_tempo.repository;

import com.example.gerenciador_slots_de_tempo.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public interface AvailabilityRepository extends JpaRepository<Availability, UUID> {
        List<Availability> findByProfessionalIdAndDayOfWeek(UUID professionalId, DayOfWeek dayOfWeek);
}
