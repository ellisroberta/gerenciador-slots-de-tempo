package com.example.gerenciador_slots_de_tempo.fixture;

import lombok.Getter;

@Getter
public enum BaseFixture {

    MODEL("com.example.gerenciador_slots_de_tempo.model"),
    ALL("com.example.gerenciador_slots_de_tempo.fixture");

    private final String pacote;

    BaseFixture(final String pacote) {
        this.pacote = pacote;
    }
}
