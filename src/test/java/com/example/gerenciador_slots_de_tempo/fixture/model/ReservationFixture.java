package com.example.gerenciador_slots_de_tempo.fixture.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.example.gerenciador_slots_de_tempo.model.Reservation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class ReservationFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        Fixture.of(Reservation.class).addTemplate(VALIDO, new Rule() {
            {
                add("id", UUID.randomUUID());
                add("professionalId", UUID.randomUUID());
                add("startTime", LocalTime.now().plusHours(1));
                add("endTime", LocalTime.now().plusHours(1));
            }
        });
    }
}
