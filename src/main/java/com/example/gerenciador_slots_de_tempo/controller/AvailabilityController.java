package com.example.gerenciador_slots_de_tempo.controller;

import com.example.gerenciador_slots_de_tempo.dto.AvailabilityDTO;
import com.example.gerenciador_slots_de_tempo.model.Availability;
import com.example.gerenciador_slots_de_tempo.service.AvailabilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/availabilities")
@Api(tags = "Disponibilidades")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService){
        this.availabilityService = availabilityService;
    }

    @ApiOperation(value = "Obter todas as disponibilidades",
            notes = "Retorna uma lista de todas as disponibilidades")
    @GetMapping
    public List<AvailabilityDTO> getAllAvailabilities() {
        List<Availability> availabilities = availabilityService.getAllAvailabilities();
        return availabilities.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @ApiOperation(value = "Obter disponibilidade por ID",
            notes = "Retorna uma disponibilidade específica por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AvailabilityDTO> getAvailabilityById(@PathVariable UUID id) {
        Availability availability = availabilityService.getAvailabilityById(id);
        if (availability != null) {
            AvailabilityDTO userDTO = convertToDTO(availability);
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Criar uma nova disponibilidade",
            notes = "Cria uma nova disponibilidade e retorna os detalhes")
    @PostMapping
    public ResponseEntity<AvailabilityDTO> createAvailability(@RequestBody AvailabilityDTO availabilityDTO) {
        Availability availability = convertToEntity(availabilityDTO);
        availability = availabilityService.save(availability);
        AvailabilityDTO newAvailabilityDTO = convertToDTO(availability);

        return ResponseEntity.status(HttpStatus.CREATED).body(newAvailabilityDTO);
    }

    @ApiOperation(value = "Atualizar disponibilidade",
            notes = "Atualiza uma disponibilidade existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<AvailabilityDTO> updateAvailability(@PathVariable UUID id,
                                                              @RequestBody AvailabilityDTO availabilityDTO) {
        Availability availabilityDetails = convertToEntity(availabilityDTO);
        Availability updatedAvailability = availabilityService.updateAvailability(id, availabilityDetails);

        if (updatedAvailability != null) {
            AvailabilityDTO updatedAvailabilityDTO = convertToDTO(updatedAvailability);
            return ResponseEntity.ok(updatedAvailabilityDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Excluir disponibilidade",
            notes = "Exclui uma disponibilidade existente pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable UUID id) {
        availabilityService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }

    private AvailabilityDTO convertToDTO(Availability entity) {
        AvailabilityDTO dto = new AvailabilityDTO();
        dto.setProfessionalId(entity.getProfessionalId());
        dto.setDayOfWeek(entity.getDayOfWeek());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        return dto;
    }

    private Availability convertToEntity(AvailabilityDTO dto) {
        Availability entity = new Availability();
        entity.setProfessionalId(dto.getProfessionalId());
        entity.setDayOfWeek(dto.getDayOfWeek());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        return entity;
    }
}
