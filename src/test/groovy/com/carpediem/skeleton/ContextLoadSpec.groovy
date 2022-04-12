package com.carpediem.skeleton

import com.carpediem.skeleton.controller.UserController
import com.carpediem.skeleton.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ContextLoadSpec extends Specification {

    @Autowired
    private UserController userController

    @Autowired
    private UserService userService

    def "when context is loaded then all expected beans are created"() {
        expect: "the user controller is created"
        userController

        and: "user service is created"
        userService
    }
}