package model.service;

import model.dto.PersonDto;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    PersonDto save(PersonDto personDto);
    Optional<PersonDto> findById(Long id);
    Optional<PersonDto> update(Long id, PersonDto personDto);
    List<PersonDto> findAll();
    List<PersonDto> findByName(String name);
    void delete(Long id);
}
