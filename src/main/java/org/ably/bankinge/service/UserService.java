package org.ably.bankinge.service;


import org.ably.bankinge.domain.dto.UserDTO;
import org.ably.bankinge.domain.entities.User;
import org.ably.bankinge.domain.request.UserRequest;
import org.ably.bankinge.mapper.UserMapper;
import org.ably.bankinge.repository.UserRepository;

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


    public void delete(Long id) {userRepository.deleteById(id);}
}