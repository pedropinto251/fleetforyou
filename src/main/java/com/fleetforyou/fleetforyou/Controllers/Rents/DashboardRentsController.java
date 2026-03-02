package com.fleetforyou.fleetforyou.Controllers.Rents;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.FormService.IFormCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.RentalService.IRentalCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.Form.FormCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Rental.RentalCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Rental.RentalDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.*;
import com.fleetforyou.fleetforyou.Domain.Utils.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.lang.annotation.Repeatable;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardRentsController {
    @FXML
    Button btn_AlterarAluguer,
            btn_FazerAluguer,
            btn_VerTodosAlugueres,
            btn_Forms;
    @FXML
    AnchorPane anchorPane;

    private LoaderFXML loaderFXML;

    private UserLoggedDTO userLoggedDTO;

    @FXML
    public void initialize() {
        // Initialize LoaderFXML instance
        loaderFXML = new LoaderFXML();
        userLoggedDTO = Session.getUser();

        btn_FazerAluguer.setDisable(!userLoggedDTO.permissions().contains(Permission.CREATE_RENTAL));
        btn_AlterarAluguer.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_RENTAL_PRICE));
        btn_VerTodosAlugueres.setDisable(!userLoggedDTO.permissions().contains(Permission.VIEW_RENTAL_HISTORY));
        btn_Forms.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_STATISTICS));

        if(userLoggedDTO.permissions().contains(Permission.GOD_ADMIN)){
            btn_FazerAluguer.setDisable(false);
            btn_AlterarAluguer.setDisable(false);
            btn_VerTodosAlugueres.setDisable(false);
            btn_Forms.setDisable(false);
        }
    }

    @FXML
    void onStageFazerAluguerButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Rents/Dashboard_Rents_Create.fxml", "Dashboard - Rents");
    }

    @FXML
    void onStageVerTodosAlugueresButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Rents/Dashboard_Rents_Read.fxml", "Dashboard - Rents");
    }

    @FXML
    void onStageAlterarAluguerButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Rents/Dashboard_Rents_Update.fxml", "Dashboard - Rents");
    }

    @FXML
    void onStageFormsButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Rents/Dashboard_Rents_Forms.fxml", "Dashboard - Forms");
    }
}
