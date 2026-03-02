package com.fleetforyou.fleetforyou.Controllers.Stands;

import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Utils.CheckBoxTableCell;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class DashboardStandsDeleteController {
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

    ObservableList<Stand> standModelObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        initTable();
    }

    public void onRemoveStandButtonClicked(MouseEvent mouseEvent) {
        Stand selecionado = standTV.getSelectionModel().getSelectedItem();

        if (selecionado == null) {
            showAlert(Alert.AlertType.ERROR, "Stand não selecionado", "Selecione um Stand para remover.");
            return;
        }

        // Verifica se o stand tem usuários associados
        if (standCRUDService.hasUserStand(selecionado.getId())) {
            showWarningAlert("Não é possível remover o Stand",
                    "O Stand selecionado tem Utilizadores ativos e não pode ser removido.");
            return; // Termina a execução do método se o stand tiver usuários
        }

        // Remover o stand selecionado
        standCRUDService.removeStand(selecionado.getId());
        showAlert(Alert.AlertType.INFORMATION, "Stand removido",
                "O Stand selecionado foi removido com sucesso.");
        initTable(); // Recarrega a tabela para refletir a remoção
    }


    public void initTable() {
        standModelObservableList.setAll(standCRUDService.getAllStands());

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
