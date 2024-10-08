package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.DonationDto;
import model.entity.Donation;
import model.mapper.DonationMapper;
import model.mapper.PersonMapper;
import model.mapper.RatingMapper;
import model.mapper.ThingMapper;
import model.repository.DonationRepository;
import model.service.DonationService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DonationServiceImpl implements DonationService{

    private final PersonMapper personMapper;
    private final ThingMapper thingMapper;
    private final RatingMapper ratingMapper;
    private DonationRepository donationRepository;
    private DonationMapper donationMapper;

    @Override
    public DonationDto save(DonationDto donationDto) {
        return donationMapper.toIdDto(donationRepository.save(donationMapper.toEntity(donationDto)));
    }

    @Override
    public Optional<DonationDto> findById(Long id) {
        return donationRepository.findById(id).map(donationMapper::toIdDto);
    }

    @Override
    public Optional<DonationDto> update(Long id, DonationDto donationDto) {
        return donationRepository.findById(id).map(oldDonation->{
            oldDonation.setBeneficiary(personMapper.toEntity(donationDto.beneficiary()));
            oldDonation.setDonor(personMapper.toEntity(donationDto.donor()));
            oldDonation.setDate(donationDto.date());
            oldDonation.setThing(thingMapper.toEntity(donationDto.thing()));
            oldDonation.setRatings(ratingMapper.toListEntity(donationDto.ratings()));
            return donationMapper.toIdDto(donationRepository.save(oldDonation));
        });
    }

    @Override
    public List<DonationDto> findAll() {
        return donationMapper.toListIdDto(donationRepository.findAll());
    }

    @Override
    public List<DonationDto> findByDate(LocalDateTime date) {
        Donation d = new Donation();
        d.setDate(date);
        Example<Donation> example = Example.of(d);
        return donationMapper.toListIdDto(donationRepository.findAll(example));
    }

    @Override
    public void delete(Long id) {
        donationRepository.deleteById(id);
    }
}
