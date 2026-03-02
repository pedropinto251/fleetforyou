package com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces;

import com.fleetforyou.fleetforyou.Domain.Models.Form;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;

import java.util.List;
import java.util.Optional;

public interface IFormDAO {

    List<Form> getAll();

    Optional<Form> getById(int id);

    Insert insert(Form data);
}
