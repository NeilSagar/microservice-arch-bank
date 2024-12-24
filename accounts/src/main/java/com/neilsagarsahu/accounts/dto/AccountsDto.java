package com.neilsagarsahu.accounts.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@AllArgsConstructor
public class AccountsDto {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
