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
        User user1 = new User();
        user1.setName("Hau Huynh");
        user1.setEmail("hauhuynh66@gmail.com");
        user1.setPassword(utils.passwordEncoder().encode("Hauhuynh"));
        user1.setRole("ADMIN");
        user1.setDisabled(false);
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Lap Ton");
        user2.setEmail("lapton11@gmail.com");
        user2.setPassword(utils.passwordEncoder().encode("Lapton"));
        user2.setRole("USER");
        user2.setDisabled(false);
        userRepository.save(user2);
    }
}
