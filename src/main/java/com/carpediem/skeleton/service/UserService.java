package com.carpediem.skeleton.service;

import com.carpediem.skeleton.dao.entity.User;
import com.carpediem.skeleton.dao.repository.UserRepository;
import com.carpediem.skeleton.exception.InvalidRequestException;
import com.carpediem.skeleton.exception.ResourceNotFoundException;
import com.carpediem.skeleton.mapper.UserMapper;
import com.carpediem.skeleton.model.dto.UserDto;
import com.carpediem.skeleton.model.enumaration.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Page<UserDto> getAllUsers(Pageable pageable) {
        final Page<User> userPage = userRepository.findAll(pageable);

        final List<UserDto> userDtoList = userMapper.mapToDtoList(userPage.getContent());

        return new PageImpl<>(userDtoList, pageable, userPage.getTotalElements());
    }

    public List<UserDto> getAllActiveUsers(String status) {
        StatusEnum enumValue = StatusEnum.fromValue(status);

        if (Objects.isNull(enumValue)) {
            throw new InvalidRequestException(String.format("Given status is not valid : %s", status));
        }

        final List<User> activeUserList = userRepository.findAllByStatus(enumValue);

        return userMapper.mapToDtoList(activeUserList);
    }

    public UserDto getUserById(Long id) {
        final Optional<User> userOpt = userRepository.findById(id);

        final User user = userOpt.orElseThrow(() ->
                new ResourceNotFoundException(String.format("User not found with the id : %s", id)));

        return userMapper.mapToDto(user);
    }
}
