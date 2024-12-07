package org.esteban.lescano.dmhouse.mappers;

import org.esteban.lescano.dmhouse.entities.Transaction;
import org.esteban.lescano.dmhouse.models.TransactionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDTO toDto(Transaction transaction);

    TransactionDTO toEntity(Transaction transaction);
}
