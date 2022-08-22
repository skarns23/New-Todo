package com.example.newtodo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder // Builder 를 이용하지 않고도 builder 패턴을 사용가능함
@NoArgsConstructor //매개변수 없는 생산자 생성
@AllArgsConstructor //모든 매개변수를 갖는 생성자 생성
@Data //클래스 멤버 변수의 Getter/Setter 를 생성해줌
@Entity
@Table(name = "Todo")
public class TodoEntity {
    @Id // Primary Key
    @GeneratedValue(generator = "system-uuid") // id를 자동으로 생성함
    @GenericGenerator(name = "system-uuid",strategy ="uuid") // Hibernate가 제공하는 기본 Generator가 아닌 나만의 것을 사용할 때 이용
    private String id; // 해당 Todo 오브젝트의 아이디
    private String userId; // 해당 오브젝트를 생성한 유저의 아이디
    private String title; // Todo 오브젝트의 제목 ( ex : 산책 )

    private Boolean done ; // 해당 오브젝트의 수행 여부



}
