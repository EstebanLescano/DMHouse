package org.esteban.userservice.mappers;

import org.esteban.userservice.entity.Client;
import org.esteban.userservice.models.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ClientDTO toDto(Client client);

    Client toEntity(ClientDTO clientDTO);
}

