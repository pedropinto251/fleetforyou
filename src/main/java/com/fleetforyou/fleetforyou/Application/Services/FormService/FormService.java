package com.fleetforyou.fleetforyou.Application.Services.FormService;

import com.fleetforyou.fleetforyou.Application.Services.FormService.IFormCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.Client.ClientCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Client.ClientDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Form.FormCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Form;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IClientDAO;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IFormDAO;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IStandDAO;

import java.util.List;
import java.util.Optional;

public final class FormService implements IFormCRUDService {
    private final IFormDAO formDAO;

    public FormService(IFormDAO formDAO) {
        this.formDAO = formDAO;
    }

    @Override
    public List<Form> getAllForms(){
        return formDAO.getAll();
    }

    @Override
    public Response createForm(FormCreateDTO formCreateDTO) {
        Form form = new Form();

        form.setSatisfaction(formCreateDTO.satisfaction());
        form.setObservation(formCreateDTO.observation());
        form.setRental(formCreateDTO.rental());

        Insert state = formDAO.insert(form);

        return new Response(state.isInserted() ? Responses.Form.Create.CREATED_SUCCESS : Responses.Form.Create.CREATED_FAILED);
    }

    @Override
    public Response getFormById(int id) {
        Optional<Form> formOptional = formDAO.getById(id);

        if (formOptional.isEmpty()) {
            return new Response(Responses.Form.Find.NOT_FOUND);
        }

        Form form = formOptional.get();

        return new Response(Responses.Form.Find.FOUND, form);
    }
}
