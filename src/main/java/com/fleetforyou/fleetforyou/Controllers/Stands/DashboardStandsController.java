package com.fleetforyou.fleetforyou.Controllers.Stands;

import com.fleetforyou.fleetforyou.Application.Services.FormService.IFormCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserLoginService;
import com.fleetforyou.fleetforyou.Domain.DTO.Client.ClientDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserRegisterDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Cars;
import com.fleetforyou.fleetforyou.Domain.Enums.Districts;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
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
import javafx.util.Callback;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardStandsController {
    @FXML
    Button btn_AddStand,
            btn_EdtStand,
            btn_EliminarStand,
            btn_VerTodosStands;
    @FXML
    AnchorPane anchorPane;

    private LoaderFXML loaderFXML;
    private UserLoggedDTO userLoggedDTO;

    private final IStandCRUDService standCRUDService = InjectorProvider.getStandCRUDService();

    @FXML
    public void initialize() {
        // Initialize LoaderFXML instance
        loaderFXML = new LoaderFXML();
        userLoggedDTO = Session.getUser();
    }

    @FXML
    void onStageAddStandButtonClicked(MouseEvent event) {

        List<Stand> allStands = standCRUDService.getAllStands();

        if (allStands.size() >= 20) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Não pode adicionar outro Stand, pois está no limite.");
            alert.showAndWait();
            return;
        } else {

            loaderFXML.loadDynamic(anchorPane, "Stands/Dashboard_Stands_Create.fxml", "Dashboard - Stands");

        }
    }

    @FXML
    void onStageVerTodosStandsButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Stands/Dashboard_Stands_Read.fxml", "Dashboard - Stands");
    }

    @FXML
    void onStageEditarStandButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Stands/Dashboard_Stands_Update.fxml", "Dashboard - Stands");
    }

    @FXML
    void onStageEliminarStandButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Stands/Dashboard_Stands_Delete.fxml", "Dashboard - Stands");
    }

    @FXML
    void onStageExtra1ButtonClicked(MouseEvent event) {

    }
}
