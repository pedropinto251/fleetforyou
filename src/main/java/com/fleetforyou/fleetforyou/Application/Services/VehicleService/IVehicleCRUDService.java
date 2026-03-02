package com.fleetforyou.fleetforyou.Application.Services.VehicleService;

import com.fleetforyou.fleetforyou.Domain.DTO.Vehicle.VehicleCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Vehicle.VehicleDTO;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;

import java.util.List;

public interface IVehicleCRUDService {
    List<Vehicle> getAllVehicles();

    Response createVehicle(VehicleCreateDTO vehicleDTO);

    Response getVehicleById(int id);

    Response updateVehicle(VehicleDTO vehicleDTO);

     Response removeVehicle(int vehicle_id);

    Response existsByRegistration(String registration);
}
