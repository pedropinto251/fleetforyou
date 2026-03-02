package com.fleetforyou.fleetforyou.Application.Services.StandService;

import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandDTO;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;

import java.util.List;

public interface IStandCRUDService {
    List<Stand> getAllStands();

    List<Stand> getAllSameDistrict(String district);

    Response createStand(StandCreateDTO standDTO);

    Response getStandById(int id);

    Response updateStand(StandDTO standDTO);

    Response removeStand(int stand_id);

    boolean hasUserStand(int stand_id);

    Response existsByName(String name);
}
