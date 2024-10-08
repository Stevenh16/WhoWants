package model.mapper;

import model.dto.PersonDto;
import model.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Named("complete")
    PersonDto toIdDto(Person person);
    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    PersonDto toDto(Person person);
    @Named("listComplete")
    List<PersonDto> toListIdDto(List<Person> persons);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    List<PersonDto> toListDto(List<Person> persons);

    Person toEntity(PersonDto personDto);
    List<Person> toListEntity(List<PersonDto> personDtos);
}
