package com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoginDTO;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IVehicleDAO {
    List<Vehicle> getAll();

    Optional<Vehicle> getById(int id);

    Insert insert(Vehicle data);

    boolean update(Vehicle data);

    boolean delete(int id);

    boolean existsByRegistration(String registration);
}
