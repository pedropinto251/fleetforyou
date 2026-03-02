package com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoginDTO;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUserDAO {
    List<User> getAll();

    List<User> getAllExceptDeleted();

    Optional<User> getById(int id);

    Insert insert(User data);

    boolean update(User data);

    boolean delete(int id);

    Optional<User> loginUser(UserLoginDTO data);

    boolean existsByEmail(String email);

    List<User> getAllSameDistrict(String district);
}
