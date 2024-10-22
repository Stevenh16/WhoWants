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
    @Mapping(source = "person.donations", target = "donations", qualifiedByName = "listCompleteWithoutEntities")
    @Mapping(source = "person.benefits", target = "benefits", qualifiedByName = "listCompleteWithoutEntities")
    PersonDto toIdDto(Person person);

    @Named("withoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "person.donations", target = "donations", qualifiedByName = "listWithoutIdWithoutEntities")
    @Mapping(source = "person.benefits", target = "benefits", qualifiedByName = "listWithoutIdWithoutEntities")
    PersonDto toDto(Person person);

    @Named("listComplete")
    @Mapping(source = "person.donations", target = "donations", qualifiedByName = "listCompleteWithoutEntities")
    @Mapping(source = "person.benefits", target = "benefits", qualifiedByName = "listCompleteWithoutEntities")
    List<PersonDto> toListIdDto(List<Person> persons);

    @Named("listWithoutId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "person.donations", target = "donations", qualifiedByName = "listWithoutIdeWithoutEntities")
    @Mapping(source = "person.benefits", target = "benefits", qualifiedByName = "listWithoutIdWithoutEntities")
    List<PersonDto> toListDto(List<Person> persons);

    @Mapping(source = "personDto.donations", target = "donations", qualifiedByName = "listEntityWithoutDtos")
    @Mapping(source = "personDto.benefits", target = "benefits", qualifiedByName = "listEntityWithoutDtos")
    Person toEntity(PersonDto personDto);

    @Mapping(source = "person.donations", target = "donations", qualifiedByName = "listEntityWithoutEntities")
    @Mapping(source = "person.benefits", target = "benefits", qualifiedByName = "listEntityWithoutEntities")
    List<Person> toListEntity(List<PersonDto> personDtos);

    @Named("completeWithoutDonation")
    @Mapping(target = "donations", ignore = true)
    @Mapping(target = "benefits", ignore = true)
    PersonDto toIdDtoWithoutDonation(Person person);

    @Named("listCompleteWithoutDonation")
    @Mapping(target = "donations", ignore = true)
    @Mapping(target = "benefits", ignore = true)
    List<PersonDto> toListIdDtoWithoutDonation(List<Person> persons);

    @Named("withoutIdWithoutDonation")
    @Mapping(target = "donations", ignore = true)
    @Mapping(target = "benefits", ignore = true)
    @Mapping(target = "id", ignore = true)
    PersonDto toDtoWithoutDonation(Person person);

    @Named("listWithoutIdWithoutDonation")
    @Mapping(target = "donations", ignore = true)
    @Mapping(target = "benefits", ignore = true)
    @Mapping(target = "id", ignore = true)
    List<PersonDto> toListDtoWithoutDonation(List<Person> persons);

    @Named("entityWithoutDonation")
    @Mapping(target = "donations", ignore = true)
    @Mapping(target = "benefits", ignore = true)
    Person toEntityWithoutDonation(PersonDto personDto);

    @Named("listEntityWithoutDonation")
    @Mapping(target = "donations", ignore = true)
    @Mapping(target = "benefits", ignore = true)
    List<Person> toListEntityWithoutDonation(List<PersonDto> personDtos);
}
