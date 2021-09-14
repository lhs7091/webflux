package com.lhs.chatapp.chatapp.controller;

import com.lhs.chatapp.chatapp.domain.Chat;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Collation;

@Configuration
public class InitConfig {

    @Bean
    public CommandLineRunner commandLineRunner(ReactiveMongoOperations reactiveMongoOperations){
        return (String... args)->{
            // collection 지울때
            reactiveMongoOperations.dropCollection("chat").subscribe();
            Thread.sleep(2000);
            // collection 만들때
            reactiveMongoOperations.createCollection(Chat.class, CollectionOptions.just(Collation.simple()).capped().size(50000).maxDocuments(20)).subscribe();
        };


    }
}
