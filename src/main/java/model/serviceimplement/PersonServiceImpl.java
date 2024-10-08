package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.PersonDto;
import model.entity.Person;
import model.mapper.DonationMapper;
import model.mapper.PersonMapper;
import model.repository.PersonRepository;
import model.service.PersonService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final DonationMapper donationMapper;
    private PersonRepository personRepository;
    private PersonMapper personMapper;

    @Override
    public PersonDto save(PersonDto personDto) {
        return personMapper.toIdDto(personRepository.save(personMapper.toEntity(personDto)));
    }

    @Override
    public Optional<PersonDto> findById(Long id) {
        return personRepository.findById(id).map(personMapper::toIdDto);
    }

    @Override
    public Optional<PersonDto> update(Long id, PersonDto personDto) {
        return personRepository.findById(id).map(oldPerson ->{
            oldPerson.setBenefits(donationMapper.toListEntity(personDto.benefits()));
            oldPerson.setDonations(donationMapper.toListEntity(personDto.donations()));
            oldPerson.setName(personDto.name());
            oldPerson.setEmail(personDto.email());
            oldPerson.setPassword(personDto.password());
            return personMapper.toIdDto(personRepository.save(oldPerson));
        });
    }

    @Override
    public List<PersonDto> findAll() {
        return personMapper.toListIdDto(personRepository.findAll());
    }

    @Override
    public List<PersonDto> findByName(String name) {
        Person p = new Person();
        p.setName(name);
        Example<Person> example = Example.of(p);
        return personMapper.toListIdDto(personRepository.findAll(example));
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
