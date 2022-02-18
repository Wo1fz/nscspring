package com.ncs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.exception.AccountNotFoundException;
import com.ncs.model.Account;
import com.ncs.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accRepo;

	public Account findByAccountId(Integer accountId) {
		return accRepo.findById(accountId).orElseThrow(AccountNotFoundException::new);
	}

	public List<Account> findByAccountType(String type) {
		return accRepo.findAllByAccountType(type);
	}

	public List<Account> findByBank(String bank) {
		return accRepo.findByBank(bank);
	}

	public List<Account> findByCustomer(Integer customerId) {
		return accRepo.findAllByCustomerId(customerId);
	}

	public List<Account> getAccounts() {
		return accRepo.findAll();
	}

	public Account addAccount(Account account) {
		return accRepo.saveAndFlush(account);
	}

	public Account updateAccount(Account account) {
		Account found = findByAccountId(account.getAccountId());
		found = account;
		return accRepo.saveAndFlush(found);
	}

	public boolean deleteAccount(Integer accountId) {
		Account found = findByAccountId(accountId);
		accRepo.delete(found);
		return true;
	}
}
