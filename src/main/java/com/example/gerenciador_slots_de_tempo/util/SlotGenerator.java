package com.example.gerenciador_slots_de_tempo.util;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SlotGenerator {

    public static List<LocalTime> generateSlots(LocalTime startTime, LocalTime endTime) {
        List<LocalTime> slots = new ArrayList<>();
        LocalTime time = startTime;

        while (time.isBefore(endTime)) {
            slots.add(time);
            time = time.plusMinutes(30);
        }

        return slots;
    }
}
