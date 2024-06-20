package com.example.gerenciador_slots_de_tempo.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.gerenciador_slots_de_tempo.controller.ReservationController;
import com.example.gerenciador_slots_de_tempo.dto.ReservationDTO;
import com.example.gerenciador_slots_de_tempo.fixture.BaseFixture;
import com.example.gerenciador_slots_de_tempo.fixture.model.ReservationFixture;
import com.example.gerenciador_slots_de_tempo.model.Reservation;
import com.example.gerenciador_slots_de_tempo.service.ReservationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    private Reservation reservation;

    private ReservationDTO reservationDTO;

    @BeforeAll
    public static void setUpFixture() {
        FixtureFactoryLoader.loadTemplates(BaseFixture.ALL.getPacote());
    }

    @BeforeEach
    public void setUp() {
        reservation = Fixture.from(Reservation.class).gimme(ReservationFixture.VALIDO);

        reservationDTO = ReservationDTO.builder()
                .professionalId(reservation.getProfessionalId())
                .startTime(reservation.getStartTime())
                .endTime(reservation.getEndTime())
                .build();
    }

    @Test
    @DisplayName("Deve obter todas as reservas com sucesso")
    void testGetAllReservations_Success() {
        List<Reservation> reservations = Collections.singletonList(reservation);
        when(reservationService.getAllReservations()).thenReturn(reservations);

        List<ReservationDTO> result = reservationController.getAllReservations();

        assertEquals(1, result.size());
        verify(reservationService, times(1)).getAllReservations();
    }

    @Test
    @DisplayName("Deve criar uma nova reserva com sucesso")
    void testCreateReservation_Success() {
        when(reservationService.createReservation(any(Reservation.class))).thenReturn(reservation);

        ResponseEntity<ReservationDTO> result = reservationController.createReservation(reservationDTO);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        assertEquals(reservationDTO.getProfessionalId(), result.getBody().getProfessionalId());
        assertEquals(reservationDTO.getStartTime(), result.getBody().getStartTime());
        assertEquals(reservationDTO.getEndTime(), result.getBody().getEndTime());

        verify(reservationService, times(1)).createReservation(any(Reservation.class));
    }

    @Test
    @DisplayName("Deve retornar conflito ao tentar criar uma reserva duplicada")
    void testCreateReservation_Conflict() {
        when(reservationService.createReservation(any(Reservation.class))).thenThrow(new RuntimeException());

        ResponseEntity<ReservationDTO> result = reservationController.createReservation(reservationDTO);

        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        verify(reservationService, times(1)).createReservation(any(Reservation.class));
    }
}