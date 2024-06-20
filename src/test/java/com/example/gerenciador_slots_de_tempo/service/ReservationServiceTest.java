package com.example.gerenciador_slots_de_tempo.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.gerenciador_slots_de_tempo.exception.ConflictingReservationException;
import com.example.gerenciador_slots_de_tempo.fixture.BaseFixture;
import com.example.gerenciador_slots_de_tempo.fixture.model.ReservationFixture;
import com.example.gerenciador_slots_de_tempo.model.Reservation;
import com.example.gerenciador_slots_de_tempo.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation reservation;

    @BeforeAll
    public static void setUpFixture() {
        FixtureFactoryLoader.loadTemplates(BaseFixture.ALL.getPacote());
    }

    @BeforeEach
    public void setUp() {
        reservation = Fixture.from(Reservation.class).gimme(ReservationFixture.VALIDO);
    }

    @Test
    @DisplayName("Deve obter todas as reservas com sucesso")
    void testGetAllReservations_Success() {
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation));

        List<Reservation> result = reservationService.getAllReservations();

        assertEquals(1, result.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve criar uma nova reserva com sucesso")
    void testCreateReservation_Success() {
        when(reservationRepository.findByProfessionalIdAndStartTimeBetween(
                any(UUID.class), any(), any())).thenReturn(Collections.emptyList());
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation result = reservationService.createReservation(reservation);

        assertNotNull(result);
        assertEquals(reservation.getId(), result.getId());
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar criar reserva conflitante")
    void testCreateReservation_Conflict() {
        when(reservationRepository.findByProfessionalIdAndStartTimeBetween(
                any(UUID.class), any(), any())).thenReturn(Collections.singletonList(reservation));

        assertThrows(ConflictingReservationException.class, () -> {
            reservationService.createReservation(reservation);
        });

        verify(reservationRepository, times(0)).save(any(Reservation.class));
    }
}