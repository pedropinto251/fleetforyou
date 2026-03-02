package com.fleetforyou.fleetforyou.Controllers.Stands;

import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Rental;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import com.fleetforyou.fleetforyou.Domain.Utils.CheckBoxTableCell;

public class DashboardStandsReadController {
    @FXML
    AnchorPane anchorPane;

    @FXML
    TableView<Stand> standTV;
    @FXML
    TableColumn<Stand, String> standIdTC,
            nameTC,
            addressTC,
            phoneNumberTC,
            postalCodeTC,
            localTC,
            districtTC;
    @FXML
    TableColumn<Stand, Boolean> deletedTC;

    private UserLoggedDTO userLoggedDTO;

    private final IStandCRUDService standCRUDService = InjectorProvider.getStandCRUDService();
    private final IUserCRUDService userCRUDService = InjectorProvider.getUserCRUDService();

    ObservableList<Stand> standModelObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        initTable();
    }

    public void initTable() {
        if (userLoggedDTO.permissions().contains(Permission.GOD_ADMIN)) {
            standModelObservableList.setAll(standCRUDService.getAllStands());
        } else {
            Response user = userCRUDService.getUserById(userLoggedDTO.id_user());
            if (user.getStatus().equals(Responses.User.Find.FOUND)) {
                User userFound = (User) user.getData();
                standModelObservableList.setAll(standCRUDService.getAllSameDistrict(userFound.getDistrict()));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Utilizador logado não encontrado.");
                alert.showAndWait();
            }
        }


        standIdTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        nameTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getName())));
        addressTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAddress())));
        phoneNumberTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPhone_number())));
        postalCodeTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPostalCode())));
        localTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getLocal())));
        districtTC.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDistrict())));
        deletedTC.setCellValueFactory(cellData -> (cellData.getValue().isDeleted() ? new SimpleBooleanProperty(true) : new SimpleBooleanProperty(false)));
        deletedTC.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Stand, Boolean> call(TableColumn<Stand, Boolean> param) {
                return new CheckBoxTableCell<Stand>();
            }
        });

        standTV.getItems().setAll(standModelObservableList);
    }
}
