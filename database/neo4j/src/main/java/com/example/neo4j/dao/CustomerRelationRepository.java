package com.example.neo4j.dao;

import com.example.neo4j.bean.CustomerRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CustomerRelationRepository extends Neo4jRepository<CustomerRelation,Long> {
}