package com.example.neo4j.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Map;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
}