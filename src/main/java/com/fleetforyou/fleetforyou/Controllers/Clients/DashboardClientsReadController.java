package com.fleetforyou.fleetforyou.Controllers.Clients;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Utils.CheckBoxTableCell;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class DashboardClientsReadController {
    @FXML
    AnchorPane anchorPane;

    @FXML
    TableView<Client> clientTV;
    @FXML
    TableColumn<Client, String> clientIdTC,
            ncTC,
            nameTC,
            phoneNumberTC,
            addressTC,
            postalCodeTC,
            localTC,
            districtTC;
    @FXML
    TableColumn<Client, Boolean> deletedTC;

    private UserLoggedDTO userLoggedDTO;

    private IClientCRUDService clientCRUDService = InjectorProvider.getClientCRUDService();

    ObservableList<Client> clientModelObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        initTable();
    }

    public void initTable() {
        clientModelObservableList.setAll(clientCRUDService.getAllClients());

        clientIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        ncTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNc())));
        nameTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getName())));
        phoneNumberTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPhone_number())));
        addressTC.setCellValueFactory((cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAddress()))));
        postalCodeTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPostalCode())));
        localTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getLocal())));
        districtTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDistrict())));
        deletedTC.setCellValueFactory(cellData -> (cellData.getValue().isDeleted() ? new SimpleBooleanProperty(true) : new SimpleBooleanProperty(false)));
        deletedTC.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Client, Boolean> call(TableColumn<Client, Boolean> param) {
                return new CheckBoxTableCell<Client>();
            }
        });

        clientTV.getItems().setAll(clientModelObservableList);
    }
}
