package model.mapper;

import model.dto.PersonDto;
import model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DonationMapper.class})
public interface PersonMapper {
    @Named("complete")
    @Mapping(source = "person.donations", target = "donations", qualifiedByName = "listComplete")
    @Mapping(source = "person.benefits", target = "benefits", qualifiedByName = "listComplete")
    PersonDto toIdDto(Person person);

    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "person.donations", target = "donations", qualifiedByName = "listWithoutId")
    @Mapping(source = "person.benefits", target = "benefits", qualifiedByName = "listWithoutId")
    PersonDto toDto(Person person);

    @Named("listComplete")
    @Mapping(source = "person.donations", target = "donations", qualifiedByName = "listComplete")
    @Mapping(source = "person.benefits", target = "benefits", qualifiedByName = "listComplete")
    List<PersonDto> toListIdDto(List<Person> persons);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "person.donations", target = "donations", qualifiedByName = "listWithoutId")
    @Mapping(source = "person.benefits", target = "benefits", qualifiedByName = "listWithoutId")
    List<PersonDto> toListDto(List<Person> persons);

    @Mapping(source = "personDto.donations", target = "donations")
    @Mapping(source = "personDto.benefits", target = "benefits")
    Person toEntity(PersonDto personDto);

    @Mapping(source = "person.donations", target = "donations")
    @Mapping(source = "person.benefits", target = "benefits")
    List<Person> toListEntity(List<PersonDto> personDtos);
}
