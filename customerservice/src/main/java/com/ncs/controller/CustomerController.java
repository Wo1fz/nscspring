package com.ncs.controller;

import java.net.URI;
import java.util.List;

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

import com.ncs.model.Customer;
import com.ncs.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	@GetMapping(value = "/customer")
	public ResponseEntity<List<Customer>> getCustomers() {
		return new ResponseEntity<List<Customer>>(customerService.getCustomers(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/customer/{customerId}")
	public ResponseEntity<Customer> findByCustomerId(@PathVariable Integer customerId) {
		Customer result = customerService.findByCustomerId(customerId);
		return new ResponseEntity<Customer>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Customer> save(@RequestBody Customer customer) {
		Customer result = customerService.addCustomer(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/customerId").buildAndExpand(result.getCustomerId()).toUri();
		return ResponseEntity.created(location).body(customer);
	}
	
	@PutMapping(value = "/customer")
	public ResponseEntity<Customer> update(@RequestBody Customer customer) {
		Customer result = customerService.updateCustomer(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{customerId}").buildAndExpand(result.getCustomerId()).toUri();
		return ResponseEntity.created(location).body(customer);
	}
	
	@DeleteMapping(value = "/customer/{customerId}")
	public ResponseEntity<?> delete(@PathVariable Integer customerId) {
		if (customerService.deleteCustomer(customerId)) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().body("Something unexpected happened!");
		}
	}
}
