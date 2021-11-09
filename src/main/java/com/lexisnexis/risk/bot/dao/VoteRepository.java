package com.lexisnexis.risk.bot.dao;

import com.lexisnexis.risk.bot.model.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VoteRepository extends MongoRepository<Vote, String> {

    public List<Vote> findByKudoId(String kudoId);

}