package com.fleetforyou.fleetforyou.Controllers.Extras;

import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.User;
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

public class DashboardExtrasReadController {
    @FXML
    AnchorPane anchorPane;

    @FXML
    TableView<User> userTV;
    @FXML
    TableColumn<User, String> userIdTC,
            nameTC,
            standTC,
            standPhoneNumberTC,
            postalCodeTC,
            localTC,
            districtTC,
            emailTC;
    @FXML
    TableColumn<User, Boolean> deletedTC;

    private UserLoggedDTO userLoggedDTO;

    private final IUserCRUDService userCRUDService = InjectorProvider.getUserCRUDService();
    private final IStandCRUDService standCRUDService = InjectorProvider.getStandCRUDService();

    ObservableList<User> userModelObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        initTable();
    }

    public void initTable() {
        userModelObservableList.setAll(userCRUDService.getAllUsers());

        userIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId_user())));
        nameTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getName())));
        standTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStand().getName())));
        standPhoneNumberTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getStand().getPhone_number())));
        postalCodeTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPostal_code())));
        localTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getLocal())));
        districtTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDistrict())));
        emailTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEmail())));
        deletedTC.setCellValueFactory(cellData -> (cellData.getValue().isDeleted() ? new SimpleBooleanProperty(true) : new SimpleBooleanProperty(false)));
        deletedTC.setCellFactory(new Callback<>() {
            @Override
            public TableCell<User, Boolean> call(TableColumn<User, Boolean> param) {
                return new CheckBoxTableCell<User>();
            }
        });

        userTV.getItems().setAll(userModelObservableList);
    }
}