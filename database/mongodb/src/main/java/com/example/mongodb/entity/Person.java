package com.example.mongodb.entity;

import lombok.*;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor //自带全参构造
@NoArgsConstructor //自带无参构造
@Builder
@ToString
public class Person {

    private ObjectId id;
    private String name;
    private int age;
    private Address address;
}