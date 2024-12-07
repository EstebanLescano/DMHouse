package org.esteban.lescano.dmhouse.mappers;

import org.esteban.lescano.dmhouse.entities.Wallet;
import org.esteban.lescano.dmhouse.models.WalletDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface WalletMapper {
    WalletDTO toDto(Wallet wallet);
    Wallet toEntity(WalletDTO walletDTO);
}

