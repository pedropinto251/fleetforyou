package com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces;

import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;

import java.util.List;
import java.util.Optional;

public interface IClientDAO {

    List<Client> getAll();

    Optional<Client> getById(int id);

    Insert insert(Client data);

    boolean update(Client data);

    boolean delete(int id);

    boolean existsByNif(String nif);

}
