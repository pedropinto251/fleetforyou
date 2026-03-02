package com.fleetforyou.fleetforyou.Controllers.Rents;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.FormService.IFormCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.RentalService.IRentalCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.Rental.RentalCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class DashboardRentsCreateController {
    @FXML
    ChoiceBox <String> vehicleStatusCB,
        clientCB,
        vehicleCB;

    @FXML
    AnchorPane anchorPane;
    @FXML
    DatePicker dateStartDP,
            dateEndDP;
    @FXML
    TextField clientTF,
            vehicleTF;
    @FXML
    private TextField idUserValue;
    @FXML
    private TextField userNameValue;
    private UserLoggedDTO userLoggedDTO;

    private IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();
    private IClientCRUDService clientCRUDService = InjectorProvider.getClientCRUDService();
    private IUserCRUDService userCRUDService = InjectorProvider.getUserCRUDService();
    private IRentalCRUDService rentalCRUDService = InjectorProvider.getRentalCRUDService();


    public void updateChoiceBoxes(){
        Response listClient = rentalCRUDService.getAllNoRentalClient();
        if (listClient.getStatus().equals(Responses.Client.Find.FOUND)) {
            Optional<List<Client>> optionalClient = (Optional<List<Client>>) listClient.getData();
            ObservableList<Client> successfulListClient = FXCollections.observableArrayList(optionalClient.get());

            ObservableList<String> filteredClients = successfulListClient.stream()
                    .map(client -> client.getId() + " - " + client.getName())
                    .distinct()
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            clientCB.setItems(filteredClients);
        }

        Response listVehicle = rentalCRUDService.getAllNoRentalVehicle();
        if (listVehicle.getStatus().equals(Responses.Vehicle.Find.FOUND)) {
            Optional<List<Vehicle>> optionalVehicle = (Optional<List<Vehicle>>) listVehicle.getData();
            ObservableList<Vehicle> successfulListVehicle = FXCollections.observableArrayList(optionalVehicle.get());

            ObservableList<String> filteredVehicles = successfulListVehicle.stream()
                    .map(vehicle -> vehicle.getId_vehicle() + " - " + vehicle.getRegistration())
                    .distinct()
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            vehicleCB.setItems(filteredVehicles);
        }
    }

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        if (userLoggedDTO != null) {
            idUserValue.setText(String.valueOf(userLoggedDTO.id_user()));
            userNameValue.setText(userLoggedDTO.name());
        }
        ObservableList<String> estados = FXCollections.observableArrayList("Muito bom", "Bom", "Satisfatório", "Mau");
        vehicleStatusCB.setItems(estados);

        updateChoiceBoxes();
    }

    public void onAddRentalButtonClicked(MouseEvent mouseEvent) {
        if (dateStartDP.getValue() == null) {
            Alert alertdataInicio = new Alert(Alert.AlertType.ERROR, "Campo Data de Inicio sem dados");
            alertdataInicio.showAndWait();
            return;
        }
        if (dateEndDP.getValue() == null) {
            Alert alertdataFim = new Alert(Alert.AlertType.ERROR, "Campo Data de Fim sem dados");
            alertdataFim.showAndWait();
            return;
        }
        if (clientCB.getValue() == null) {
            Alert alertCliente = new Alert(Alert.AlertType.ERROR, "Campo Cliente sem dados");
            alertCliente.showAndWait();
            return;
        }
        if (vehicleCB.getValue() == null) {
            Alert alertvehicle = new Alert(Alert.AlertType.ERROR, "Campo Veiculo sem dados");
            alertvehicle.showAndWait();
            return;
        }
        if (vehicleStatusCB.getValue() == null) {
            Alert alertvehicle = new Alert(Alert.AlertType.ERROR, "Estado de veículo sem dados");
            alertvehicle.showAndWait();
            return;
        }

        if (dateStartDP.getValue().isAfter(dateEndDP.getValue()) || dateStartDP.getValue().isEqual(dateEndDP.getValue())) {
            Alert alertdate = new Alert(Alert.AlertType.ERROR, "Data de inicio não pode ser depois ou igual à data do fim");
            alertdate.showAndWait();
            return;
        }

        Response clientResponse = clientCRUDService.getClientById(Integer.parseInt(clientCB.getValue().split(" - ")[0]));

        Client clientFinal = null;

        if (clientResponse.getStatus().equals(Responses.Client.Find.FOUND)) {
            clientFinal = (Client) clientResponse.getData();
        }

        Response vehicleResponse = vehicleCRUDService.getVehicleById(Integer.parseInt(vehicleCB.getValue().split(" - ")[0]));

        Vehicle vehicleFinal = null;

        if (vehicleResponse.getStatus().equals(Responses.Vehicle.Find.FOUND)) {
            vehicleFinal = (Vehicle) vehicleResponse.getData();
        }

        Response userResponse = userCRUDService.getUserById(userLoggedDTO.id_user());// fazer busca do user

        User userFinal = null;

        if (userResponse.getStatus().equals(Responses.User.Find.FOUND)) { // verificar se na busca o status é encontrado
            userFinal = (User) userResponse.getData();
        }

        Date dateStart = Date.valueOf(dateStartDP.getValue());
        Date dateEnd = Date.valueOf(dateEndDP.getValue());

        if ((vehicleFinal != null) && (clientFinal != null) && (userFinal != null)) {
            if (rentalCRUDService.hasRentalClient(clientFinal.getId())) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Este cliente já possui um aluguer.");
                alert1.setHeaderText("Este cliente já possui um aluguer.");
                alert1.showAndWait();
                return;
            }
            if (rentalCRUDService.hasRentalVehicle(vehicleFinal.getId_vehicle())) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Este veículo já possui um aluguer.");
                alert1.setHeaderText("Este veículo já possui um aluguer.");
                alert1.showAndWait();
                return;
            }


            long numberOfDays = calculateDifference(dateStart, dateEnd);


            if (numberOfDays < 0) {
                Alert alertdate = new Alert(Alert.AlertType.ERROR, "Data de início não pode ser depois da data do fim");
                alertdate.showAndWait();
                return;
            }

            double rentalPrice = vehicleFinal.getRental_price();
            double totalPrice = numberOfDays * rentalPrice;

            RentalCreateDTO rentalCreateDTO = new RentalCreateDTO(dateStart,
                    dateEnd,
                    null,
                    vehicleFinal,
                    clientFinal,
                    userFinal,
                    vehicleStatusCB.getValue(),
                    rentalPrice

            );

            Response rentalresponse = rentalCRUDService.createRenal(rentalCreateDTO);
            if (rentalresponse.getStatus().equals(Responses.Rental.Create.CREATED_SUCCESS)) {
                updateChoiceBoxes();
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Aluguer criado com sucesso.\n" +
                        "Duração do aluguer: " + numberOfDays + " dias.\n" +
                        "Preço total: " + totalPrice);
                alert1.setHeaderText("Aluguer criado com sucesso");
                alert1.showAndWait();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao criar o Aluguer");
                alert1.setHeaderText("Ocorreu um erro ao criar o Aluguer");
                alert1.showAndWait();
            }
        }else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION,"Cliente ou Veiculo não existem");
            alert1.showAndWait();
        }

    }
    private long calculateDifference(Date start, Date end) {
        long difference = end.getTime() - start.getTime();
        return difference / (24 * 60 * 60 * 1000);
    }
}
