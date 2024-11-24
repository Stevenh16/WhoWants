package model.mapper;

import model.dto.ThingDto;
import model.entity.Thing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DonationMapper.class})
public interface ThingMapper {

    @Named("complete")
    @Mapping(source = "thing.donation", target = "donation", qualifiedByName = "complete")
    ThingDto toIdDto(Thing thing);

    @Named("withoutId")
    @Mapping(source = "thing.donation", target = "donation", qualifiedByName = "withoutId")
    @Mapping(target = "id", ignore = true)
    ThingDto toDto(Thing thing);

    @Named("listComplete")
    @Mapping(source = "thing.donation", target = "donation", qualifiedByName = "complete")
    List<ThingDto> toListIdDto(List<Thing> things);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "thing.donation", target = "donation", qualifiedByName = "withoutId")
    List<ThingDto> toListDto(List<Thing> things);

    @Mapping(source = "thingDto.donation", target = "donation", qualifiedByName = "entity")
    Thing toEntity(ThingDto thingDto);

    @Mapping(source = "thingDto.donation", target = "donation", qualifiedByName = "entity")
    List<Thing> toListEntity(List<ThingDto> thingDtos);
}

