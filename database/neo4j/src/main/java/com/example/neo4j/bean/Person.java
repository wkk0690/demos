package com.example.neo4j.bean;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;
import java.util.stream.Collectors;

@Node
@ToString
@NoArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Relationship
    @JsonIgnoreProperties({"familyMember"})
    public Map<String, List<Person>> familyMember;

    public void linkWith(String type, Person person) {
        if (familyMember == null) {
            familyMember = new HashMap<>();
        }
        List<Person> listOfPerson = familyMember.get(type);
        if (listOfPerson == null) {
            listOfPerson = new ArrayList<>();
        }
        listOfPerson.add(person);
        familyMember.put(type, listOfPerson);
    }
}
