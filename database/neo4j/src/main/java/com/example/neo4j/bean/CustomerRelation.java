package com.example.neo4j.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRelation {
    @Id
    @GeneratedValue
    private Long id;
    private Date createTime;
    private String remark;

    private Customer customerFrom;

    private Customer customerTo;
}