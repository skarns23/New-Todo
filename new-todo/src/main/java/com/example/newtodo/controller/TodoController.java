package com.example.newtodo.controller;


import com.example.newtodo.dto.ResponseDTO;
import com.example.newtodo.service.TodoService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
}
