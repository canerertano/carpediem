package com.carpediem.skeleton.controller

import com.carpediem.skeleton.exception.ExceptionHandlerControllerAdvice
import com.carpediem.skeleton.exception.ResourceNotFoundException
import com.carpediem.skeleton.model.dto.UserDto
import com.carpediem.skeleton.model.enumaration.StatusEnum
import com.carpediem.skeleton.service.UserService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class UserControllerSpec extends Specification {

    private MockMvc mockMvc
    private UserController userController
    private UserService userService

    void setup() {
        userService = Mock()
        userController = new UserController(userService)
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(ExceptionHandlerControllerAdvice).build()
    }

    def "get the user with the given id"() {
        given: "an user object"
        UserDto userDto = new UserDto(1L, "Bob", "Martin", "uncle_bob",
                "uncle_bob@gmail.com", LocalDate.of(1952, 9, 15), StatusEnum.ACTIVE)

        and: "an instance of userService returns this user object"
        userService.getUserById(1L) >> userDto

        when: "mock request sent to the api endpoint"
        ResultActions response = mockMvc.perform(get("/api/users/{id}", 1L))

        then: "retrieve the user object with given id from mock service"
        response.andExpect(status().isOk())
        response.andExpect(jsonPath('$.firstName').value("Bob"))
        response.andExpect(jsonPath('$.lastName').value("Martin"))
        response.andExpect(jsonPath('$.userName').value("uncle_bob"))
    }

    def "get http 404 not found status code with requesting non existing user"() {

        given: "an instance of userService throws ResourceNotFoundException"
        userService.getUserById(1L) >> { throw new ResourceNotFoundException("") }

        when: "mock request sent to the api endpoint"
        ResultActions response = mockMvc.perform(get("/api/users/{id}", 1L))

        then: "retrieve the ResponseEntity object with 404 not found status"
        response.andExpect(status().isNotFound())
    }
}
