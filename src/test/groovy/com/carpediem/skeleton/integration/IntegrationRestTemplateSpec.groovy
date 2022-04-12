package com.carpediem.skeleton.integration

import com.carpediem.skeleton.model.dto.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationRestTemplateSpec extends Specification {

    @LocalServerPort
    private int port

    @Autowired
    private TestRestTemplate restTemplate

    def "user can be retrieved from database by requesting with rest call"() {
        given: "url of the api endpoint with parameter"
        String url = String.format("http://localhost:%d/skeletonapp/api/users/%d", port, 1L)

        when: "request sent to the api endpoint"
        UserDto userDto = restTemplate.getForObject(url, UserDto.class)

        then: "retrieve the user object with given id from database"
        assert userDto != null
        assert userDto.getFirstName() == "Bob"
        assert userDto.getLastName() == "Martin"
        assert userDto.getUserName() == "uncle_bob"
    }
}