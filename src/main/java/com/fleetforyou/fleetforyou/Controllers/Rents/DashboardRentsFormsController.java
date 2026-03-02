package com.fleetforyou.fleetforyou.Controllers.Rents;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.FormService.IFormCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.RentalService.IRentalCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Models.Form;
import com.fleetforyou.fleetforyou.Domain.Models.Rental;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class DashboardRentsFormsController {
    @FXML
    AnchorPane anchorPane;

    @FXML
    TableView<Form> formTV;
    @FXML
    TableColumn<Form, String> formIdTC,
            satisfactionTC,
            observationTC,
            rentalIdTC;

    private UserLoggedDTO userLoggedDTO;

    private IFormCRUDService formCRUDService = InjectorProvider.getFormCRUDService();

    ObservableList<Form> formModelObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        initTable();
    }

    public void initTable() {
        formModelObservableList.setAll(formCRUDService.getAllForms());

        formIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId_form())));
        satisfactionTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSatisfaction())));
        observationTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getObservation())));
        rentalIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getRental().getId_rental())));

        formTV.getItems().setAll(formModelObservableList);
    }
}
