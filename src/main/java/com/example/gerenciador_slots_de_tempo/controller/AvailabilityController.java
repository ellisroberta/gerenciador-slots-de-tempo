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
import java.util.Optional;
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
        Optional<Availability> optionalAvailability = availabilityService.findById(id);

        return optionalAvailability.map(availability -> ResponseEntity.ok(convertToDTO(availability)))
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Criar uma nova disponibilidade",
            notes = "Cria uma nova disponibilidade e retorna os detalhes")
    @PostMapping
    public ResponseEntity<AvailabilityDTO> createAvailability(@RequestBody AvailabilityDTO availabilityDTO) {
        Availability availability = convertToEntity(availabilityDTO);
        availability = availabilityService.save(availability);

        availabilityDTO.setId(availability.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(availabilityDTO);
    }

    @ApiOperation(value = "Atualizar disponibilidade",
            notes = "Atualiza uma disponibilidade existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<AvailabilityDTO> updateAvailability(@PathVariable UUID id,
                                                              @RequestBody AvailabilityDTO availabilityDTO) {
        AvailabilityDTO updatedAvailabilityDTO = null;
        if (availabilityDTO != null) {
            Availability availabilityDetails = convertToEntity(availabilityDTO);

            Availability updatedAvailability = availabilityService.updateAvailability(id, availabilityDetails);

            if (updatedAvailability != null) {
                updatedAvailabilityDTO = convertToDTO(updatedAvailability);
            }
        }
        if (updatedAvailabilityDTO != null) {
            return ResponseEntity.ok(updatedAvailabilityDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Excluir disponibilidade",
            notes = "Exclui uma disponibilidade existente pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable UUID id) {
        availabilityService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }

    private AvailabilityDTO convertToDTO(Availability entity) {
        return AvailabilityDTO.builder()
                .id(entity.getId())
                .professionalId(entity.getProfessionalId())
                .dayOfWeek(entity.getDayOfWeek())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .build();
    }

    private Availability convertToEntity(AvailabilityDTO dto) {
        return Availability.builder()
                .professionalId(dto.getProfessionalId())
                .dayOfWeek(dto.getDayOfWeek())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .build();
    }
}
