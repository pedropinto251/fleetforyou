package com.fleetforyou.fleetforyou.Controllers.Vehicles;

import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class DashboardVehiclesReadController {
    @FXML
    AnchorPane anchorPane;

    @FXML
    TableView<Vehicle> vehicleTV;
    @FXML
    TableColumn<Vehicle, String> vehicleIdTC,
                                    registrationTC,
                                    brandTC,
                                    modelTC,
                                    segmentTC,
                                    cilinTC,
                                    statusTC,
                                    typeTC,
                                    fuelTC,
                                    yearBuiltTC,
                                    doorsTC,
                                    kmTC,
                                    potencyTC,
                                    colorTC,
                                    priceTC,
                                    selling_priceTC,
                                    soldTC;
    @FXML
    TableColumn<Vehicle, Boolean> deletedTC;

    private UserLoggedDTO userLoggedDTO;

    private final IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();

    ObservableList<Vehicle> vehicleModelObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        initTable();
    }

    public void initTable() {
        vehicleModelObservableList.setAll(vehicleCRUDService.getAllVehicles());

        vehicleIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId_vehicle())));
        registrationTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getRegistration())));
        brandTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getBrand())));
        modelTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getModel())));
        segmentTC.setCellValueFactory((cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSegment()))));
        cilinTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEngine_capacity())));
        statusTC.setCellValueFactory(cellData -> {
            return (cellData.getValue().isStatus() ? new SimpleStringProperty("Usado") : new SimpleStringProperty("Novo"));
        });
        typeTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getType())));
        fuelTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFuel())));
        yearBuiltTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getYear_built())));
        doorsTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNum_doors())));
        kmTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNum_km())));
        potencyTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPotency())));
        colorTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getColor())));
        priceTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getRental_price())));
        deletedTC.setCellValueFactory(cellData -> (cellData.getValue().isDeleted() ? new SimpleBooleanProperty(true) : new SimpleBooleanProperty(false)));
        selling_priceTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSelling_price())));
        soldTC.setCellValueFactory(cellData -> {
                    return (cellData.getValue().isSold() ? new SimpleStringProperty("Sim") : new SimpleStringProperty("Não"));
                });
        deletedTC.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Vehicle, Boolean> call(TableColumn<Vehicle, Boolean> param) {
                return new CheckBoxTableCell<Vehicle>();
            }
        });

        vehicleTV.getItems().setAll(vehicleModelObservableList);
    }


    public void RetrocederClicar(MouseEvent mouseEvent) {

    }
}
