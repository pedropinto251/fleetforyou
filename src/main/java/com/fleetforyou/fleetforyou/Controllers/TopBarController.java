package com.fleetforyou.fleetforyou.Controllers;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Utils.LoaderFXML;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Collections;

public class TopBarController {
    @FXML

    Button btn_home,
            btn_carros,
            btn_stands,
            btn_clientes,
            btn_Rents,
            btn_extras,
            btn_off;

    @FXML
    AnchorPane anchorPane;

    private UserLoggedDTO userLoggedDTO;
    private LoaderFXML loaderFXML;

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();
        // Initialize LoaderFXML instance
        loaderFXML = new LoaderFXML();
        loaderFXML.loadDynamic(anchorPane,"Home.fxml", "Home");

        btn_Rents.setDisable(true);
        btn_carros.setDisable(true);
        btn_clientes.setDisable(true);
        btn_extras.setDisable(true);

        // OPERATOR PERMISSIONS
        if (btn_clientes.isDisabled()) {
            btn_clientes.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_CLIENTS));
        }
        if (btn_carros.isDisabled()) {
            btn_carros.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_VEHICLES));
        }
        if (btn_Rents.isDisabled()) {
            btn_Rents.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_RENTAL_PRICE));
        }

        // SELLER PERMISSIONS
        if (btn_Rents.isDisabled()) {
            btn_Rents.setDisable(!userLoggedDTO.permissions().contains(Permission.CREATE_RENTAL));
        }

        // MANAGER PERMISSIONS
        if (btn_extras.isDisabled()) {
            btn_extras.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_EMPLOYEES));
        }
        if (btn_Rents.isDisabled()) {
            btn_Rents.setDisable(!userLoggedDTO.permissions().contains(Permission.VIEW_RENTAL_HISTORY));
        }

        // GOD ADMIN PERMISSIONS
        if (userLoggedDTO.permissions().contains(Permission.GOD_ADMIN)) {
            btn_clientes.setDisable(false);
            btn_carros.setDisable(false);
            btn_Rents.setDisable(false);
            btn_extras.setDisable(false);
            // TODO: Employee Tab
        }
    }

    @FXML
    public void onStageHomeButtonClicked(MouseEvent mouseEvent) {
        loaderFXML.loadDynamic(anchorPane,"Home.fxml", "Home");
    }

    @FXML
    public void onStageCarrosButtonClicked(MouseEvent mouseEvent) {
        loaderFXML.loadDynamic(anchorPane,"Vehicles/Dashboard_Vehicles.fxml", "Dashboard - Vehicles");
    }

    public void onStageStandsButtonClicked(MouseEvent mouseEvent) {
        loaderFXML.loadDynamic(anchorPane,"Stands/Dashboard_Stands.fxml", "Dashboard - Stands");
    }

    public void onStageClientesButtonClicked(MouseEvent mouseEvent) {
        loaderFXML.loadDynamic(anchorPane, "Clients/Dashboard_Clients.fxml", "Dashboard - Clients");
    }

    public void onStageRentsButtonClicked(MouseEvent mouseEvent) {
        loaderFXML.loadDynamic(anchorPane, "Rents/Dashboard_Rents.fxml", "Dashboard - Rents");
    }

    public void onStageExtrasButtonClicked(MouseEvent mouseEvent) {
        loaderFXML.loadDynamic(anchorPane, "Extras/Dashboard_Extras.fxml", "Dashboard - Extras");
    }

    public void onOffButtonClicked(MouseEvent mouseEvent) {
        Session.logOut();
        loaderFXML.loadLogin((Stage) btn_off.getScene().getWindow());
    }
}
