package com.bankapp.server.service;


import com.bankapp.server.domain.dto.UserDTO;
import com.bankapp.server.domain.entities.User;
import com.bankapp.server.domain.request.UserRequest;
import com.bankapp.server.exception.UserNotFoundException;
import com.bankapp.server.mapper.UserMapper;
import com.bankapp.server.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService  {

    private UserRepository userRepository;
    private UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }




    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }


    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }


    public UserDTO save(UserRequest userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }


//    public UserDTO update(Long id, UserRequest userDTO) {
//        return userRepository.findById(id).map(user -> {
//            user.setName(userDTO.getName());
//            user.setAge(userDTO.getAge());
//            user.setMonthlyIncome(userDTO.getMonthlyIncome());
//            user.setCreditScore(userDTO.getCreditScore());
//            user.setRole(userDTO.getRole());
//            User updatedUser = userRepository.save(user);
//            return userMapper.toDTO(updatedUser);
//        }).orElseThrow(() ->new UserNotFoundException());
//    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}