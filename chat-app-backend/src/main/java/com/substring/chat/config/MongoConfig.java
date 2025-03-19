package com.substring.chat.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
//    private final String mongoUri;

    @Value("${spring.data.mongodb.uri}")  // Fetch URI from application.properties
    private String mongoUri;

//    @Autowired
//    public MongoConfig(Dotenv dotenv) {
//        this.mongoUri = dotenv.get("SPRING_DATA_MONGODB_URI", "mongodb://localhost:27017/chatapp"); // Default value
//    }

    public String getMongoUri() {
        return mongoUri;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }
}
