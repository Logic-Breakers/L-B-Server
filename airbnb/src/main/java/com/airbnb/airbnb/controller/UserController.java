package com.airbnb.airbnb.controller;

import com.airbnb.airbnb.dto.UserPatchDto;
import com.airbnb.airbnb.dto.UserPostDto;
import com.airbnb.airbnb.mapper.UserMapper;
import com.airbnb.airbnb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity postUser(@Valid @RequestBody UserPostDto userPostDto) {
        userService.createUser(userPostDto);
        return new ResponseEntity<>("계정 생성이 완료되었습니다.", HttpStatus.CREATED);
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity patchUser(@Valid @RequestBody UserPatchDto userPatchDto,
                                    @PathVariable ("user-id") Long id) {
        userService.updateUser(userPatchDto, id);
        return new ResponseEntity<>("계정 정보 수정이 완료되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@PathVariable ("user-id") Long id) {
        userService.removeUser(id);
        return new ResponseEntity<>("계정이 삭제되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUser (@PathVariable ("user-id") Long id) {
        return new ResponseEntity<>(userMapper.toUserResponseDto(userService.findVerifiedUser(id)),HttpStatus.OK);
    }

}
