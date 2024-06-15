package com.example.gerenciador_slots_de_tempo.service;

import com.example.gerenciador_slots_de_tempo.model.Reservation;
import com.example.gerenciador_slots_de_tempo.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Reservation reservation) {
        // Check for conflicts before saving
        List<Reservation> conflictingReservations = reservationRepository.findByProfessionalIdAndStartTimeBetween(
                reservation.getProfessionalId(), reservation.getStartTime(), reservation.getEndTime().plusHours(1));
        if (conflictingReservations.isEmpty()) {
            return reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Conflicting reservation exists");
        }
    }
}
