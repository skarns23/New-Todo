package com.example.newtodo.dto;


import com.example.newtodo.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
    private String id; // 오브젝트 id
    private String title; // 오브젝트의 타이틀

    @Builder.Default
    private Boolean done = false; // 성공여부

    public TodoDTO(final TodoEntity entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.getDone();
    }

    public static TodoEntity toEntity(final TodoDTO todoDTO){
        TodoEntity todoEntity = TodoEntity.builder()
                .title(todoDTO.getTitle())
                .id(todoDTO.getId())
                .done(todoDTO.getDone()).build();
        return todoEntity;
    }


}
