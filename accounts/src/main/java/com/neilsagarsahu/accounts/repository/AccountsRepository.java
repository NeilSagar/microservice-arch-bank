package com.neilsagarsahu.accounts.repository;

import com.neilsagarsahu.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}
