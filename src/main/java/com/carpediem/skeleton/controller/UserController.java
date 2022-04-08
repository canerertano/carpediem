package com.carpediem.skeleton.controller;

import com.carpediem.skeleton.model.dto.UserDto;
import com.carpediem.skeleton.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Retrieve all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all users"),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @Operation(summary = "Retrieve all users by status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all users by status"),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllActiveUsers(@RequestParam(defaultValue = "ACTIVE") String status) {
        return userService.getAllActiveUsers(status);
    }

    @Operation(summary = "Retrieve user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the user"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getUserById(@PathVariable @NotNull Long id) {
        return userService.getUserById(id);
    }
}
