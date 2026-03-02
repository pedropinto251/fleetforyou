package com.fleetforyou.fleetforyou.Application.Services.RentalService;

import com.fleetforyou.fleetforyou.Domain.DTO.Rental.RentalCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Rental.RentalDTO;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Rental;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;

import java.util.List;

public interface IRentalCRUDService {

    List<Rental> getAllRentals();

    Response createRenal(RentalCreateDTO rentalCreateDTO);

    Response getRentalById(int id);

    Response updateRental(RentalDTO rentalDTO);

    Response removeRental(int id_rental);

    boolean hasRentalClient(int id_client);

    boolean hasRentalVehicle(int id_vehicle);

    Response getAllNoRentalClient();

    Response getAllNoRentalVehicle();
}
