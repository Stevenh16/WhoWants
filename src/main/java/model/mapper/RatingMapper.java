package model.mapper;

import model.dto.RatingDto;
import model.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    @Named("complete")
    RatingDto toIdDto(Rating rating);
    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    RatingDto toDto(Rating rating);
    @Named("listComplete")
    List<RatingDto> toListIdDto(List<Rating> ratings);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    List<RatingDto> toListDto(List<Rating> ratings);

    Rating toEntity(RatingDto ratingDto);
    List<Rating> toListEntity(List<RatingDto> ratingDtos);
}
