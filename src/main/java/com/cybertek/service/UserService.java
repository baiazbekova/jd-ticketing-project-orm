package com.cybertek.service;

import com.cybertek.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username); //this is not a derived query, its DTO
    void save (UserDTO dto);
    UserDTO update(UserDTO dto);
    void delete(String username);
}
