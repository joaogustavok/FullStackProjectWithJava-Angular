package com.joao.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joao.todo.domain.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
	
	@Query("SELECT obj FROM Todo obj WHERE obj.finalizado = false ORDER BY obj.dataFinalizar")
	List<Todo> findAllOpen();
	
	@Query("SELECT obj FROM Todo obj WHERE obj.finalizado = true ORDER BY obj.dataFinalizar")
	List<Todo> findAllClose();

}