package com.carpediem.skeleton.config;

import com.carpediem.skeleton.dao.entity.User;
import com.carpediem.skeleton.dao.repository.UserRepository;
import com.carpediem.skeleton.model.enumaration.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("!prod")
@RequiredArgsConstructor
public class UserDataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {

        final List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Bob", "Martin", "uncle_bob", "uncle_bob@gmail.com", LocalDate.of(1952, 9, 15), StatusEnum.ACTIVE));
        userList.add(new User(2L, "Kent", "Beck", "kent_beck", "kent_beck@gmail.com", LocalDate.of(1961, 5, 28), StatusEnum.ACTIVE));
        userList.add(new User(3L, "Martin", "Fowler", "martin_fowler", "martin_fowler@gmail.com", LocalDate.of(1963, 3, 11), StatusEnum.PASSIVE));
        userList.add(new User(4L, "Linus", "Torvalds", "linus_torvalds", "linus_torvalds@gmail.com", LocalDate.of(1969, 10, 2), StatusEnum.ACTIVE));

        userRepository.saveAll(userList);
    }
}
