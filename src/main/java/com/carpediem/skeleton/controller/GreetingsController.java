package com.carpediem.skeleton.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GreetingsController {

    @GetMapping("/greetings/{name}")
    public String greetings(@PathVariable @NonNull String name) {
        log.info("Greetings request arrived for the value {}", name);

        return "Greetings ".concat(name.toUpperCase()).concat("!");
    }
}
