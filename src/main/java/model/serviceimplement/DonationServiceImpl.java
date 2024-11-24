package model.serviceimplement;

import lombok.AllArgsConstructor;
import model.dto.DonationDto;
import model.entity.Donation;
import model.mapper.DonationMapper;
import model.mapper.RatingMapper;
import model.repository.DonationRepository;
import model.repository.PersonRepository;
import model.repository.ThingRepository;
import model.service.DonationService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DonationServiceImpl implements DonationService{
    private final RatingMapper ratingMapper;
    private final PersonRepository personRepository;
    private final ThingRepository thingRepository;
    private DonationRepository donationRepository;
    private DonationMapper donationMapper;

    @Override
    public DonationDto save(DonationDto donationDto) {
        return donationMapper.toIdDto(donationRepository.save(new Donation(
                null,
                donationDto.date(),
                donationDto.img(),
                donationMapper.mapToPerson(donationDto.donor()),
                null,
                donationMapper.mapToThing(donationDto.thing()),
                null
        )));
    }

    @Override
    public Optional<DonationDto> findById(Long id) {
        return donationRepository.findById(id).map(donationMapper::toIdDto);
    }

    @Override
    public Optional<DonationDto> update(Long id, DonationDto donationDto) {
        return donationRepository.findById(id).map(oldDonation->{
            oldDonation.setBeneficiary(personRepository.findById(donationDto.beneficiary()).get());
            oldDonation.setDonor(personRepository.findById(donationDto.donor()).get());
            oldDonation.setDate(donationDto.date());
            oldDonation.setThing(thingRepository.findById(donationDto.thing()).get());
            oldDonation.setRatings(ratingMapper.toListEntity(donationDto.ratings()));
            return donationMapper.toIdDto(donationRepository.save(oldDonation));
        });
    }

    @Override
    public List<DonationDto> findAll() {
        List<DonationDto> donations = donationMapper.toListIdDto(donationRepository.findAll());
        donations.sort((d1, d2) -> d2.date().compareTo(d1.date()));
        return donations;
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
