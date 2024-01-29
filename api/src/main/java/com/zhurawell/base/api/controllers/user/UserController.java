package com.zhurawell.base.api.controllers.user;

import com.zhurawell.base.api.converters.UserRestConverter;
import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.mappers.user.UserMapper;
import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * REST controller to manage data about users.
 * @author dimazhuravlyov
 * */
@Slf4j
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAuthority('user:manager') or hasAuthority('sysadm')")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        user.setRegistrationDate(new Date());
        return ResponseEntity.ok(userMapper.entityToDto(userService.createUser(userMapper.dtoToEntity(user))));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userMapper.entityToDto(userService.updateUser(userMapper.dtoToEntity(user))));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sysadm')")
    public ResponseEntity<User> deleteUser(@PathVariable("id") BigInteger id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") BigInteger id) {
        return ResponseEntity.ok(userMapper.entityToDto(userService.findById(id)));
    }

    @GetMapping("/getByFirstName")
    public ResponseEntity<List<UserDto>> findAllByFirstName(@RequestParam("firstName") String name) {
        return ResponseEntity.ok(userMapper.entityListToDtoList(userService.findAllByFirstName(name)));
    }

    /**
     * @see  UserRestConverter
     * */
    @GetMapping("/get/new/")
    public ResponseEntity<UserDto> getUserNew(UserDto user) {
        return ResponseEntity.ok(userMapper.entityToDto(userService.findByLogin(user.getLogin())));
    }

    @GetMapping("/getAllActiveFrom/{date}")
    public ResponseEntity<List<UserDto>> getUser(@PathVariable("date") Long activeFrom) {
        return ResponseEntity.ok(userMapper.entityListToDtoList(
                userService.findByRegistrationDateAfter(new Date(activeFrom))));
    }
}
