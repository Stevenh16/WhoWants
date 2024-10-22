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
    @Mapping(source = "thing.donation", target = "donation", qualifiedByName = "completeWithoutEntities")
    ThingDto toIdDto(Thing thing);

    @Named("withoutId")
    @Mapping(source = "thing.donation", target = "donation", qualifiedByName = "withoutIdWithoutEntities")
    @Mapping(target = "id", ignore = true)
    ThingDto toDto(Thing thing);

    @Named("listComplete")
    @Mapping(source = "thing.donation", target = "donation", qualifiedByName = "completeWithoutEntities")
    List<ThingDto> toListIdDto(List<Thing> things);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "thing.donation", target = "donation", qualifiedByName = "withoutIdWithoutEntities")
    List<ThingDto> toListDto(List<Thing> things);

    @Mapping(source = "thingDto.donation", target = "donation", qualifiedByName = "entityWithoutDtos")
    Thing toEntity(ThingDto thingDto);

    @Mapping(source = "thingDto.donation", target = "donation", qualifiedByName = "entityWithoutDtos")
    List<Thing> toListEntity(List<ThingDto> thingDtos);

    @Named("completeWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    ThingDto toIdDtoWithoutDonation(Thing thing);

    @Named("listCompleteWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    List<ThingDto> toListIdDtoWithoutDonation(List<Thing> things);

    @Named("withoutIdWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    @Mapping(target = "id", ignore = true)
    ThingDto toDtoWithoutDonation(Thing thing);

    @Named("listWithoutIdWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    @Mapping(target = "id", ignore = true)
    List<ThingDto> toListDtoWithoutDonation(List<Thing> things);

    @Named("entityWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    Thing toEntityWithoutDto(ThingDto thingDto);

    @Named("listEntityWithoutDonation")
    @Mapping(target = "donation", ignore = true)
    List<Thing> toListEntityWithoutDto(List<ThingDto> thingDtos);
}

