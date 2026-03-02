package com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces;

import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Rental;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;

import java.util.List;
import java.util.Optional;

public interface IRentalDAO {
    List<Rental> getAll();

    Optional<Rental> getById(int id);

    Insert insert(Rental data);

    boolean update(Rental data);

    boolean delete(int id);

    Optional<Rental> getByClientId(int id);

    Optional<Rental> getByVehicleId(int id);

    Optional<Rental> hasRentalClient(int id);

    Optional<Rental> hasRentalVehicle(int id);

    Optional<List<Client>> getAllNoRentalClient();

    Optional<List<Vehicle>> getAllNoRentalVehicle();
}
