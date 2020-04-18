package com.ityoudream.service;

import com.ityoudream.dto.UserDTO;

public interface RedisService {

    void createUser(UserDTO userDto);

    void updateUser(UserDTO userDto);

    UserDTO selectUserById(Integer id);
}
