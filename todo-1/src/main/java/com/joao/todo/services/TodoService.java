package com.joao.todo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.todo.domain.Todo;
import com.joao.todo.repositories.TodoRepository;
import com.joao.todo.services.exceptions.ObjectNotFoundException;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	public Todo findById(Long id) {
		Optional<Todo> todo = todoRepository.findById(id);
		return todo.orElseThrow(
				() -> new ObjectNotFoundException("Objeto nao encontrado: " + id + ", Tipo: " + Todo.class.getName()));
	}

	public List<Todo> findAllOpen() {
		List<Todo> list = todoRepository.findAllOpen();
		return list;
	}

	public List<Todo> findAllClose() {
		List<Todo> list = todoRepository.findAllClose();
		return list;
	}

	public List<Todo> findAll() {
		List<Todo> list = todoRepository.findAll();
		return list;
	}

	public Todo create(Todo todo) {
		todo.setId(null);
		return todoRepository.save(todo);
	}

	public void delete(Long id) {
		todoRepository.deleteById(id);

	}

	public Todo update(Long id, Todo todo) {
		Todo newTodo = findById(id);
		newTodo.setTitulo(todo.getTitulo());
		newTodo.setDataFinalizar(todo.getDataFinalizar());
		newTodo.setDescricao(todo.getDescricao());
		newTodo.setFinalizado(todo.getFinalizado());
		return todoRepository.save(newTodo);
	}

	
	

}
