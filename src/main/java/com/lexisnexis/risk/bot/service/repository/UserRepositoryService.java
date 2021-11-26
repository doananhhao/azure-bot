package com.lexisnexis.risk.bot.service.repository;

import com.lexisnexis.risk.bot.dao.UserRepository;
import com.lexisnexis.risk.bot.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryService extends BaseRepositoryService<User, String, UserRepository> {
}
