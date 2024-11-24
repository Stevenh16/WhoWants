package model.mapper;

import model.dto.DonationDto;
import model.entity.Donation;
import model.entity.Person;
import model.entity.Thing;
import model.repository.DonationRepository;
import model.repository.PersonRepository;
import model.repository.ThingRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DonationMapper {
    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected ThingRepository thingRepository;

    @Autowired
    protected DonationRepository donationRepository;

    @Named("complete")
    @Mapping(source = "donation.donor.id", target = "donor")
    @Mapping(source = "donation.beneficiary.id", target = "beneficiary")
    @Mapping(source = "donation.thing.id", target = "thing")
    @Mapping(source = "donation.ratings", target = "ratings")
    public abstract DonationDto toIdDto(Donation donation);
    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "donation.donor.id", target = "donor")
    @Mapping(source = "donation.beneficiary.id", target = "beneficiary")
    @Mapping(source = "donation.thing.id", target = "thing")
    @Mapping(source = "donation.ratings", target = "ratings")
    public abstract DonationDto toDto(Donation donation);

    @Named("listComplete")
    @Mapping(source = "donation.donor.id", target = "donor")
    @Mapping(source = "donation.beneficiary.id", target = "beneficiary")
    @Mapping(source = "donation.thing.id", target = "thing")
    @Mapping(source = "donation.ratings", target = "ratings")
    public abstract List<DonationDto> toListIdDto(List<Donation> donations);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "donation.donor.id", target = "donor")
    @Mapping(source = "donation.beneficiary.id", target = "beneficiary")
    @Mapping(source = "donation.thing.id", target = "thing")
    @Mapping(source = "donation.ratings", target = "ratings")
    public abstract List<DonationDto> toListDto(List<Donation> donations);

    @Named("entity")
    @Mapping(source = "donationDto.donor", target = "donor")
    @Mapping(source = "donationDto.beneficiary", target = "beneficiary")
    @Mapping(source = "donationDto.thing", target = "thing")
    @Mapping(source = "donationDto.ratings", target = "ratings")
    public abstract Donation toEntity(DonationDto donationDto);

    @Mapping(target= "donationDto.id", ignore = true)
    @Mapping(source = "donationDto.donor", target = "donor")
    @Mapping(source = "donationDto.beneficiary", target = "beneficiary")
    @Mapping(source = "donationDto.thing", target = "thing")
    @Mapping(source = "donationDto.ratings", target = "ratings")
    public abstract Donation toEntityWithoutId(DonationDto donationDto);
    @Mapping(source = "donationDto.donor", target = "donor")
    @Mapping(source = "donationDto.beneficiary", target = "beneficiary")
    @Mapping(source = "donationDto.thing", target = "thing")
    @Mapping(source = "donationDto.ratings", target = "ratings")
    public abstract List<Donation> toListEntity(List<DonationDto> donationDtos);

    public Long mapToDonationId(Donation donation) {
        return donation != null ? donation.getId() : null;
    }

    public Long mapToPersonId(Person person) {
        return person != null ? person.getId() : null;
    }

    public Long mapToThingId(Thing thing) {
        return thing != null ? thing.getId() : null;
    }

    public Person mapToPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Thing mapToThing(Long id) {
        return thingRepository.findById(id).orElse(null);
    }

    public Donation mapToDonation(Long id) {
        return donationRepository.findById(id).orElse(null);
    }
}
