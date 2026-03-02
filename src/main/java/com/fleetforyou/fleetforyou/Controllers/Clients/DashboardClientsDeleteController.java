package com.fleetforyou.fleetforyou.Controllers.Clients;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.RentalService.IRentalCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class DashboardClientsDeleteController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Client> clientTV;
    @FXML
    private TableColumn<Client, String> clientIdTC,
            ncTC,
            nameTC,
            phoneNumberTC,
            addressTC,
            postalCodeTC,
            localTC,
            districtTC;
    @FXML
    private TableColumn<Client, Boolean> deletedTC;
    @FXML
    private Button removeBTN;

    private UserLoggedDTO userLoggedDTO;

    // Inicializa diretamente na declaração para evitar nullPointerException
    private IClientCRUDService clientCRUDService = InjectorProvider.getClientCRUDService();
    private IRentalCRUDService rentalCRUDService = InjectorProvider.getRentalCRUDService();

    private ObservableList<Client> clientModelObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();
        initTable();
    }

    private void initTable() {
        clientModelObservableList.setAll(clientCRUDService.getAllClients());

        clientIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        ncTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNc())));
        nameTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getName())));
        phoneNumberTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPhone_number())));
        addressTC.setCellValueFactory((cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAddress()))));
        postalCodeTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPostalCode())));
        localTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getLocal())));
        districtTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDistrict())));
        deletedTC.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isDeleted()));
        deletedTC.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Client, Boolean> call(TableColumn<Client, Boolean> param) {
                return new CheckBoxTableCell<>();
            }
        });

        clientTV.setItems(clientModelObservableList);
    }

    @FXML
    public void onRemoveBTNClicked(MouseEvent mouseEvent) {
        Client selecionado = clientTV.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            showAlert(Alert.AlertType.ERROR, "Cliente não selecionado", "Selecione um cliente para remover.");
            return;
        }

        // Verifica se o cliente possui aluguers ativos
        boolean hasActiveRentals = rentalCRUDService.hasRentalClient(selecionado.getId());

        if (hasActiveRentals) {
            showWarningAlert("Não é possível remover o cliente",
                    "O cliente selecionado tem alugueres ativos e não pode ser removido.");
            return;
        }

        // Se o cliente não tiver aluguers ativos, prossegue com a remoção
        Response response = clientCRUDService.removeClient(selecionado.getId());

        if (response.getStatus().equals(Responses.User.Delete.DELETED_SUCCESS)) {
            clientModelObservableList.remove(selecionado);
            clientTV.refresh();
            showAlert(Alert.AlertType.INFORMATION, "Cliente removido",
                    "O cliente selecionado foi removido com sucesso.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro ao remover cliente",
                    "Ocorreu um erro ao remover o cliente. Por favor, tente novamente mais tarde.");
        }

        initTable();
    }
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
}



