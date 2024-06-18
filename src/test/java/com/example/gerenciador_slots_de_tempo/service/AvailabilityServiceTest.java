package com.example.gerenciador_slots_de_tempo.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.gerenciador_slots_de_tempo.fixture.BaseFixture;
import com.example.gerenciador_slots_de_tempo.fixture.model.AvailabilityFixture;
import com.example.gerenciador_slots_de_tempo.model.Availability;
import com.example.gerenciador_slots_de_tempo.repository.AvailabilityRepository;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvailabilityServiceTest {

    @Mock
    private AvailabilityRepository availabilityRepository;

    @InjectMocks
    private AvailabilityService availabilityService;

    private Availability availability;
    private UUID availabilityId;

    @BeforeAll
    public static void setUpFixture() {
        FixtureFactoryLoader.loadTemplates(BaseFixture.ALL.getPacote());
    }

    @BeforeEach
    public void setUp() {
        availability = Fixture.from(Availability.class).gimme(AvailabilityFixture.VALIDO);
        availabilityId = availability.getId();
    }

    @Test
    @DisplayName("Deve salvar uma disponibilidade com sucesso")
    void testSaveAvailability_Success() {
        when(availabilityRepository.save(any(Availability.class))).thenReturn(availability);

        Availability result = availabilityService.save(availability);

        assertNotNull(result);
        assertEquals(availabilityId, result.getId());
        verify(availabilityRepository, times(1)).save(any(Availability.class));
    }

    @Test
    @DisplayName("Deve obter todas as disponibilidades com sucesso")
    void testGetAllAvailabilities_Success() {
        when(availabilityRepository.findAll()).thenReturn(Collections.singletonList(availability));

        List<Availability> result = availabilityService.getAllAvailabilities();

        assertEquals(1, result.size());
        verify(availabilityRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve obter uma disponibilidade por ID com sucesso")
    void testGetAvailabilityById_Success() {
        when(availabilityRepository.findById(any(UUID.class))).thenReturn(Optional.of(availability));

        Availability result = availabilityService.getAvailabilityById(availabilityId);

        assertNotNull(result);
        assertEquals(availabilityId, result.getId());
        verify(availabilityRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    @DisplayName("Deve retornar null ao não encontrar disponibilidade por ID")
    void testGetAvailabilityById_NotFound() {
        when(availabilityRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Availability result = availabilityService.getAvailabilityById(availabilityId);

        assertNull(result);
        verify(availabilityRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    @DisplayName("Deve atualizar uma disponibilidade com sucesso")
    void testUpdateAvailability_Success() {
        Availability updatedAvailability = Fixture.from(Availability.class).gimme("valido");
        updatedAvailability.setProfessionalId(UUID.randomUUID());

        when(availabilityRepository.findById(any(UUID.class))).thenReturn(Optional.of(availability));
        when(availabilityRepository.save(any(Availability.class))).thenReturn(updatedAvailability);

        Availability result = availabilityService.updateAvailability(availabilityId, updatedAvailability);

        assertNotNull(result);
        assertEquals(updatedAvailability.getProfessionalId(), result.getProfessionalId());
        verify(availabilityRepository, times(1)).findById(any(UUID.class));
        verify(availabilityRepository, times(1)).save(any(Availability.class));
    }

    @Test
    @DisplayName("Deve retornar null ao tentar atualizar disponibilidade não encontrada")
    void testUpdateAvailability_NotFound() {
        when(availabilityRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Availability result = availabilityService.updateAvailability(availabilityId, availability);

        assertNull(result);
        verify(availabilityRepository, times(1)).findById(any(UUID.class));
        verify(availabilityRepository, times(0)).save(any(Availability.class));
    }

    @Test
    @DisplayName("Deve deletar uma disponibilidade com sucesso")
    void testDeleteAvailability_Success() {
        doNothing().when(availabilityRepository).deleteById(any(UUID.class));

        availabilityService.deleteAvailability(availabilityId);

        verify(availabilityRepository, times(1)).deleteById(any(UUID.class));
    }
}