package com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces;

import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;

import java.util.List;
import java.util.Optional;

public interface IStandDAO {

    List<Stand> getAll();

    List<Stand> getAllSameDistrict(String district);

    Optional<Stand> getById(int id);

    Insert insert(Stand data);

    boolean update(Stand data);

    boolean delete(int id);

    Optional<User> hasUserStand(int id_stand);

    boolean existsByName(String name);
}
