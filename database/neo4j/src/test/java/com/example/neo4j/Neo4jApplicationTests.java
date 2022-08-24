package com.example.neo4j;

import com.example.neo4j.bean.Customer;
import com.example.neo4j.bean.CustomerRelation;
import com.example.neo4j.bean.Person;
import com.example.neo4j.dao.CustomerRelationRepository;
import com.example.neo4j.dao.CustomerRepository;
import com.example.neo4j.dao.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class Neo4jApplicationTests {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerRelationRepository customerRelationRepository;

    @Test
    public void find(){
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     *  添加节点，并添加关系，正常情况下应该由文件导入，此处仅是测试
     */
    @Test
    public void add() {
        Date now = new Date();

        Customer customer1 = Customer.builder().name("jack").age(11).build();
        Customer customer2 = Customer.builder().name("rose").age(12).build();
        Customer customer3 = Customer.builder().name("tom").age(13).build();
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);

        //添加关系
        CustomerRelation customerRelation1 = CustomerRelation.builder()
                .customerFrom(customer1)
                .customerTo(customer2)
                .createTime(now)
                .remark("夫妻")
                .build();

        CustomerRelation customerRelation2 = CustomerRelation.builder()
                .customerFrom(customer1)
                .customerTo(customer3)
                .createTime(now)
                .remark("朋友")
                .build();
        customerRelationRepository.save(customerRelation1);
        customerRelationRepository.save(customerRelation2);

    }

    @Test
    public void update(){
        long id = 1;
        Customer customerNode = customerRepository.findNodeById(id);
        customerNode.setName("jack");
        customerNode.setAge(18);
        customerRepository.save(customerNode);
    }

    @Test
    public void delete(){
        customerRepository.deleteAll();
        customerRelationRepository.deleteAll();
    }

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void person() {
        personRepository.deleteAll();

        Person jack = new Person("jack");
        Person rose = new Person("rose");
        Person tom = new Person("tom");
        Person jone = new Person("jone");

        jack = personRepository.save(jack);
        rose = personRepository.save(rose);
        tom = personRepository.save(tom);
        jone = personRepository.save(jone);

        String ship1 = "朋友";
        tom.linkWith(ship1, jack);
        tom.linkWith(ship1, jone);
        personRepository.save(tom);

        String ship2 = "夫妻";
        jack.linkWith(ship2, rose);
        personRepository.save(jack);

        String ship3 = "战友";
        jone.linkWith(ship3, jack);
        personRepository.save(jone);

        List<Person> family = personRepository.findByFamilyMemberName(jack.getName());
        family.forEach(person -> System.out.println("\t" + person));
    }
}
