package org.esteban.userservice.mappers;


import org.esteban.userservice.entity.Person;
import org.esteban.userservice.models.PersonDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO toDto(Person person);

    Person toEntity(PersonDTO personDTO);
}
