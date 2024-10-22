package model.mapper;

import model.dto.RatingDto;
import model.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DonationMapper.class})
public interface RatingMapper {
    @Named("complete")
    @Mapping(source = "rating.donation", target = "donation", qualifiedByName = "completeWithoutEntities")
    RatingDto toIdDto(Rating rating);
    @Named("withoutId")
    @Mapping(source = "rating.donation", target = "donation", qualifiedByName = "withoutIdWithoutEntities")
    @Mapping(target = "id", ignore = true)
    RatingDto toDto(Rating rating);
    @Named("listComplete")
    @Mapping(source = "rating.donation", target = "donation", qualifiedByName = "completeWithoutEntities")
    List<RatingDto> toListIdDto(List<Rating> ratings);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "rating.donation", target = "donation", qualifiedByName = "withoutIdWithoutEntities")
    List<RatingDto> toListDto(List<Rating> ratings);


    @Mapping(source = "ratingDto.donation", target = "donation", qualifiedByName = "entityWithoutDtos")
    Rating toEntity(RatingDto ratingDto);
    @Mapping(source = "ratingDto.donation", target = "donation", qualifiedByName = "entityWithoutDtos")
    List<Rating> toListEntity(List<RatingDto> ratingDtos);

    @Named("completeWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    RatingDto toIdDtoWithoutDonation(Rating rating);

    @Named("listCompleteWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    List<RatingDto> toListIdDtoWithoutDonation(List<Rating> ratings);

    @Named("withoutIdWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    @Mapping(target = "id", ignore = true)
    RatingDto toDtoWithoutDonation(Rating rating);

    @Named("listWithoutIdWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    @Mapping(target = "id", ignore = true)
    List<RatingDto> toListDtoWithoutDonation(List<Rating> ratings);

    @Named("entityWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    Rating toEntityWithoutDto(RatingDto ratingDto);

    @Named("listEntityWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    List<Rating> toListEntityWithoutDto(List<RatingDto> ratingDtos);
}
