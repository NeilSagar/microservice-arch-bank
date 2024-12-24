package com.neilsagarsahu.accounts.service.impl;

import com.neilsagarsahu.accounts.constants.AccountsConstants;
import com.neilsagarsahu.accounts.dto.CustomerDto;
import com.neilsagarsahu.accounts.entity.Accounts;
import com.neilsagarsahu.accounts.entity.Customer;
import com.neilsagarsahu.accounts.exception.CustomerAlreadyExistsException;
import com.neilsagarsahu.accounts.mapper.CustomerMapper;
import com.neilsagarsahu.accounts.repository.AccountsRepository;
import com.neilsagarsahu.accounts.repository.CustomerRepository;
import com.neilsagarsahu.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer is present with given mobile number : "+customerDto.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }
}
