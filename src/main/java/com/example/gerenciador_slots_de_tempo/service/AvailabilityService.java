package com.example.gerenciador_slots_de_tempo.service;

import com.example.gerenciador_slots_de_tempo.model.Availability;
import com.example.gerenciador_slots_de_tempo.repository.AvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository){
        this.availabilityRepository = availabilityRepository;
    }

    public Availability save(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    public Availability getAvailabilityById(UUID id) {
        return availabilityRepository.findById(id).orElse(null);
    }

    public Availability createAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public Availability updateAvailability(UUID id, Availability availabilityDetails) {
        Availability availability = availabilityRepository.findById(id).orElse(null);
        if (availability != null) {
            availability.setProfessionalId(availabilityDetails.getProfessionalId());
            availability.setDayOfWeek(availabilityDetails.getDayOfWeek());
            availability.setStartTime(availabilityDetails.getStartTime());
            availability.setEndTime(availabilityDetails.getEndTime());
            return availabilityRepository.save(availability);
        }
        return null;
    }

    public void deleteAvailability(UUID id) {
        availabilityRepository.deleteById(id);
    }
}
