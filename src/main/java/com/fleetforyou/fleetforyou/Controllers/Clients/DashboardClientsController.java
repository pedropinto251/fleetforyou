package com.fleetforyou.fleetforyou.Controllers.Clients;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.Client.ClientCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Client.ClientDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Districts;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
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

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardClientsController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button btn_VerTodosClientes,
            btn_AddCliente,
            btn_EditarCliente,
            btn_EliminarCliente;

    private LoaderFXML loaderFXML;
    private UserLoggedDTO userLoggedDTO;

    private IClientCRUDService clientCRUDService = InjectorProvider.getClientCRUDService();

    @FXML
    public void initialize() {
        // Initialize LoaderFXML instance
        loaderFXML = new LoaderFXML();
        userLoggedDTO = Session.getUser();

        btn_AddCliente.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_CLIENTS));
        btn_EditarCliente.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_CLIENTS));
        btn_EliminarCliente.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_CLIENTS));
        btn_VerTodosClientes.setDisable(!userLoggedDTO.permissions().contains(Permission.MANAGE_CLIENTS));

        if (userLoggedDTO.permissions().contains(Permission.GOD_ADMIN)){
            btn_EliminarCliente.setDisable(false);
            btn_AddCliente.setDisable(false);
            btn_EditarCliente.setDisable(false);
            btn_VerTodosClientes.setDisable(false);
        }
    }

    @FXML
    void onStageAddClientesButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Clients/Dashboard_Clients_Create.fxml", "Dashboard - Clients");
    }

    @FXML
    void onStageVerClientesButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Clients/Dashboard_Clients_Read.fxml", "Dashboard - Clients");
    }

    @FXML
    void onStageEditarClientesButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Clients/Dashboard_Clients_Update.fxml", "Dashboard - Clients");
    }

    @FXML
    void onStageEliminarClientesButtonClicked(MouseEvent event) {
        loaderFXML.loadDynamic(anchorPane, "Clients/Dashboard_Clients_Delete.fxml", "Dashboard - Clients");
    }

    @FXML
    void onStageExtra1ButtonClicked(MouseEvent event) {

    }

    @FXML
    void onStageExtra2ButtonClicked(MouseEvent event) {

    }
}
