package com.example.gerenciador_slots_de_tempo.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationDTO {

    private UUID professionalId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
