package com.fleetforyou.fleetforyou.Application.Services.FormService;

import com.fleetforyou.fleetforyou.Domain.DTO.Form.FormCreateDTO;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Form;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;

import java.util.List;

public interface IFormCRUDService {
    List<Form> getAllForms();

    Response createForm(FormCreateDTO formCreateDTO);

    Response getFormById(int id);
}