package model.mapper;

import model.dto.DonationDto;
import model.entity.Donation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonMapper.class, ThingMapper.class, RatingMapper.class})
public interface DonationMapper {
    @Named("complete")
    @Mapping(source = "donation.donor", target = "donor", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.beneficiary", target = "beneficiary", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.thing", target = "thing", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.ratings", target = "ratings", qualifiedByName = "listCompleteWithoutDonation")
    DonationDto toIdDto(Donation donation);
    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "donation.donor", target = "donor", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.beneficiary", target = "beneficiary", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.thing", target = "thing", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.ratings", target = "ratings", qualifiedByName = "listCompleteWithoutDonation")
    DonationDto toDto(Donation donation);

    @Named("listComplete")
    @Mapping(source = "donation.donor", target = "donor", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.beneficiary", target = "beneficiary", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.thing", target = "thing", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.ratings", target = "ratings", qualifiedByName = "listCompleteWithoutDonation")
    List<DonationDto> toListIdDto(List<Donation> donations);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "donation.donor", target = "donor", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.beneficiary", target = "beneficiary", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.thing", target = "thing", qualifiedByName = "completeWithoutDonation")
    @Mapping(source = "donation.ratings", target = "ratings", qualifiedByName = "listCompleteWithoutDonation")
    List<DonationDto> toListDto(List<Donation> donations);

    @Mapping(source = "donationDto.donor", target = "donor", qualifiedByName = "entityWithoutDonation")
    @Mapping(source = "donationDto.beneficiary", target = "beneficiary", qualifiedByName = "entityWithoutDonation")
    @Mapping(source = "donationDto.thing", target = "thing", qualifiedByName = "entityWithoutDonation")
    @Mapping(source = "donationDto.ratings", target = "ratings", qualifiedByName = "listEntityWithoutDonation")
    Donation toEntity(DonationDto donationDto);
    @Mapping(source = "donationDto.donor", target = "donor", qualifiedByName = "entityWithoutDonation")
    @Mapping(source = "donationDto.beneficiary", target = "beneficiary", qualifiedByName = "entityWithoutDonation")
    @Mapping(source = "donationDto.thing", target = "thing", qualifiedByName = "entityWithoutDonation")
    @Mapping(source = "donationDto.ratings", target = "ratings", qualifiedByName = "listEntityWithoutDonation")
    List<Donation> toListEntity(List<DonationDto> donationDtos);

    @Named("completeWithoutEntities")
    @Mapping(target = "donor",ignore = true)
    @Mapping(target = "beneficiary", ignore = true)
    @Mapping(target = "thing", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    DonationDto toIdDtoWithoutEntities(Donation donation);

    @Named("listCompleteWithoutEntities")
    @Mapping(target = "donor",ignore = true)
    @Mapping(target = "beneficiary", ignore = true)
    @Mapping(target = "thing", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    List<DonationDto> toListIdDtoWithoutEntities(List<Donation> donations);

    @Named("withoutIdWithoutEntities")
    @Mapping(target = "donor",ignore = true)
    @Mapping(target = "beneficiary", ignore = true)
    @Mapping(target = "thing", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "id", ignore = true)
    DonationDto toDtoWithoutEntities(Donation donation);

    @Named("listWithoutIdWithoutEntities")
    @Mapping(target = "donor",ignore = true)
    @Mapping(target = "beneficiary", ignore = true)
    @Mapping(target = "thing", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "id", ignore = true)
    List<DonationDto> toListDtoWithoutEntities(List<Donation> donation);

    @Named("entityWithoutDtos")
    @Mapping(target = "donor",ignore = true)
    @Mapping(target = "beneficiary", ignore = true)
    @Mapping(target = "thing", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    Donation toDonationWithoutDtos(DonationDto donation);

    @Named("listEntityWithoutDtos")
    @Mapping(target = "donor",ignore = true)
    @Mapping(target = "beneficiary", ignore = true)
    @Mapping(target = "thing", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    List<Donation> toListDonationWithoutEntities(List<DonationDto> donation);
}
