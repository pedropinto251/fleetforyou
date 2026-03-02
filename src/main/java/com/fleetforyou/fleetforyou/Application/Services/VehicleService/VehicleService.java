package com.fleetforyou.fleetforyou.Application.Services.VehicleService;

import com.fleetforyou.fleetforyou.Domain.DTO.Vehicle.VehicleCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Vehicle.VehicleDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IVehicleDAO;

import java.util.List;
import java.util.Optional;

public class VehicleService implements IVehicleCRUDService{
    private final IVehicleDAO vehicleDAO;

    public VehicleService(IVehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    @Override
    public List<Vehicle> getAllVehicles(){
        return vehicleDAO.getAll();
    }
    @Override
    public Response createVehicle(VehicleCreateDTO vehicleCreateDTO) {
        Vehicle vehicle = new Vehicle();

        vehicle.setBrand(vehicleCreateDTO.brand());
        vehicle.setColor(vehicleCreateDTO.color());
        vehicle.setFuel(vehicleCreateDTO.fuel());
        vehicle.setModel(vehicleCreateDTO.model());
        vehicle.setEngine_capacity(vehicleCreateDTO.engine_capacity());
        vehicle.setNum_doors(vehicleCreateDTO.num_doors());
        vehicle.setNum_km(vehicleCreateDTO.num_km());
        vehicle.setPotency(vehicleCreateDTO.potency());
        vehicle.setRegistration(vehicleCreateDTO.registration());
        vehicle.setSegment(vehicleCreateDTO.segment());
        vehicle.setYear_built(vehicleCreateDTO.year_built());
        vehicle.setType(vehicleCreateDTO.type());
        vehicle.setSelling_price(vehicleCreateDTO.selling_price());
        vehicle.setSold(vehicleCreateDTO.sold());
        vehicle.setStatus(vehicleCreateDTO.num_km() > 0);

        vehicle.setRental_price(vehicleCreateDTO.rental_price());

        vehicle.setDeleted(false);

        Insert state = vehicleDAO.insert(vehicle);

        return new Response(state.isInserted() ? Responses.Vehicle.Create.CREATED_SUCCESS : Responses.Vehicle.Create.CREATED_FAILED);
    }

    @Override
    public Response getVehicleById(int id) {
        Optional<Vehicle> vehicleOptional = vehicleDAO.getById(id);

        if (vehicleOptional.isEmpty()) {
            return new Response(Responses.Vehicle.Find.NOT_FOUND);
        }

        Vehicle vehicle = vehicleOptional.get();

        return new Response(Responses.Vehicle.Find.FOUND, vehicle);
    }

    @Override
    public Response updateVehicle(VehicleDTO vehicleDTO) {
        Optional<Vehicle> vehicleOptional = vehicleDAO.getById(vehicleDTO.id_vehicle());

        if (vehicleOptional.isEmpty()) {
            return new Response(Responses.Vehicle.Find.NOT_FOUND);
        }

        var vehicle = vehicleOptional.get();

        vehicle.setBrand(vehicleDTO.brand());
        vehicle.setColor(vehicleDTO.color());
        vehicle.setFuel(vehicleDTO.fuel());
        vehicle.setModel(vehicleDTO.model());
        vehicle.setEngine_capacity(vehicleDTO.engine_capacity());
        vehicle.setNum_doors(vehicleDTO.num_doors());
        vehicle.setNum_km(vehicleDTO.num_km());
        vehicle.setPotency(vehicleDTO.potency());
        vehicle.setRegistration(vehicleDTO.registration());
        vehicle.setSegment(vehicleDTO.segment());
        vehicle.setYear_built(vehicleDTO.year_built());
        vehicle.setStatus(vehicleDTO.status());

        vehicle.setStatus(vehicleDTO.num_km() > 0);
        vehicle.setRental_price(vehicleDTO.rental_price());
        vehicle.setType(vehicleDTO.type());

        vehicle.setDeleted(vehicleDTO.deleted());

        vehicle.setSelling_price(vehicleDTO.selling_price());
        vehicle.setSold(vehicleDTO.sold());

        boolean state = vehicleDAO.update(vehicle);

        return new Response(state ? Responses.Vehicle.Update.UPDATED_SUCCESS : Responses.Vehicle.Update.UPDATE_FAILED);
    }

    @Override
    public Response removeVehicle(int vehicle_id) {
        Optional<Vehicle> vehicleOptional = vehicleDAO.getById(vehicle_id);

        if (vehicleOptional.isEmpty()) {
            return new Response(Responses.Vehicle.Find.NOT_FOUND);
        }

        boolean state = vehicleDAO.delete(vehicle_id);

        return new Response(state ? Responses.Vehicle.Delete.DELETED_SUCCESS : Responses.Vehicle.Delete.DELETED_FAILED);
    }

    @Override
    public Response existsByRegistration(String registration) {
        if (vehicleDAO.existsByRegistration(registration)) {
            return new Response(Responses.Vehicle.Registration.REGISTRATION_EXISTS);
        }
        return new Response(Responses.Vehicle.Registration.REGISTRATION_NOT_EXISTS);
    }
}
