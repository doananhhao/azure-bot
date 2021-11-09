package com.lexisnexis.risk.bot.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.lexisnexis.risk.bot.dao")
@ComponentScan(basePackages = {
        "com.lexisnexis.risk.bot.service"})
public class MongoConfig {
}