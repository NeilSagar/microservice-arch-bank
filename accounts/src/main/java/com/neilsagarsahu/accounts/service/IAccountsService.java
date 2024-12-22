package com.neilsagarsahu.accounts.service;

import com.neilsagarsahu.accounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);
}
