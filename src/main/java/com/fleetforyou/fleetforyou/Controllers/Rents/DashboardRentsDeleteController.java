package com.fleetforyou.fleetforyou.Controllers.Rents;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.FormService.IFormCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.RentalService.IRentalCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Rental;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DashboardRentsDeleteController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    TableView<Rental> rentalTV;
    @FXML
    TableColumn<Rental, String> rentalIdTC,
            dateStartTC,
            dateEndTC,
            dateReturnTC,
            vehicleIdTC,
            clientIdTC,
            userIdTC;

    private UserLoggedDTO userLoggedDTO;

    private IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();
    private IClientCRUDService clientCRUDService = InjectorProvider.getClientCRUDService();
    private IUserCRUDService userCRUDService = InjectorProvider.getUserCRUDService();
    private IRentalCRUDService rentalCRUDService = InjectorProvider.getRentalCRUDService();
    private IFormCRUDService formCRUDService = InjectorProvider.getFormCRUDService();

    ObservableList<Rental> rentalModelObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        initTable();
    }

    public void onRemoveRentalButtonClicked(MouseEvent mouseEvent) {
        Rental selecionado = rentalTV.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            rentalCRUDService.removeRental(selecionado.getId_rental());

            initTable();
        }
    }


    public void initTable() {
        rentalModelObservableList.setAll(rentalCRUDService.getAllRentals());

        rentalIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId_rental())));
        dateStartTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDate_start())));
        dateEndTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDate_end())));
        dateReturnTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDate_return())));
        vehicleIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVehicle().getId_vehicle())));
        clientIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getClient().getId())));
        userIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUser().getId_user())));

        rentalTV.getItems().setAll(rentalModelObservableList);
    }
}
