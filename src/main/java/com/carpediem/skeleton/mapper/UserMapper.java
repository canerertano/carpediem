package com.carpediem.skeleton.mapper;

import com.carpediem.skeleton.dao.entity.User;
import com.carpediem.skeleton.model.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(User user);

    List<UserDto> mapToDtoList(List<User> userList);

}
