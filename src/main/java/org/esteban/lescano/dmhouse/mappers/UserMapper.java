package org.esteban.lescano.dmhouse.mappers;

import org.esteban.lescano.dmhouse.entities.Client;
import org.esteban.lescano.dmhouse.models.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ClientDTO toDto(Client client);

    Client toEntity(ClientDTO clientDTO);
}

