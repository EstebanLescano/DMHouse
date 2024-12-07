package org.esteban.lescano.dmhouse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletDTO {
    private Integer walletId;
    private Integer personId;
    private List<AccountDTO> accounts;
}
