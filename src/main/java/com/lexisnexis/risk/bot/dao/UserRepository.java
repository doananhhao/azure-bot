package com.lexisnexis.risk.bot.dao;

import com.lexisnexis.risk.bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
