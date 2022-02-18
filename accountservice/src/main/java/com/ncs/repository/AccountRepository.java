package com.ncs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncs.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	List<Account> findAllByCustomerId(Integer customerId);
	List<Account> findAllByAccountType(String accountType);
	List<Account> findByBank(String bank);
}
