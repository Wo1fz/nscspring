package com.ncs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ncs.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
