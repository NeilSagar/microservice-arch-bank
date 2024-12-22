package com.neilsagarsahu.accounts.repository;

import com.neilsagarsahu.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
