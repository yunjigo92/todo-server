package com.yunji.controller;

import com.yunji.model.TodoEntity;
import com.yunji.model.TodoRequest;
import com.yunji.model.TodoResponse;
import com.yunji.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/")
public class TodoController {
    private final TodoService todoService;

    @PostMapping("")
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request){

        if(ObjectUtils.isEmpty(request.getTitle())){
            return ResponseEntity.badRequest().build();
        }

        if(ObjectUtils.isEmpty(request.getOrder())){
            request.setOrder(0L);
        }

        if(ObjectUtils.isEmpty(request.getCompleted())){
            request.setCompleted(false);
        }
        TodoEntity result = this.todoService.add(request);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){
        TodoEntity todoEntity = todoService.searchById(id);
        return ResponseEntity.ok(new TodoResponse(todoEntity));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll(){

        return null;
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(){
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(){
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        return null;
    }
}
