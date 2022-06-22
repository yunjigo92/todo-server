package com.yunji.service;

import com.yunji.model.TodoModel;
import com.yunji.model.TodoRequest;
import com.yunji.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoModel add(TodoRequest todoRequest){
        TodoModel todoModel = new TodoModel();
        todoModel.setTitle(todoRequest.getTitle());
        todoModel.setOrder(todoRequest.getOrder());
        todoModel.setCompleted(todoRequest.getCompleted());

        return this.todoRepository.save(todoModel);
    }

    public TodoModel searchById(Long id){

        return this.todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<TodoModel> searchAll(){
        return this.todoRepository.findAll();
    }

    public TodoModel updateById(Long id, TodoRequest todoRequest){

        TodoModel todoModel = this.searchById(id);

        if(todoRequest.getTitle() != null){
            todoModel.setTitle(todoRequest.getTitle());
        }

        if(todoRequest.getOrder() != null){
            todoModel.setOrder(todoRequest.getOrder());
        }

        if(todoRequest.getCompleted() != null){
            todoModel.setCompleted(todoRequest.getCompleted());
        }

        return this.todoRepository.save(todoModel);
    }

    public void deleteById(Long id){
        this.todoRepository.deleteById(id);
    }

    public void deleteAll(){
        this.todoRepository.deleteAll();
    }

}
