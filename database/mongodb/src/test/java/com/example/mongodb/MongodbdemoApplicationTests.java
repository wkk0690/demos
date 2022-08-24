package com.example.mongodb;

import com.example.mongodb.dao.PersonDao;
import com.example.mongodb.entity.Address;
import com.example.mongodb.entity.Person;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbdemoApplicationTests {

    @Autowired
    private PersonDao personDAO;

    @Test
    public void find(){
        List<Person> jack = personDAO.queryPagePersonList(1, 10);
        System.out.println(jack.toString());
    }

    @Test
    public void save(){
        Address address = Address.builder()
                .city("郑州")
                .street("中州大道")
                .zip("111")
                .build();
        Person person = Person.builder()
                .name("jack")
                .age(12)
                .address(address)
                .build();
        person.setAge(14);
        Person savePerson = personDAO.savePerson(person);
        System.out.println(savePerson.toString());
    }

    @Test
    public void update(){
        Person person = new Person();
        person.setId(new ObjectId("63043494c1201d206ced96a6"));
        person.setAge(11);
        UpdateResult updateResult = personDAO.update(person);
        System.out.println(updateResult);
    }

    @Test
    public void delete(){
        DeleteResult deleteResult = personDAO.deleteById("5e3ceef9bd9df8383c5ff5d7");
        System.out.println(deleteResult);
    }

}
