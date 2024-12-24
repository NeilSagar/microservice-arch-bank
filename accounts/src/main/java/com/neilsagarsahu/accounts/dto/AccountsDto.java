package com.neilsagarsahu.accounts.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {

    @Pattern(regexp = "^$|[0,9]{10}", message = "Account number should be 10 digits.")
    @NotEmpty(message="accountNumber can not be empty.")
    private Long accountNumber;

    @NotEmpty(message="accountType can not be empty.")
    private String accountType;

    @NotEmpty(message="branchAddress can not be empty.")
    private String branchAddress;
}
