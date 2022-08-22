package com.example.newtodo.service;

import com.example.newtodo.model.TodoEntity;
import com.example.newtodo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    // 유저가 갖고 있는 모든 Todo 반환
    public List<TodoEntity> retrieve(final String userID){
        log.info("retrieve Service");
        return repository.findByUserId(userID);
    }


    //한 가지의 객체를 업데이트하는 느낌
    public List<TodoEntity> update(final TodoEntity todoEntity){

        //유효성 검증
        validate(todoEntity);

        final Optional<TodoEntity> original = repository.findById(todoEntity.getId());

        //반환된 TodoEntity가 존재하는 경우 새로운 Entity에 값을 담아 준다.
        original.ifPresent(todo->{
            todo.setTitle(todoEntity.getTitle());
            todo.setDone(todoEntity.getDone());

            //DB에 새 값 추가
            repository.save(todo);
        });

        return retrieve(todoEntity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity todoEntity){
        validate(todoEntity);

        try{
            repository.delete(todoEntity);
        } catch (Exception e){
            log.error("error in delete TodoEntity : "+todoEntity.getId());
            throw new RuntimeException("error in delete TodoEntity : "+todoEntity.getId());
        }
        return retrieve(todoEntity.getUserId());

    }

}
