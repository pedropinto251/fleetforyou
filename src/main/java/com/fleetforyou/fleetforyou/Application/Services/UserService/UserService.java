package com.fleetforyou.fleetforyou.Application.Services.UserService;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoginDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserRegisterDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IUserDAO;
import com.fleetforyou.fleetforyou.Domain.Models.User;

import java.util.List;
import java.util.Optional;

public final class UserService implements IUserCRUDService, IUserLoginService {
    private final IUserDAO userDAO;

    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public List<User> getAllSameDistrict(String district){
        return userDAO.getAllSameDistrict(district);
    }
    @Override
    public Response registerUser(UserRegisterDTO userRegisterDTO) {

        User user = new User();

        user.setName(userRegisterDTO.name());
        user.setEmail(userRegisterDTO.email());
        user.setPassword(userRegisterDTO.password());
        user.setPermissions(userRegisterDTO.permissions());
        user.setStand(userRegisterDTO.stand());

        user.setPhone_number(userRegisterDTO.phone_number());
        user.setAddress(userRegisterDTO.address());
        user.setPostal_code(userRegisterDTO.postal_code());
        user.setLocal(userRegisterDTO.local());
        user.setDistrict(userRegisterDTO.district());

        user.setDeleted(false);

        Insert state = userDAO.insert(user);

        return new Response(state.isInserted() ? Responses.User.Create.CREATED_SUCCESS : Responses.User.Create.CREATED_FAILED);
    }

    @Override
    public Response getUserById(int id) {
        Optional<User> userOptional = userDAO.getById(id);

        if (userOptional.isEmpty()) {
            return new Response(Responses.User.Find.NOT_FOUND);
        }

        User user = userOptional.get();

        return new Response(Responses.User.Find.FOUND, user);
    }

    @Override
    public Response updateUser(UserDTO userDTO) {
        Optional<User> userOptional = userDAO.getById(userDTO.id_user());

        if (userOptional.isEmpty()) {
            return new Response(Responses.User.Find.NOT_FOUND);
        }

        var user = userOptional.get();

        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setPermissions(userDTO.permissions());
        user.setStand(userDTO.stand());

        user.setPhone_number(userDTO.phone_number());
        user.setAddress(userDTO.address());
        user.setPostal_code(userDTO.postal_code());
        user.setLocal(userDTO.local());
        user.setDistrict(userDTO.district());

        user.setDeleted(userDTO.deleted());

        boolean state = userDAO.update(user);

        return new Response(state ? Responses.User.Update.UPDATED_SUCCESS : Responses.User.Update.UPDATE_FAILED);
    }

    @Override
    public Response removeUser(int user_id) {
        Optional<User> userOptional = userDAO.getById(user_id);

        if (userOptional.isEmpty()) {
            return new Response(Responses.User.Find.NOT_FOUND);
        }

        boolean state = userDAO.delete(user_id);

        return new Response(state ? Responses.User.Delete.DELETED_SUCCESS : Responses.User.Delete.DELETED_FAILED);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    @Override
    public Response loginUser(final UserLoginDTO userLoginDTO) {
        Optional<User> userOptional = userDAO.loginUser(userLoginDTO);

        if (userOptional.isEmpty()) {
            return new Response(Responses.Login.LOGIN_FAILED);
        }

        var user = userOptional.get();

        UserLoggedDTO userLoggedDTO = new UserLoggedDTO(user.getId_user(), user.getName(), user.getEmail(), user.getPermissions());//cria um objeto UserLoggedDTO com os dados do user logado

        Response loginResponse = this.loginStates(userLoginDTO, user, userLoggedDTO);

        if (loginResponse != null) {
            return loginResponse;
        }

        return new Response(Responses.Login.LOGIN_FAILED);
    }

    @Override
    public Response existsByEmail(String email) {
        if (userDAO.existsByEmail(email)) {
            return new Response(Responses.User.Email.EMAIL_EXISTS);
        }
        return new Response(Responses.User.Email.EMAIL_NOT_EXISTS);
    }

    private Response loginStates(UserLoginDTO userLoginDTO, User user, UserLoggedDTO userLoggedDTO) {
        if (user.getPassword().equals(userLoginDTO.password())) {//verifica se a password esta correta
            return new Response(Responses.Login.LOGIN_SUCCESS, userLoggedDTO);
        }
        return null;
    }
}
