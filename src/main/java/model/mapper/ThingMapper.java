package model.mapper;

import model.dto.ThingDto;
import model.entity.Thing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ThingMapper {
    @Named("complete")
    ThingDto toIdDto(Thing thing);
    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    ThingDto toDto(Thing thing);
    @Named("listComplete")
    List<ThingDto> toListIdDto(List<Thing> things);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    List<ThingDto> toListDto(List<Thing> things);

    Thing toEntity(ThingDto thingDto);
    List<Thing> toListEntity(List<ThingDto> thingDtos);
}
