package com.yunji.controller;

import com.yunji.model.TodoModel;
import com.yunji.model.TodoRequest;
import com.yunji.model.TodoResponse;
import com.yunji.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        TodoModel result = this.todoService.add(request);

        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){
        TodoModel todoModel = todoService.searchById(id);
        return ResponseEntity.ok(new TodoResponse(todoModel));
    }

    @GetMapping("/")
    public ResponseEntity<List<TodoResponse>> readAll(){
        List<TodoModel> list =this.todoService.searchAll();
        List<TodoResponse> responses = list.stream().map(TodoResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request)
    {
        TodoModel result = this.todoService.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        this.todoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll()
    {
        this.todoService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
