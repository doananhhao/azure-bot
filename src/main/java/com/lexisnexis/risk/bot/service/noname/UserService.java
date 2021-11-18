package com.lexisnexis.risk.bot.service.noname;

import com.lexisnexis.risk.bot.dao.UserRepository;
import com.lexisnexis.risk.bot.model.User;

public interface UserService {
    User saveUser(User user);
}
