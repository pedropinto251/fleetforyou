package com.fleetforyou.fleetforyou.Application.Services.ClientService;

import com.fleetforyou.fleetforyou.Domain.DTO.Client.ClientCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Client.ClientDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IClientDAO;

import java.util.List;
import java.util.Optional;

public final class ClientService implements IClientCRUDService {
    private final IClientDAO clientDAO;

    public ClientService(IClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public List<Client> getAllClients(){
        return clientDAO.getAll();
    }
    @Override
    public Response createClient(ClientCreateDTO clientCreateDTO) {
        Client client = new Client();

        client.setName(clientCreateDTO.name());
        client.setNc(clientCreateDTO.nc());
        client.setPhone_number(clientCreateDTO.phone_number());
        client.setAddress(clientCreateDTO.address());
        client.setPostalCode(clientCreateDTO.postalCode());
        client.setLocal(clientCreateDTO.local());
        client.setDistrict(clientCreateDTO.district());

        client.setDeleted(false);

        Insert state = clientDAO.insert(client);

        return new Response(state.isInserted() ? Responses.Client.Create.CREATED_SUCCESS : Responses.Client.Create.CREATED_FAILED);
    }

    @Override
    public Response getClientById(int id) {
        Optional<Client> clientOptional = clientDAO.getById(id);

        if (clientOptional.isEmpty()) {
            return new Response(Responses.Client.Find.NOT_FOUND);
        }

        Client client = clientOptional.get();

        return new Response(Responses.Client.Find.FOUND, client);
    }

    @Override
    public Response updateClient(ClientDTO clientDTO) {
        Optional<Client> clientOptional = clientDAO.getById(clientDTO.id());

        if (clientOptional.isEmpty()) {
            return new Response(Responses.Client.Find.NOT_FOUND);
        }

        var client = clientOptional.get();

        client.setName(clientDTO.name());
        client.setNc(clientDTO.nc());
        client.setPhone_number(clientDTO.phone_number());
        client.setAddress(clientDTO.address());
        client.setPostalCode(clientDTO.postalCode());
        client.setLocal(clientDTO.local());
        client.setDistrict(clientDTO.district());
        client.setDeleted(clientDTO.deleted());

        boolean state = clientDAO.update(client);

        return new Response(state ? Responses.Client.Update.UPDATED_SUCCESS : Responses.Client.Update.UPDATE_FAILED);
    }

    @Override
    public Response removeClient(int client_id) {
        Optional<Client> clientOptional = clientDAO.getById(client_id);

        if (clientOptional.isEmpty()) {
            return new Response(Responses.Client.Find.NOT_FOUND);
        }

        boolean state = clientDAO.delete(client_id);

        return new Response(state ? Responses.Client.Delete.DELETED_SUCCESS : Responses.Client.Delete.DELETED_FAILED);
    }
    public Response existsByNif (String nif) {
        if (clientDAO.existsByNif(nif)) {
            return new Response(Responses.Client.Nif.NIF_EXISTS);
        }
        return new Response(Responses.Client.Nif.NIF_NOT_EXISTS);
    }


}
