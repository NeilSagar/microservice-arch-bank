package com.neilsagarsahu.accounts.service;

import com.neilsagarsahu.accounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);
    CustomerDto getCustomerByMobileNumber(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber );
}
