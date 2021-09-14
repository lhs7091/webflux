package com.lhs.chatapp.chatapp;

import com.mongodb.ClientSessionOptions;
import com.mongodb.ServerAddress;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.reactivestreams.client.*;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class ChatappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatappApplication.class, args);
    }

}
