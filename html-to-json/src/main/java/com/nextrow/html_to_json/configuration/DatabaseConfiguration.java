package com.nextrow.html_to_json.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoClient mongoClient= MongoClients.create();

        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient,"website-data"));
    }

}
