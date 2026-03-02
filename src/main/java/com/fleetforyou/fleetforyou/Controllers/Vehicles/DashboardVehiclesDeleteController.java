package com.fleetforyou.fleetforyou.Controllers.Vehicles;

import com.fleetforyou.fleetforyou.Application.Services.RentalService.IRentalCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Rental;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.CheckBoxTableCell;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class DashboardVehiclesDeleteController {
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
    TableColumn<Vehicle, Boolean> deletedTC ;

    private UserLoggedDTO userLoggedDTO;

    private final IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();
    private IRentalCRUDService rentalCRUDService = InjectorProvider.getRentalCRUDService();

    ObservableList<Vehicle> vehicleModelObservableList = FXCollections.observableArrayList();
    private void showWarningAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        initTable();
    }

    @FXML
    public void onRemoveVehicleBTNClicked(MouseEvent mouseEvent) {
        Vehicle selecionado = vehicleTV.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            showAlert(Alert.AlertType.ERROR, "Veículo não selecionado", "Selecione um veículo para remover.");
            return;
        }

        // Verifica se o veículo possui aluguers ativos
        boolean hasActiveRentals = rentalCRUDService.hasRentalVehicle(selecionado.getId_vehicle());

        if (hasActiveRentals) {
            showWarningAlert("Não é possível remover o veículo",
                    "O veículo selecionado tem aluguers ativos e não pode ser removido.");
            return;
        }

        // Remove o veículo
        Response response = vehicleCRUDService.removeVehicle(selecionado.getId_vehicle());
        if (response.getStatus().equals(Responses.Vehicle.Delete.DELETED_SUCCESS)) {
            vehicleModelObservableList.remove(selecionado);
            vehicleTV.refresh();
            showAlert(Alert.AlertType.INFORMATION, "Veículo removido",
                    "O veículo selecionado foi removido com sucesso.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro ao remover veículo",
                    "Ocorreu um erro ao remover o veículo. Por favor, tente novamente mais tarde.");
        }

        initTable();
    }


    public void RetrocederClicar(MouseEvent mouseEvent) {
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
}
