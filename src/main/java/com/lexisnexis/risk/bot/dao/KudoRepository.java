package com.lexisnexis.risk.bot.dao;

import com.lexisnexis.risk.bot.model.Kudo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KudoRepository extends MongoRepository<Kudo, String> {

    public Kudo findByMonthAndYear(int month, int year);

}
