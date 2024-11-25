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
    @Mapping(source = "rating.donation.id", target = "donation")
    @Mapping(source = "rating.person.id", target = "person")
    RatingDto toIdDto(Rating rating);
    @Named("withoutId")
    @Mapping(source = "rating.donation.id", target = "donation")
    @Mapping(source = "rating.person.id", target = "person")
    @Mapping(target = "id", ignore = true)
    RatingDto toDto(Rating rating);
    @Named("listComplete")
    @Mapping(source = "rating.donation.id", target = "donation")
    @Mapping(source = "rating.person.id", target = "person")
    List<RatingDto> toListIdDto(List<Rating> ratings);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "rating.donation.id", target = "donation")
    @Mapping(source = "rating.person.id", target = "person")
    List<RatingDto> toListDto(List<Rating> ratings);


    @Mapping(source = "ratingDto.donation", target = "donation")
    @Mapping(source = "ratingDto.person", target = "person")
    Rating toEntity(RatingDto ratingDto);
    @Mapping(source = "ratingDto.donation", target = "donation")
    @Mapping(source = "ratingDto.person", target = "person")
    List<Rating> toListEntity(List<RatingDto> ratingDtos);
}
