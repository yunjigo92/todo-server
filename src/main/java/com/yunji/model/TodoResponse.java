package com.yunji.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private String url;

    public TodoResponse(TodoModel todoModel){
        this.id = todoModel.getId();
        this.title = todoModel.getTitle();
        this.order = todoModel.getOrder();
        this.completed = todoModel.getCompleted();
        //임의의 프로젝트이기 때문에 아래처럼 사용.
        this.url = "http://localhost:8080/" + this.id;
    }

}
