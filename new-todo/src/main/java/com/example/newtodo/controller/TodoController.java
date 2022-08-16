package com.example.newtodo.controller;


import com.example.newtodo.dto.ResponseDTO;
import com.example.newtodo.dto.TodoDTO;
import com.example.newtodo.model.TodoEntity;
import com.example.newtodo.service.TodoService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {


    @Autowired // Bean 을 알아서 찾은 다음 연결해줌 TodoService를 초기화하거나 할 필요없음 의존성 주입?
    private TodoService todoService;

    @GetMapping("/test-todo")
    public ResponseEntity<?> testTodo(){
        ArrayList<String> list = new ArrayList<>();
        list.add(todoService.testService());
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }
    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO){
       try {
           String temp_userID = "temporary User ID";
           //TodoDTO -> TodoEntity
           TodoEntity todoEntity = TodoDTO.toEntity(todoDTO);

           //AutoIncrement 여서 초기 ID null로 설정
           todoEntity.setId(null);

           //임시 user ID 부여
           todoEntity.setUserId(temp_userID);

           //Service를 활용하여 todoEntity 저장
           List<TodoEntity> Entity_list = todoService.create(todoEntity);

           //자바 stream을 활용한 TodoDTO 리스트로 변환
           List<TodoDTO> DTO_list = Entity_list.stream().map(TodoDTO::new).collect(Collectors.toList());

           //TodoDTO -> ResponseDTO
           ResponseDTO<TodoDTO> responseDTO = ResponseDTO.<TodoDTO>builder().data(DTO_list).build();

           return ResponseEntity.ok().body(responseDTO);
       } catch (Exception e){ //예외 발생 시
           String error = e.getMessage();
           ResponseDTO<TodoDTO> response_error = ResponseDTO.<TodoDTO>builder().error(error).build();
           return ResponseEntity.badRequest().body(response_error);
       }

       }
}
