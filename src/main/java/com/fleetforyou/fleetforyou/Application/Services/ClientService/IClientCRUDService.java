package com.fleetforyou.fleetforyou.Application.Services.ClientService;

import com.fleetforyou.fleetforyou.Domain.DTO.Client.ClientCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Client.ClientDTO;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;

import java.util.List;

public interface IClientCRUDService {
    List<Client> getAllClients();

    Response createClient(ClientCreateDTO clientDTO);

    Response getClientById(int id);

    Response updateClient(ClientDTO clientDTO);

    Response removeClient(int client_id);
    Response existsByNif(String nif);

}
