package com.joao.todo.resourcer;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joao.todo.domain.Todo;
import com.joao.todo.services.TodoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/todos")
public class TodoResource {

	@Autowired
	private TodoService todoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Todo> findByid(@PathVariable Long id) {
		Todo todo = todoService.findById(id);
		return ResponseEntity.ok().body(todo);
	}

	@GetMapping(value = "/open")
	public ResponseEntity<List<Todo>> listOpen() {
		List<Todo> list = todoService.findAllOpen();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/close")
	public ResponseEntity<List<Todo>> listClose() {
		List<Todo> list = todoService.findAllClose();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping
	public ResponseEntity<List<Todo>> listAll() {
		List<Todo> list = todoService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Todo> create(@RequestBody Todo todo) {
		todo = todoService.create(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todo.getId()).toUri();
		return ResponseEntity.created(uri).body(todo);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		todoService.delete(id);
		return ResponseEntity.noContent().build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo todo) {
		Todo newTodo = todoService.update(id, todo);
		return ResponseEntity.ok().body(newTodo);
	}
}
