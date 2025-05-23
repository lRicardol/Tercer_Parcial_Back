package com.cvds.parcial.back_parcial_3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClients;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
@EnableMongoRepositories(basePackages = "edu.eci.cvds.elysium.repository")
public class MongoConfig {

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String mongoURI = dotenv.get("MONGODB_URI");

        if (mongoURI == null || mongoURI.isEmpty()) {
            mongoURI = System.getenv("MONGODB_URI");
        }

        if (mongoURI == null || mongoURI.isEmpty()) {
            System.out.println("⚠️ ADVERTENCIA: No se encontró MONGODB_URI en el entorno!");
            mongoURI = "mongodb://localhost:27017/elysium";
        } else {
            System.out.println("✅ MONGODB_URI encontrado!");
        }

        String dbName = dotenv.get("MONGODB_DATABASE");

        if (dbName == null || dbName.isEmpty()) {
            dbName = System.getenv("MONGODB_DATABASE");
        }

        if (dbName == null || dbName.isEmpty()) {
            String environment = System.getenv("APP_ENVIRONMENT");
            if ("production".equals(environment)) {
                dbName = "ReservaECILimbo";
            } else {
                dbName = "ReservaECIHades";
            }
            System.out.println("⚠️ Usando base de datos por defecto según entorno: " + dbName);
        } else {
            System.out.println("✅ Nombre de base de datos encontrado en configuración: " + dbName);
        }

        mongoURI = mongoURI.trim();
        if (mongoURI.startsWith("\"") && mongoURI.endsWith("\"")) {
            mongoURI = mongoURI.substring(1, mongoURI.length() - 1);
        }

        System.out.println("Conectando a MongoDB con URI: " +
                mongoURI.replaceAll(":[^:@]+@", ":****@"));
        System.out.println("Usando base de datos: " + dbName);

        return new SimpleMongoClientDatabaseFactory(MongoClients.create(mongoURI), dbName);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }
}