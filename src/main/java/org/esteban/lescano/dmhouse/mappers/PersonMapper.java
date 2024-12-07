package org.esteban.lescano.dmhouse.mappers;

import org.esteban.lescano.dmhouse.entities.Person;
import org.esteban.lescano.dmhouse.models.PersonDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO toDto(Person person);

    Person toEntity(PersonDTO personDTO);
}
