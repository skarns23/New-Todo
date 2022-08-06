package com.example.newtodo.controller;


import com.example.newtodo.dto.ResponseDTO;
import com.example.newtodo.dto.TestRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping()
    public String testController(){
        return "Hello World";
    }

    @GetMapping("/testGetMapping")
    public String testControllerWithPath(){
        return "Test GetMapping";
    }

    @GetMapping("/{id}")
    public String testPathVariableController(@PathVariable(required = false) String id ){
        return "Hello " +id;
    }

    @GetMapping("/testQueryParam")
    public String testQueryParam(@RequestParam(required = false) String id  ){
        return "Hello World! ID + "+id;
    }

    @GetMapping("/testRequestBody")
    public TestRequestDTO testRequestDTO(@RequestBody TestRequestDTO testRequestDTO){
        return testRequestDTO;
    }

    @GetMapping("/testResponseBody")
    public ResponseDTO<String> responseDTO (){
        List<String >list = new ArrayList<>();
        list.add("Hello World! I'm Response");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data((ArrayList<String>) list).build();
        return response;
    }

    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testResponseEntity(){ // 응답 및 Http status, 헤더를 조작할 수 있음
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello i will give you 400!");
        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.badRequest().body(responseDTO);
    }

}
