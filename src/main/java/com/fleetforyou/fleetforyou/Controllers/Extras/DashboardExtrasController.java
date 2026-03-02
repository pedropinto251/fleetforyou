package com.fleetforyou.fleetforyou.Controllers.Extras;

import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserRegisterDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Districts;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Enums.UserType;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Utils.*;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DashboardExtrasController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button btn_VerTodosUsers,
            btn_AddNovoUser;

    private LoaderFXML loaderFXML;
    private UserLoggedDTO userLoggedDTO;

    @FXML
    public void initialize() {
        // Initialize LoaderFXML instance
        loaderFXML = new LoaderFXML();
        userLoggedDTO = Session.getUser();

        btn_VerTodosUsers.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_EMPLOYEES));
        btn_AddNovoUser.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_EMPLOYEES));

        if (userLoggedDTO.permissions().contains(Permission.GOD_ADMIN)){
            btn_VerTodosUsers.setDisable(false);
            btn_AddNovoUser.setDisable(false);
        }
    }

    @FXML
    void onAddNovoUserButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Extras/Dashboard_Extras_Create.fxml", "Dashboard - Extras");
    }

    @FXML
    void onVerTodosUsersButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Extras/Dashboard_Extras_Read.fxml", "Dashboard - Extras");
    }

    @FXML
    void onStageDisciplinaButtonClicked(MouseEvent event) {

    }

    @FXML
    void onStageOutrosButtonClicked(MouseEvent event) {

    }

    @FXML
    void onStageProjetoButtonClicked(MouseEvent event) {

    }

    @FXML
    void onStageSobreNosButtonClicked(MouseEvent event) {

    }
}
