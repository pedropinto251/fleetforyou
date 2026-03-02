package com.fleetforyou.fleetforyou.Controllers;
import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.LoaderFXML;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.List;

public class HomeController {
    @FXML
    AnchorPane root_home;
    @FXML
    Label welcomeLabel,
            userLabel;
    @FXML
    Text vehicleCount,
        clientCount,
        standCount;

    private UserLoggedDTO userLoggedDTO;

    private final IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();
    private final IClientCRUDService clientCRUDService = InjectorProvider.getClientCRUDService();
    private final IStandCRUDService standCRUDService = InjectorProvider.getStandCRUDService();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();
        welcomeLabel.setText("Bem Vindo, caro(a) " + userLoggedDTO.name());

        List<Vehicle> vehicles = vehicleCRUDService.getAllVehicles();
        vehicleCount.setText(String.valueOf(vehicles.size()));

        List<Client> clients = clientCRUDService.getAllClients();
        clientCount.setText(String.valueOf(clients.size()));

        List<Stand> stands = standCRUDService.getAllStands();
        standCount.setText(String.valueOf(stands.size()));

        userLabel.setText(userLoggedDTO.name() +  " - "  + userLoggedDTO.id_user());
    }
}
