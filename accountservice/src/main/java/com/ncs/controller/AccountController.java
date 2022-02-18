package com.ncs.controller;

import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ncs.exception.AccountTypeNotFoundException;
import com.ncs.exception.BankNotFoundException;
import com.ncs.model.Account;
import com.ncs.service.AccountService;

@RestController
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@GetMapping(value = "/account/{accountId}")
	public ResponseEntity<Account> findByAccountId(@PathVariable Integer accountId) {
		Account result = accountService.findByAccountId(accountId);
		logger.info("Find by Account ID :" + accountId);
		return new ResponseEntity<Account>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/account/account-type/{type}")
	public ResponseEntity<List<Account>> findByAccountType(@PathVariable String type) {
		List<Account> result = accountService.findByAccountType(type);
		logger.info("Find by Account Type :" + type);
		if (result.isEmpty())
			throw new AccountTypeNotFoundException();
		return new ResponseEntity<List<Account>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/account/bank/{bank}")
	public ResponseEntity<List<Account>> findByBank(@PathVariable String bank) {
		List<Account> result = accountService.findByBank(bank);
		logger.info("Find by Bank :" + bank);
		if (result.isEmpty())
			throw new BankNotFoundException();
		return new ResponseEntity<List<Account>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/account/customer/{customerId}")
	public ResponseEntity<List<Account>> findByCustomer(@PathVariable Integer customerId) {
		List<Account> result = accountService.findByCustomer(customerId);
		logger.info("Find by Customer :" + customerId);
		return new ResponseEntity<List<Account>>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/account")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Account> save(@RequestBody Account account) {
		Account result = accountService.addAccount(account);
		logger.info("Add Account :" + account);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{accountId}")
				.buildAndExpand(result.getAccountId()).toUri();
		return ResponseEntity.created(location).body(account);
	}

	@GetMapping(value = "/account")
	public ResponseEntity<List<Account>> all() {
		List<Account> result = accountService.getAccounts();
		logger.info("Find all accounts information ");
		return new ResponseEntity<List<Account>>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/account")
	public ResponseEntity<Account> update(@RequestBody Account account) {
		Account result = accountService.updateAccount(account);
		logger.info("Update Account :" + account);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{accountId}").buildAndExpand(result.getAccountId()).toUri();
		return ResponseEntity.created(location).body(account);
	}

	@DeleteMapping(value = "/account/{accountId}")
	public ResponseEntity<?> delete(@PathVariable Integer accountId) {
		if (accountService.deleteAccount(accountId)) {
			logger.info("delete by Account id:" + accountId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().body("Something unexpected happened!");
		}
	}
}
