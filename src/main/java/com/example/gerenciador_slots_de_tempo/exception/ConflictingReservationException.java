package com.example.gerenciador_slots_de_tempo.exception;

public class ConflictingReservationException extends RuntimeException {
    public ConflictingReservationException(String message) {
        super(message);
    }
}
