package com.fleetforyou.fleetforyou.Application.Services.RentalService;

import com.fleetforyou.fleetforyou.Domain.DTO.Rental.RentalCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Rental.RentalDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Rental;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IClientDAO;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IRentalDAO;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IVehicleDAO;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RentalService implements IRentalCRUDService{
    private final IRentalDAO rentalDAO;

    public RentalService(IRentalDAO rentalDAO) {
        this.rentalDAO = rentalDAO;
    }

    @Override
    public List<Rental> getAllRentals(){
        return rentalDAO.getAll();}
    @Override
    public Response createRenal(RentalCreateDTO rentalCreateDTO) {
        Rental rental = new Rental();

        rental.setClient(rentalCreateDTO.client());
        rental.setDate_end(rentalCreateDTO.date_end());
        rental.setUser(rentalCreateDTO.user());
        rental.setVehicle(rentalCreateDTO.vehicle());
        rental.setDate_start(rentalCreateDTO.date_start());
        rental.setDate_return(rentalCreateDTO.date_return());
        rental.setVehicleStatus(rentalCreateDTO.vehicle_status());

        Insert state = rentalDAO.insert(rental);

        return new Response(state.isInserted() ? Responses.Rental.Create.CREATED_SUCCESS : Responses.Rental.Create.CREATED_FAILED);
    }

    @Override
    public Response getRentalById(int id) {
        Optional<Rental> rentalOptional = rentalDAO.getById(id);

        if (rentalOptional.isEmpty()) {
            return new Response(Responses.Rental.Find.NOT_FOUND);
        }

        Rental rental = rentalOptional.get();

        return new Response(Responses.Rental.Find.FOUND, rental);
    }

    @Override
    public Response updateRental(RentalDTO rentalDTO) {
        Optional<Rental> rentalOptional = rentalDAO.getById(rentalDTO.id_rental());

        if (rentalOptional.isEmpty()) {
            return new Response(Responses.Rental.Find.NOT_FOUND);
        }

        var rental = rentalOptional.get();

        rental.setDate_start(rentalDTO.date_start());
        rental.setDate_end(rentalDTO.date_end());
        rental.setDate_return(rentalDTO.date_return());
        rental.setVehicle(rentalDTO.vehicle());
        rental.setClient(rentalDTO.client());
        rental.setUser(rentalDTO.user());
        rental.setVehicleStatus(rentalDTO.vehicle_status());

        boolean state = rentalDAO.update(rental);

        return new Response(state ? Responses.Rental.Update.UPDATED_SUCCESS : Responses.Rental.Update.UPDATE_FAILED);
    }

    @Override
    public Response removeRental(int id_rental) {
        Optional<Rental> rentalOptional = rentalDAO.getById(id_rental);

        if (rentalOptional.isEmpty()) {
            return new Response(Responses.Rental.Find.NOT_FOUND);
        }

        boolean state = rentalDAO.delete(id_rental);

        return new Response(state ? Responses.Rental.Delete.DELETED_SUCCESS : Responses.Rental.Delete.DELETED_FAILED);
    }

    @Override
    public boolean hasRentalClient(int id_client){
        Optional<Rental> rentalOptional = rentalDAO.hasRentalClient(id_client);

        if (rentalOptional.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean hasRentalVehicle(int id_vehicle){
        Optional<Rental> rentalOptional = rentalDAO.hasRentalVehicle(id_vehicle);

        if (rentalOptional.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public Response getAllNoRentalClient() {
        Optional<List<Client>> listClient = rentalDAO.getAllNoRentalClient();

        if (listClient.isEmpty()) {
            return new Response(Responses.Client.Find.NOT_FOUND);
        }
        return new Response(Responses.Client.Find.FOUND, listClient);
    }

    @Override
    public Response getAllNoRentalVehicle() {
        Optional<List<Vehicle>> listVehicle = rentalDAO.getAllNoRentalVehicle();

        if (listVehicle.isEmpty()) {
            return new Response(Responses.Vehicle.Find.NOT_FOUND);
        }
        return new Response(Responses.Vehicle.Find.FOUND, listVehicle);
    }
}

