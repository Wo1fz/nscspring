package com.ncs.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ncs.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer>{

	List<Todo> findByUser(String user);
	List<Todo> findByTargetDateAfterAndUserAndDoneFalse(LocalDate date,String user);
	List<Todo> findByOrderByUserAsc();
	@Query("select t from Todo t where t.user = ?1")
	List<Todo> getTodosOfUser(String user);
}
