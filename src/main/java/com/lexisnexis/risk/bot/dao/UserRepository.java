package com.lexisnexis.risk.bot.dao;

import com.lexisnexis.risk.bot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findBySkypeId(String skypeId);

}
