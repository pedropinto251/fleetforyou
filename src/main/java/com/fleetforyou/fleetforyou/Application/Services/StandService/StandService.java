package com.fleetforyou.fleetforyou.Application.Services.StandService;

import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IStandDAO;

import java.util.List;
import java.util.Optional;

public final class StandService implements IStandCRUDService {
    private final IStandDAO standDAO;

    public StandService(IStandDAO standDAO) {
        this.standDAO = standDAO;
    }

    @Override
    public List<Stand> getAllStands(){
        return standDAO.getAll();
    }

    @Override
    public List<Stand> getAllSameDistrict(String district){
        return standDAO.getAllSameDistrict(district);
    }

    @Override
    public Response createStand(StandCreateDTO standCreateDTO) {
        Stand stand = new Stand();

        stand.setName(standCreateDTO.name());
        stand.setPhone_number(standCreateDTO.phone_number());
        stand.setAddress(standCreateDTO.address());
        stand.setPostalCode(standCreateDTO.postalCode());
        stand.setLocal(standCreateDTO.local());
        stand.setDistrict(standCreateDTO.district());

        stand.setDeleted(false);

        Insert state = standDAO.insert(stand);

        return new Response(state.isInserted() ? Responses.Stand.Create.CREATED_SUCCESS : Responses.Stand.Create.CREATED_FAILED);
    }

    @Override
    public Response getStandById(int id) {
        Optional<Stand> standOptional = standDAO.getById(id);

        if (standOptional.isEmpty()) {
            return new Response(Responses.Stand.Find.NOT_FOUND);
        }

        Stand stand = standOptional.get();

        return new Response(Responses.Stand.Find.FOUND, stand);
    }

    @Override
    public Response updateStand(StandDTO standDTO) {
        Optional<Stand> standOptional = standDAO.getById(standDTO.id_stand());

        if (standOptional.isEmpty()) {
            return new Response(Responses.Stand.Find.NOT_FOUND);
        }

        var stand = standOptional.get();

        stand.setName(standDTO.name());
        stand.setPhone_number(standDTO.phone_number());
        stand.setAddress(standDTO.address());
        stand.setPostalCode(standDTO.postalCode());
        stand.setLocal(standDTO.local());
        stand.setDistrict(standDTO.district());

        stand.setDeleted(standDTO.deleted());

        boolean state = standDAO.update(stand);

        return new Response(state ? Responses.Stand.Update.UPDATED_SUCCESS : Responses.Stand.Update.UPDATE_FAILED);
    }

    @Override
    public Response removeStand(int stand_id) {
        Optional<Stand> standOptional = standDAO.getById(stand_id);

        if (standOptional.isEmpty()) {
            return new Response(Responses.Stand.Find.NOT_FOUND);
        }

        boolean state = standDAO.delete(stand_id);

        return new Response(state ? Responses.Stand.Delete.DELETED_SUCCESS : Responses.Stand.Delete.DELETED_FAILED);
    }

    @Override
    public boolean hasUserStand(int stand_id) {
        Optional<User> userOptional = standDAO.hasUserStand(stand_id);

        if (userOptional.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public Response existsByName(String name) {
        if (standDAO.existsByName(name)) {
            return new Response(Responses.Stand.Exists.EXISTS);
        }
        return new Response(Responses.Stand.Exists.NOT_EXISTS);
    }
}
