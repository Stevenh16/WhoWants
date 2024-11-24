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
    RatingDto toIdDto(Rating rating);
    @Named("withoutId")
    @Mapping(source = "rating.donation.id", target = "donation")
    @Mapping(target = "id", ignore = true)
    RatingDto toDto(Rating rating);
    @Named("listComplete")
    @Mapping(source = "rating.donation.id", target = "donation")
    List<RatingDto> toListIdDto(List<Rating> ratings);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "rating.donation.id", target = "donation")
    List<RatingDto> toListDto(List<Rating> ratings);


    @Mapping(source = "ratingDto.donation", target = "donation")
    Rating toEntity(RatingDto ratingDto);
    @Mapping(source = "ratingDto.donation", target = "donation")
    List<Rating> toListEntity(List<RatingDto> ratingDtos);
}
