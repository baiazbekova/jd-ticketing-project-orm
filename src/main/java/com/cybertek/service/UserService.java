package com.cybertek.service;

import com.cybertek.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username); //this is not a derived query, its DTO
    void save (UserDTO dto);
    UserDTO update(UserDTO dto);
    void delete(String username);
}
