package com.example.mongodb.test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.*;

/**
 * 非 springboot环境 测试 Mongodb crud
 */
public class TestCRUD {

    private static MongoCollection<Document> mongoCollection;

    public static void main(String[] args) {
        init();
        testQueryAll();
//        testQuery();
//        testInsert();
//        testUpdate();
//        testDelete();
    }
    public static void init() {
        // 建立连接
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        // 选择数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("testdb");
        // 选择表
        mongoCollection = mongoDatabase.getCollection("user");
    }

    // 查询数据
    public static void testQueryAll() {
        mongoCollection.find().limit(10).forEach((Consumer<? super Document>) document -> {
            System.out.println(document.toJson());
        });
    }

    // 查询age<=50并且id>=100的用户信息，并且按照id倒序排序，只返回id，age字段，不返回_id字段
    public static void testQuery() {
        mongoCollection.find(and(lte("age", 50), gte("id", 100))).sort(Sorts.descending("id")).projection(Projections.fields(Projections.include("id", "age"), Projections.excludeId())).forEach((Consumer<? super Document>) document -> {
            System.out.println(document.toJson());
        });
    }

    public static void testInsert() {
        Document document = new Document("id", 10001).append("name", "张三").append("age", 30);
        mongoCollection.insertOne(document);
        System.out.println("插入数据成功！");
        mongoCollection.find(eq("id", 10001)).forEach((Consumer<? super Document>) doc -> {
            System.out.println(doc.toJson());
        });
    }

    public static void testUpdate() {
        UpdateResult updateResult = mongoCollection.updateOne(eq("id", 10001), Updates.set("age", 25));
        System.out.println(updateResult);
        mongoCollection.find(eq("id", 10001)).forEach((Consumer<? super Document>) doc -> {
            System.out.println(doc.toJson());
        });
    }

    public static void testDelete() {
        DeleteResult deleteResult = mongoCollection.deleteOne(eq("id", 10001));
        System.out.println(deleteResult);
    }
}
