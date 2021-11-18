package com.lexisnexis.risk.bot.service.noname;

import com.lexisnexis.risk.bot.base.BaseServiceImpl;
import com.lexisnexis.risk.bot.dao.UserRepository;
import com.lexisnexis.risk.bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return this.userRepository;
    }
}
