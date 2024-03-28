package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    public static List<Todo> todoList;

    public TodoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, true, "Todo 2", 2));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodoList() {
        return ResponseEntity.ok(todoList);
    } // this is one way using ResponseEntity

//    @ResponseStatus(HttpStatus.CREATED) -> we can use this annotation to send response status back to the client, but this is not the only way
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo); // this is another way using ResponseEntity
    }
}
