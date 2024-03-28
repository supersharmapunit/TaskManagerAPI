package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private static List<Todo> todoList;

    public TodoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, true, "Todo 2", 2));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodoList() {
        return ResponseEntity.ok(todoList);
    } // this is one way using ResponseEntity

//    @ResponseStatus(HttpStatus.CREATED) -> we can use this annotation to send response status back to the client, but this is not the only way
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo); // this is another way using ResponseEntity
    }

    @GetMapping("/{todoID}")
    public ResponseEntity<?> getTodoByID(@PathVariable Long todoID) {
        for(Todo todo : todoList) {
            if(todo.getId() == todoID) return ResponseEntity.ok(todo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(null, false, HttpStatus.NOT_FOUND.toString(), "no todo exists with this id."));
    }

    @DeleteMapping("/{todoID}")
    public ResponseEntity<String> deleteByID(@PathVariable Long todoID) {
        todoList = todoList.stream().filter(todo -> todo.getId() != todoID).collect(Collectors.toList());
        return ResponseEntity.ok("Successfully deleted the todo");
    }

    @PatchMapping("/{todoID}")
    public ResponseEntity<?> updateTodoByID(@PathVariable Long todoID, @RequestBody Todo newTodo) {
        Todo todo = null;
        for(Todo t : todoList) {
            if(todoID == t.getId()) {
                todo = t;
                break;
            }
        }
        if(todo == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(null, false, HttpStatus.NOT_FOUND.toString(), "no todo exists with this id."));

        if(newTodo.getUserId() != null) {
            todo.setUserId(newTodo.getUserId());
        }
        if(newTodo.getTitle() != null) {
            todo.setTitle(newTodo.getTitle());
        }
        if(newTodo.isCompleted()) {
            todo.setCompleted(true);
        }

        return ResponseEntity.ok(todo);
    }
}
