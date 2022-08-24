package com.example.neo4j.dao;

import com.example.neo4j.bean.Customer;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface CustomerRepository extends Neo4jRepository<Customer, Long> {

    Customer findByName(String name);

    Customer deleteByName(String name);

    /**
     * 根据节点名称查找关系
     *
     * @param name
     * @return
     */
    @Query("MATCH c=(cf:Customer)-[r:CustomerRelation]->(ct:Customer) WHERE ct.name=$name OR cf.name=$name RETURN cf")
    List<Customer> findRelationByCustomer(String name);

    @Query("MATCH c=(cf:Customer) WHERE cf.id=$id RETURN cf")
    Customer findNodeById(Long id);
}