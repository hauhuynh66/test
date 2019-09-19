package com.house.util;

import com.house.model.User;
import com.house.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLines implements CommandLineRunner {
    @Autowired
    private Utils utils;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("Hau Huynh");
        user.setEmail("hauhuynh66@gmail.com");
        user.setPassword(utils.passwordEncoder().encode("Hauhuynh"));
        user.setRole("USER");
        user.setDisabled(false);
        userRepository.save(user);
    }
}
