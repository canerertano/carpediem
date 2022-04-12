package com.carpediem.skeleton.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class IntegrationMockMvcSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    def "user can be retrieved from database by requesting with mock mvc call"() {
        when: "request sent to the api endpoint"
        ResultActions response = mockMvc.perform(get("/api/users/{id}", 1L))

        then: "retrieve the user object with given id from database"
        response.andExpect(status().isOk())
        response.andExpect(jsonPath('$.firstName').value("Bob"))
        response.andExpect(jsonPath('$.lastName').value("Martin"))
        response.andExpect(jsonPath('$.userName').value("uncle_bob"))
    }
}