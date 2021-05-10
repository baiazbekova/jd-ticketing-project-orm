package com.cybertek.implementation;

import com.cybertek.dto.UserDTO;
import com.cybertek.entity.User;
import com.cybertek.mapper.MapperUtil;
import com.cybertek.mapper.RoleMapper;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.RoleRepository;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> list = userRepository.findAll(Sort.by("firstName"));
        return list.stream().map(obj-> mapperUtil.convert(obj, new UserDTO())).collect(Collectors.toList());

    }

    @Override
    public UserDTO findByUserName(String username) {

        User user = userRepository.findByUsername(username);
        return mapperUtil.convert(user, new UserDTO());
    }

    @Override
    public void save(UserDTO dto) {

        User obj = mapperUtil.convert(dto, new User());
        userRepository.save(obj);

    }

    @Override
    public UserDTO update(UserDTO dto) {

        //Find current user
        User user = userRepository.findByUsername(dto.getUsername());
        //Map update user dto to entity object
        User convertedUser = mapperUtil.convert(dto, new User());
        //because dto user does not have ID, i can set id from userId
        convertedUser.setId(user.getId());
        //save updated user
        userRepository.save(convertedUser);

        return findByUserName(dto.getUsername());
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUsername(username);
        user.setIsDeleted(true);
        userRepository.save(user);

    }


    //hard deleting
    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> users = userRepository.finidAllByRoleDescriptionIgnoreCase(role);
        return users.stream().map(obj-> {return UserMapper.convertToDto(obj);}).collect(Collectors.toList());


    }


}
