package com.example.newtodo.service;

import org.springframework.stereotype.Service;

@Service //@Component 와 역할은 크게 차이가 없으나 Service라고 명시해줌
public class TodoService {

    public String testService(){
        return "Test Service";
    }
}
