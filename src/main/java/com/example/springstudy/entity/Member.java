package com.example.springstudy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Entity
public class Member {
    @Id  //엔티티의 대표값 지정
    @GeneratedValue
    private Long id;
    @Column  // email 필드 선언, DB 테이블의 email
    private String email;
    @Column  // password 필드 선언, DB 테이블의 password
    private String password;
}
