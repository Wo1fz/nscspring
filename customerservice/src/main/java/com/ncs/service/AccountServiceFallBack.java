package com.ncs.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ncs.model.Account;

@Service
public class AccountServiceFallBack implements AccountService {

	@Override
	public ResponseEntity<List<Account>> findByCustomer(Integer customerId) {
		Account fallback = new Account(0, 0.0, customerId, "Dummy", "Dummy", "Dummy");
		return ResponseEntity.ok(List.of(fallback));
	}

	@Override
	public ResponseEntity<Account> save(Account account) {
		Account fallback = new Account(0, 0.0, account.getCustomerId(), "Dummy", "Dummy", "Dummy");
		return ResponseEntity.ok(fallback);
	}

	@Override
	public ResponseEntity<?> delete(Integer accountId) {
		return ResponseEntity.badRequest().body("Something unexpected happened!");
	}

}
