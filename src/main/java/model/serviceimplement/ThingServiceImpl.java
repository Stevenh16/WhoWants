package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.ThingDto;
import model.mapper.DonationMapper;
import model.mapper.ThingMapper;
import model.repository.ThingRepository;
import model.service.ThingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ThingServiceImpl implements ThingService {
    private final DonationMapper donationMapper;
    private ThingRepository thingRepository;
    private ThingMapper thingMapper;

    @Override
    public ThingDto save(ThingDto thing) {
        return thingMapper.toIdDto(thingRepository.save(thingMapper.toEntity(thing)));
    }

    @Override
    public Optional<ThingDto> findById(Long id) {
        return thingRepository.findById(id).map(thingMapper::toIdDto);
    }

    @Override
    public Optional<ThingDto> update(Long id, ThingDto thing) {
        return thingRepository.findById(id).map(oldThing->{
            oldThing.setDescription(thing.description());
            oldThing.setName(thing.name());
            oldThing.setDonation(donationMapper.toEntity(thing.donation()));
            return thingMapper.toIdDto(thingRepository.save(oldThing));
        });
    }

    @Override
    public List<ThingDto> findAll() {
        return thingMapper.toListIdDto(thingRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        thingRepository.deleteById(id);
    }
}
