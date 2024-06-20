package com.example.gerenciador_slots_de_tempo.controller;

import com.example.gerenciador_slots_de_tempo.dto.ReservationDTO;
import com.example.gerenciador_slots_de_tempo.model.Availability;
import com.example.gerenciador_slots_de_tempo.model.Reservation;
import com.example.gerenciador_slots_de_tempo.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @ApiOperation(value = "Obter todas as reservas", notes = "Retorna uma lista de todas as reservas")
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return reservations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ApiOperation(value = "Criar uma nova reserva", notes = "Cria uma nova reserva e retorna os detalhes")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            Reservation reservation = convertToEntity(reservationDTO);
            reservation = reservationService.createReservation(reservation);
            ReservationDTO newReservationDTO = convertToDTO(reservation);
            return ResponseEntity.status(HttpStatus.CREATED).body(newReservationDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    private ReservationDTO convertToDTO(Reservation reservation) {
        return ReservationDTO.builder()
                .professionalId(reservation.getProfessionalId())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();
    }

    private Reservation convertToEntity(ReservationDTO reservationDTO) {
        return Reservation.builder()
                .professionalId(reservationDTO.getProfessionalId())
                .startTime(reservationDTO.getStartTime())
                .endTime(reservationDTO.getEndTime())
                .build();
    }
}