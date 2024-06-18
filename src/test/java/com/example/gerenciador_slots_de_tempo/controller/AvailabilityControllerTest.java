package com.example.gerenciador_slots_de_tempo.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.gerenciador_slots_de_tempo.dto.AvailabilityDTO;
import com.example.gerenciador_slots_de_tempo.fixture.BaseFixture;
import com.example.gerenciador_slots_de_tempo.fixture.model.AvailabilityFixture;
import com.example.gerenciador_slots_de_tempo.model.Availability;
import com.example.gerenciador_slots_de_tempo.service.AvailabilityService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AvailabilityControllerTest {
    @Mock
    private AvailabilityService availabilityService;

    @InjectMocks
    private AvailabilityController availabilityController;

    private UUID availabilityId;
    private Availability availability;

    @BeforeAll
    public static void setUpFixture() {
        FixtureFactoryLoader.loadTemplates(BaseFixture.ALL.getPacote());
    }

    @BeforeEach
    public void setUp() {
        availabilityId = UUID.randomUUID();
        availability = Fixture.from(Availability.class).gimme(AvailabilityFixture.VALIDO);
        availability.setId(availabilityId);
    }

    @Test
    @DisplayName("Deve obter todas as disponibilidades com sucesso")
    void testGetAllAvailabilities_Success() {
        when(availabilityService.getAllAvailabilities()).thenReturn(Collections.singletonList(availability));

        List<AvailabilityDTO> result = availabilityController.getAllAvailabilities();

        Assertions.assertEquals(1, result.size());
        verify(availabilityService, times(1)).getAllAvailabilities();
    }

    @Test
    @DisplayName("Deve obter disponibilidade por ID com sucesso")
    void testGetAvailabilityById_Success() {
        when(availabilityService.getAvailabilityById(availabilityId)).thenReturn(availability);

        ResponseEntity<AvailabilityDTO> result = availabilityController.getAvailabilityById(availabilityId);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(availabilityService, times(1)).getAvailabilityById(availabilityId);
    }

    @Test
    @DisplayName("Deve retornar 404 ao não encontrar disponibilidade por ID")
    void testGetAvailabilityById_NotFound() {
        when(availabilityService.getAvailabilityById(availabilityId)).thenReturn(null);

        ResponseEntity<AvailabilityDTO> result = availabilityController.getAvailabilityById(availabilityId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(availabilityService, times(1)).getAvailabilityById(availabilityId);
    }

    @Test
    @DisplayName("Deve criar uma nova disponibilidade com sucesso")
    void testCreateAvailability_Success() {
        when(availabilityService.save(any(Availability.class))).thenReturn(availability);

        ResponseEntity<AvailabilityDTO> result = availabilityController.createAvailability(new AvailabilityDTO());

        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(availabilityService, times(1)).save(any(Availability.class));
    }

    @Test
    @DisplayName("Deve atualizar uma disponibilidade com sucesso")
    void testUpdateAvailability_Success() {
        when(availabilityService.updateAvailability(eq(availabilityId), any(Availability.class))).thenReturn(availability);

        ResponseEntity<AvailabilityDTO> result = availabilityController.updateAvailability(availabilityId, new AvailabilityDTO());

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(availabilityService, times(1)).updateAvailability(eq(availabilityId), any(Availability.class));
    }

    @Test
    @DisplayName("Deve retornar 404 ao tentar atualizar uma disponibilidade não encontrada")
    void testUpdateAvailability_NotFound() {
        when(availabilityService.updateAvailability(eq(availabilityId), any(Availability.class))).thenReturn(null);

        ResponseEntity<AvailabilityDTO> result = availabilityController.updateAvailability(availabilityId, new AvailabilityDTO());

        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(availabilityService, times(1)).updateAvailability(eq(availabilityId), any(Availability.class));
    }

    @Test
    @DisplayName("Deve deletar uma disponibilidade com sucesso")
    void testDeleteAvailability_Success() {
        doNothing().when(availabilityService).deleteAvailability(availabilityId);

        ResponseEntity<Void> result = availabilityController.deleteAvailability(availabilityId);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(availabilityService, times(1)).deleteAvailability(availabilityId);
    }
}
