package com.airbnb.airbnb.service;

import com.airbnb.airbnb.dto.UserPatchDto;
import com.airbnb.airbnb.dto.UserPostDto;
import com.airbnb.airbnb.entity.User;
import com.airbnb.airbnb.exception.BusinessLogicException;
import com.airbnb.airbnb.exception.ExceptionCode;
import com.airbnb.airbnb.mapper.UserMapper;
import com.airbnb.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserPostDto userPostDto) {
        User user = userMapper.toUser(userPostDto);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void updateUser(UserPatchDto userPatchDto, Long id) {
        User findUser = findVerifiedUser(id);
        Optional.ofNullable(userPatchDto.getEmail())
                .ifPresent(email -> findUser.setEmail(email));
        Optional.ofNullable(userPatchDto.getPassword())
                .ifPresent(password -> findUser.setPassword(password));
    }

    @Transactional
    public User findVerifiedUser(Long id) {
        return userRepository.findById(id)
                 .orElseThrow(()-> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }


    @Transactional
    public void removeUser(Long id) {
        userRepository.delete(findVerifiedUser(id));
    }

}
