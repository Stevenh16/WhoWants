package model.mapper;

import model.dto.DonationDto;
import model.entity.Donation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonationMapper {
    @Named("complete")
    DonationDto toIdDto(Donation donation);
    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    DonationDto toDto(Donation donation);
    @Named("listComplete")
    List<DonationDto> toListIdDto(List<Donation> donations);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    List<DonationDto> toListDto(List<Donation> donations);

    Donation toEntity(DonationDto donationDto);
    List<Donation> toListEntity(List<DonationDto> donationDtos);
}
