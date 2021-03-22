package com.cybertek.service;

import com.cybertek.dto.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {

    List <RoleDTO> listAllRoles();
    RoleDTO findById(Long id);

}
