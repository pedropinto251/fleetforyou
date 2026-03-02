package com.fleetforyou.fleetforyou.Controllers.Rents;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.FormService.IFormCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.RentalService.IRentalCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.TypeCars;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Rental;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.CheckBoxTableCell;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardRentsReadController {
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
            userIdTC,
            vehicleStatusTC,
            rentalPriceTC,
            insuranceNameTC,
            insuranceTypeTC,
            insurancePriceTC,
            vehicleTypeTC;


    @FXML
    ChoiceBox<String> vehicleTypeCB;


    private UserLoggedDTO userLoggedDTO;
    @FXML
    TextField clientSearchField, vehicleSearchField;

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

        List<String> vehicleTypes = vehicleCRUDService.getAllVehicles().stream()
                .map(Vehicle::getType)
                .distinct()
                .collect(Collectors.toList());
        vehicleTypeCB.setItems(FXCollections.observableArrayList(vehicleTypes));

        vehicleTypeCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleVehicleTypeSelection(newValue);
        });
    }

    private void handleVehicleTypeSelection(String vehicleType) {
        if (vehicleType != null && !vehicleType.isEmpty()) {
            ObservableList<Rental> filteredList = rentalModelObservableList.stream()
                    .filter(rental -> rental.getVehicle().getType().equals(vehicleType))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            rentalTV.setItems(filteredList);
        } else {
            rentalTV.setItems(rentalModelObservableList);
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
        vehicleStatusTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVehicleStatus())));
        rentalPriceTC.setCellValueFactory(cellData -> {
            long difference = cellData.getValue().getDate_end().getTime() - cellData.getValue().getDate_start().getTime();
            long numberOfDays = difference / (24 * 60 * 60 * 1000);
            double totalPrice = numberOfDays * cellData.getValue().getVehicle().getRental_price();
            return new SimpleStringProperty(String.valueOf(totalPrice));
        });
        vehicleTypeTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVehicle().getType())));
        // insuranceNameTC
        // insuranceTypeTC
        // insurancePriceTC

        rentalTV.getItems().setAll(rentalModelObservableList);
    }

    @FXML
    public void handleSearch() {
        String clientSearchText = clientSearchField.getText().trim().toLowerCase();
        String vehicleSearchText = vehicleSearchField.getText().trim().toLowerCase();

        ObservableList<Rental> filteredList = FXCollections.observableArrayList();
        for (Rental rental : rentalCRUDService.getAllRentals()) {
            boolean matchesClient = rental.getClient().getName().toLowerCase().contains(clientSearchText) ||
                    String.valueOf(rental.getClient().getId()).contains(clientSearchText);
            boolean matchesVehicle = rental.getVehicle().getModel().toLowerCase().contains(vehicleSearchText) ||
                    String.valueOf(rental.getVehicle().getId_vehicle()).contains(vehicleSearchText);

            if (matchesClient && matchesVehicle) {
                filteredList.add(rental);
            }
        }

        rentalTV.setItems(filteredList);
    }


    @FXML
    public void onSearchKeyTyped(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            handleSearch();
        }
    }
}