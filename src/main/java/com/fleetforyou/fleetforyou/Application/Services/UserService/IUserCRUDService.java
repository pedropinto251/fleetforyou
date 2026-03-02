package com.fleetforyou.fleetforyou.Application.Services.UserService;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoginDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserRegisterDTO;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;

import java.util.List;

sealed public interface IUserCRUDService permits UserService {
    List<User> getAllSameDistrict(String district);

    Response registerUser(final UserRegisterDTO userDTO);

    Response getUserById(final int id);

    Response updateUser(final UserDTO userDTO);

    Response removeUser(final int user_id);

    List<User> getAllUsers();
}