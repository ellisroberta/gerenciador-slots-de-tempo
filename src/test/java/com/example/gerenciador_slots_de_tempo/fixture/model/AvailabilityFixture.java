package com.example.gerenciador_slots_de_tempo.fixture.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.example.gerenciador_slots_de_tempo.model.Availability;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public class AvailabilityFixture implements TemplateLoader {

    public static final String VALIDO = "valido";

    @Override
    public void load() {
        Fixture.of(Availability.class).addTemplate(VALIDO, new Rule() {
            {
                add("id", UUID.randomUUID());
                add("professionalId", UUID.randomUUID());
                add("dayOfWeek", random(DayOfWeek.class));
                add("startTime", LocalTime.of(9, 0));
                add("endTime", LocalTime.of(17, 0));
            }
        });
    }
}
