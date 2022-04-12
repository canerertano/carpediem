package com.carpediem.skeleton.service

import com.carpediem.skeleton.dao.entity.User
import com.carpediem.skeleton.dao.repository.UserRepository
import com.carpediem.skeleton.exception.InvalidRequestException
import com.carpediem.skeleton.exception.ResourceNotFoundException
import com.carpediem.skeleton.mapper.UserMapper
import com.carpediem.skeleton.mapper.UserMapperImpl
import com.carpediem.skeleton.model.dto.UserDto
import com.carpediem.skeleton.model.enumaration.StatusEnum
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title
import java.time.LocalDate

@Subject(UserService)
@Title("Unit tests for user service")
class UserServiceSpec extends Specification {

    UserRepository repository
    UserMapper mapper
    UserService service

    def setup() {
        repository = Stub(UserRepository.class)
        mapper = new UserMapperImpl()
        service = new UserService(repository, mapper)
    }

    def "get all users with given page param"() {
        given: "a list of users"
        final List<User> userList = new ArrayList<>()
        userList.add(new User(1L, "Bob", "Martin", "uncle_bob", "uncle_bob@gmail.com", LocalDate.of(1952, 9, 15), StatusEnum.ACTIVE))
        userList.add(new User(2L, "Kent", "Beck", "kent_beck", "kent_beck@gmail.com", LocalDate.of(1961, 5, 28), StatusEnum.PASSIVE))

        and: "an instance of user repository returns this user list as pageable object"
        Pageable pageable = PageRequest.of(0, 2)
        Page<User> userPage = new PageImpl<>(userList, pageable, userList.size())
        repository.findAll(pageable) >> userPage

        when: "request for users"
        Page<UserDto> userDtoPage = service.getUsers(pageable, null)
        List<UserDto> resultList = userDtoPage.asCollection()

        then: "check if list size correct"
        resultList.size() == 2
    }

    def "get all users with given page and status param as active"() {
        given: "a list of active users"
        final List<User> userList = new ArrayList<>()
        userList.add(new User(1L, "Bob", "Martin", "uncle_bob", "uncle_bob@gmail.com", LocalDate.of(1952, 9, 15), StatusEnum.ACTIVE))

        and: "an instance of user repository returns this user list as pageable object"
        Pageable pageable = PageRequest.of(0, 2)
        Page<User> userPage = new PageImpl<>(userList, pageable, userList.size())
        repository.findAllByStatus(pageable, StatusEnum.ACTIVE) >> userPage

        when: "request for active users"
        Page<UserDto> userDtoPage = service.getUsers(pageable, "ACTIVE")
        List<UserDto> resultList = userDtoPage.asCollection()

        then: "check if list size correct"
        resultList.size() == 1
    }

    def "request for users with wrong status value and exception"() {
        given: "a page request"
        Pageable pageable = PageRequest.of(0, 2)

        when: "request for users with wrong status value"
        service.getUsers(pageable, "IN_PROGRESS")

        then: "get InvalidRequestException"
        thrown(InvalidRequestException)
    }

    def "get the user with the given id"() {
        given: "a user object"
        def user = new User()
        user.setId(123L)
        user.setUserName("uncle_bob")

        and: "an instance of user repository returns this user object"
        repository.findById(123L) >> Optional.of(user)

        when: "request for existing user"
        UserDto userDto = service.getUserById(123L)

        then: "check if name matches"
        userDto.getUserName() == "uncle_bob"
    }

    def "request a non existing user and get ResourceNotFoundException"() {
        given: "an instance of user repository returns empty optional value"
        repository.findById(123L) >> Optional.empty()

        when: "request for non existing id"
        service.getUserById(123L)

        then: "get exception"
        thrown(ResourceNotFoundException)
    }

    def "get user by an id and check mapper if it maps correctly"() {
        given: "an user object"
        def userEntity = new User(123L, "Robert", "Martin", "uncle_bob",
                "uncle_bob@gmail.com", LocalDate.of(1952, 9, 15), StatusEnum.ACTIVE)

        and: "an instance of user repository returns this user object"
        repository.findById(123L) >> Optional.of(userEntity)

        when: "request for existing user"
        UserDto userDto = service.getUserById(123L)

        then: "check if all properties match"
        matchUserEntityAndUserDto(userDto, userEntity)
    }

    void matchUserEntityAndUserDto(UserDto userDto, User userEntity) {
        assert userDto.getId() == userEntity.getId()
        assert userDto.getFirstName() == userEntity.getFirstName()
        assert userDto.getLastName() == userEntity.getLastName()
        assert userDto.getUserName() == userEntity.getUserName()
        assert userDto.getEmail() == userEntity.getEmail()
        assert userDto.getBirthDate() == userEntity.getBirthDate()
        assert userDto.getStatus() == userEntity.getStatus()
    }
}
