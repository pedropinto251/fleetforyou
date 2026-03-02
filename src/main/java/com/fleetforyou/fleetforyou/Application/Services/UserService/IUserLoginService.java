package com.fleetforyou.fleetforyou.Application.Services.UserService;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoginDTO;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;

sealed public interface IUserLoginService permits UserService{
    Response loginUser(final UserLoginDTO userLoginDTO);
    Response existsByEmail(String email);
}
