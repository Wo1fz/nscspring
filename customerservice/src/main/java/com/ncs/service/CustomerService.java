package com.ncs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.exception.CustomerNotFoundException;
import com.ncs.model.Account;
import com.ncs.model.Customer;
import com.ncs.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AccountService accountService;

	public List<Customer> getCustomers() {
		List<Customer> customers = customerRepository.findAll();
		for (Customer c : customers) {
			c.setAccount(accountService.findByCustomer(c.getCustomerId()).getBody());
		}
		return customers;
	}

	public Customer findByCustomerId(Integer customerId) {
		Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		customer.setAccount(accountService.findByCustomer(customerId).getBody());
		return customer;
	}

	public Customer addCustomer(Customer customer) {
		List<Account> accounts = customer.getAccount();
		for (Account account : accounts) {
			accountService.save(account);
		}
		Customer result = customerRepository.saveAndFlush(customer);
		result.setAccount(accountService.findByCustomer(result.getCustomerId()).getBody());
		return result;
	}

	public Customer updateCustomer(Customer customer) {
		findByCustomerId(customer.getCustomerId());
		List<Account> accounts = customer.getAccount();
		for (Account account : accounts) {
			accountService.save(account);
		}
		return customerRepository.saveAndFlush(customer);
	}

	public boolean deleteCustomer(Integer customerId) {
		findByCustomerId(customerId);
		List<Account> accounts = accountService.findByCustomer(customerId).getBody();
		for (Account account : accounts) {
			accountService.delete(account.getAccountId());
		}
		customerRepository.deleteById(customerId);
		return true;
	}
}
