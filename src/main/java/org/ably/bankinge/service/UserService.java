package org.ably.bankinge.service;

import org.ably.bankinge.domain.entities.User;
import org.ably.bankinge.domain.request.UserRequest;
import org.ably.bankinge.mapper.UserMapper;
import org.ably.bankinge.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService  {

    private final UserRepository  userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }




    public List<User> findAll() {
        return  userRepository.findAll();
    }




    public Optional<User> findById(Long id) {
        return userRepository.findById(id) ;
    }


    public User save(UserRequest request) {
        User user = userMapper.toEntity(request);
        return   userRepository.save(user);
    }





    public void delete(Long id) {userRepository.deleteById(id);}
}