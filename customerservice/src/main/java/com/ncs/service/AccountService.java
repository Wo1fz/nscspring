package com.ncs.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ncs.model.Account;

@Service
@FeignClient(name = "ACCOUNT-SERVICE", fallback = AccountServiceFallBack.class)
public interface AccountService {
	@GetMapping(value = "/account/customer/{customerId}")
	public ResponseEntity<List<Account>> findByCustomer(@PathVariable Integer customerId);
	@PostMapping(value = "/account")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Account> save(@RequestBody Account account);
	@DeleteMapping(value = "/account/{accountId}")
	public ResponseEntity<?> delete(@PathVariable Integer accountId);
}
