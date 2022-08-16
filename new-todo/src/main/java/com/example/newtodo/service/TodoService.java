package com.example.newtodo.service;

import com.example.newtodo.model.TodoEntity;
import com.example.newtodo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service //@Component 와 역할은 크게 차이가 없으나 Service라고 명시해줌
public class TodoService {

    @Autowired
    private TodoRepository repository;
    public String testService(){

        TodoEntity todoEntity = TodoEntity.builder().title("운동").build();
        repository.save(todoEntity);
        TodoEntity saveEntity = repository.findById(todoEntity.getId()).get();
        return saveEntity.getTitle();
    }

    public List<TodoEntity> create (final TodoEntity todoEntity){
        //검증
        validate(todoEntity);
        repository.save(todoEntity);
        log.info("Entity Id : {} is saved",todoEntity.getId());
        return repository.findByUserId(todoEntity.getUserId());
    }

    private void validate(final TodoEntity entity){
        if(entity == null){
            log.warn("Entity is null");
            throw new RuntimeException("Entity is null");
        }
        if(entity.getUserId() == null){
            log.warn("Unknown User");
            throw new RuntimeException("Unknown User");
        }
    }


}
