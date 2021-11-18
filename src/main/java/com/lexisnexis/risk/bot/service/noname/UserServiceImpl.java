package com.lexisnexis.risk.bot.service.noname;

import com.lexisnexis.risk.bot.dao.UserRepository;
import com.lexisnexis.risk.bot.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

//    private boolean checkIfUserExisting(String skypeId) {
//        Optional<User> user = userRepository.findById(skypeId);
//        if (user.isPresent()) {
//            return true;
//        }
//        return false;
//    }

}
