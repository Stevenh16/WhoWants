package model.service;

import model.dto.ThingDto;

import java.util.List;
import java.util.Optional;

public interface ThingService {
    ThingDto save(ThingDto thing);
    Optional<ThingDto> findById(Long id);
    Optional<ThingDto> update(Long id, ThingDto thing);
    List<ThingDto> findAll();
    void delete(Long id);
}
