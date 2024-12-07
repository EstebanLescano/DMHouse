package org.esteban.lescano.dmhouse.mappers;

import org.esteban.lescano.dmhouse.entities.Account;
import org.esteban.lescano.dmhouse.models.AccountDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    // Mapea la entidad Account a DTO
    AccountDTO toDto(Account account);

    // Mapea el DTO AccountDTO a entidad Account
    Account toEntity(AccountDTO accountDTO);
}
