package com.fleetforyou.fleetforyou.Controllers.Vehicles;

import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Vehicle.VehicleCreateDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.*;
import com.fleetforyou.fleetforyou.Domain.DTO.Vehicle.VehicleDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Cars;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Enums.Fuel;
import com.fleetforyou.fleetforyou.Domain.Enums.Segments;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.*;
import com.fleetforyou.fleetforyou.Infrastructure.Connection.DBConnection;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardVehiclesController {

    @FXML
    Button btn_EditarCarro,
            btn_EliminarCarro,
            btn_VerTodosCarros,
            btn_AddCarro;

    @FXML
    Text vehicleCount;
    @FXML
    AnchorPane anchorPane;

    private LoaderFXML loaderFXML;
    private UserLoggedDTO userLoggedDTO;

    private final IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();

    @FXML
    public void initialize() {
        // Initialize LoaderFXML instance
        loaderFXML = new LoaderFXML();
        userLoggedDTO = Session.getUser();

        List<Vehicle> vehicles = vehicleCRUDService.getAllVehicles();
        vehicleCount.setText(String.valueOf(vehicles.size()));

        btn_EditarCarro.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_RENTAL_PRICE));
        btn_AddCarro.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_VEHICLES));
        btn_EliminarCarro.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_VEHICLES));

        if(userLoggedDTO.permissions().contains(Permission.GOD_ADMIN)){
            btn_EditarCarro.setDisable(false);
            btn_AddCarro.setDisable(false);
            btn_EliminarCarro.setDisable(false);
        }
    }

    @FXML
    void onStageAddCarroButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Vehicles/Dashboard_Vehicles_Create.fxml", "Dashboard - Vehicles");
    }

    @FXML
    void onStageVerTodosCarrosButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Vehicles/Dashboard_Vehicles_Read.fxml", "Dashboard - Vehicles");
    }

    @FXML
    void onStageEditarCarroButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Vehicles/Dashboard_Vehicles_Update.fxml", "Dashboard - Vehicles");
    }

    @FXML
    void onStageEliminarCarroButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Vehicles/Dashboard_Vehicles_Delete.fxml", "Dashboard - Vehicles");
    }

    @FXML
    void onStageExtra1ButtonClicked(MouseEvent event) {

    }

    @FXML
    void onStageExtra2ButtonClicked(MouseEvent event) {

    }


}

