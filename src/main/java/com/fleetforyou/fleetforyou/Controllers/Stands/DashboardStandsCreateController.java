package com.fleetforyou.fleetforyou.Controllers.Stands;

import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Districts;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider.getUserCRUDService;

public class DashboardStandsCreateController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField nameTF,
            phoneNumberTF,
            addressTF,
            postalCodeTF,
            localTF;
    @FXML
    ChoiceBox<String> districtCB;

    @FXML
    ChoiceBox<String> GestorCB;
    @FXML
    ChoiceBox<String> OperadorCB;
    @FXML
    ChoiceBox<String> VendedorCB;

    private UserLoggedDTO userLoggedDTO;
    private final IStandCRUDService standCRUDService = InjectorProvider.getStandCRUDService();
    private final IUserCRUDService userCRUDService = InjectorProvider.getUserCRUDService();


    ObservableList<User> userModelList = FXCollections.observableArrayList(userCRUDService.getAllUsers());

    // MANAGER PERMISSIONS
    Set<Permission> managerPermissions = EnumSet.of(
            Permission.MANAGE_EMPLOYEES,
            Permission.MANAGE_STATISTICS,
            Permission.VIEW_RENTAL_HISTORY
    );

    // SELLER PERMISSIONS
    Set<Permission> sellerPermissions = EnumSet.of(
            Permission.CREATE_RENTAL
    );

    // OPERATOR PERMISSIONS
    Set<Permission> operatorPermissions = EnumSet.of(
            Permission.MANAGE_CLIENTS,
            Permission.MANAGE_VEHICLES,
            Permission.MANAGE_RENTAL_PRICE
    );

    List<Stand> allStands = standCRUDService.getAllStands();
    List<String> districtNames = Districts.iterateByDistricts();
    ObservableList<String> districtNamesOL = FXCollections.observableArrayList(districtNames);
    Set<String> usedDistricts = allStands.stream()
            .map(Stand::getDistrict)
            .collect(Collectors.toSet());

    ObservableList<String> filteredDistricts = districtNamesOL.stream()
            .filter(district -> !usedDistricts.contains(district))
            .collect(Collectors.toCollection(FXCollections::observableArrayList));

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        districtCB.setItems(filteredDistricts);

        districtCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            /*
            Obter a lista de itens da ChoiceBox
            Ler o item na posição indicada pelo valor de number2 (convertido para Integer) dentro dessa lista
            Atualzia a listas para filtrar pelo distrito selecionado que é o que está na posição number2
            Atualiza as ChoiceBoxes com essas listas
             */
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                ObservableList<String> managersList = userModelList.stream()
                        .filter(user -> user.getDistrict().equals(districtCB.getItems().get((Integer) number2)))
                        .filter(user -> user.getPermissions().equals(managerPermissions))
                        .map(User::getName)
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
                GestorCB.setItems(managersList);
                ObservableList<String> operatorsList = userModelList.stream()
                        .filter(user -> user.getDistrict().equals(districtCB.getItems().get((Integer) number2)))
                        .filter(user -> user.getPermissions().equals(operatorPermissions))
                        .map(User::getName)
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
                OperadorCB.setItems(operatorsList);
                ObservableList<String> sellersList = userModelList.stream()
                        .filter(user -> user.getDistrict().equals(districtCB.getItems().get((Integer) number2)))
                        .filter(user -> user.getPermissions().equals(sellerPermissions))
                        .map(User::getName)
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
                VendedorCB.setItems(sellersList);
            }
        });
    }

    public void onAddStandButtonClicked(MouseEvent mouseEvent) {
        List<Stand> allStands = standCRUDService.getAllStands();

        if (allStands.size() >= 20) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Não pode adicionar outro Stand, pois está no limite.");
            alert.showAndWait();
            return;
        }

        Response exists = standCRUDService.existsByName(nameTF.getText());
        if (exists.getStatus().equals(Responses.Stand.Exists.EXISTS)) {
            Alert alertRegistration = new Alert(Alert.AlertType.ERROR, "Este nome já se encontra na base de dados");
            alertRegistration.showAndWait();
            return;
        }

        if (nameTF.getText().isEmpty() || nameTF.getText().isBlank()) {
            Alert alertname = new Alert(Alert.AlertType.ERROR, "Campo Nome sem dados");
            alertname.showAndWait();
            return;
        } else {
            String regexName = "^[a-zA-Z\\s]+$";
            Pattern patternName = Pattern.compile(regexName, Pattern.CASE_INSENSITIVE);
            Matcher matcherName = patternName.matcher(nameTF.getText());
            if (!matcherName.matches()) {
                Alert alertName = new Alert(Alert.AlertType.ERROR, "Campo Nome inválido. O campo Nome não deve conter dígitos");
                alertName.showAndWait();
                return;
            }
        }

        if (phoneNumberTF.getText().isEmpty() || phoneNumberTF.getText().isBlank()) {
            Alert alertphone = new Alert(Alert.AlertType.ERROR, "Campo Telefone/Telemovel sem dados");
            alertphone.showAndWait();
            return;
        } else {
            String phone = "\\d{9}";
            Pattern patternPhone = Pattern.compile(phone);
            Matcher matcherPhone = patternPhone.matcher(phoneNumberTF.getText());
            if (!matcherPhone.matches()) {
                Alert alertphone = new Alert(Alert.AlertType.ERROR, "Campo Telefone/Telemovel inválido.Tem de conter 9 digitos");
                alertphone.showAndWait();
                return;
            }
        }

        if (addressTF.getText().isEmpty() || addressTF.getText().isBlank()) {
            Alert alertadress = new Alert(Alert.AlertType.ERROR, "Campo Morada sem dados");
            alertadress.showAndWait();
            return;
        }

        if (postalCodeTF.getText().isEmpty() || postalCodeTF.getText().isBlank()) {
            Alert alertpostal = new Alert(Alert.AlertType.ERROR, "Campo Código-Postal sem dados");
            alertpostal.showAndWait();
            return;
        } else {
            String postal = "[1-9][0-9]{3}-[0-9]{3}";
            Pattern postal1 = Pattern.compile(postal);
            Matcher postalCode = postal1.matcher(postalCodeTF.getText());
            if (!postalCode.matches()) {
                Alert alertpsotalCode = new Alert(Alert.AlertType.ERROR, "Campo Código-Postal inválido.Tente em formato 0000-000");
                alertpsotalCode.showAndWait();
                return;
            }
        }

        if (localTF.getText().isEmpty() || localTF.getText().isBlank()) {
            Alert alertlocal = new Alert(Alert.AlertType.ERROR, "Campo Localidade sem dados");
            alertlocal.showAndWait();
            return;
        } else {
            String regexLocal = "^[a-zA-Z\\s]+$";
            Pattern patternLocal = Pattern.compile(regexLocal, Pattern.CASE_INSENSITIVE);
            Matcher matcherLocal = patternLocal.matcher(localTF.getText());
            if (!matcherLocal.matches()) {
                Alert alertName = new Alert(Alert.AlertType.ERROR, "Campo Localidade inválido. O campo Localidade não deve conter dígitos");
                alertName.showAndWait();
                return;
            }
        }

        if (districtCB.getValue() == null) {
            Alert alertdistrito = new Alert(Alert.AlertType.ERROR, "Campo Distrito sem dados");
            alertdistrito.showAndWait();
            return;
        }

        if (GestorCB.getValue() == null) {
            Alert alertGestor = new Alert(Alert.AlertType.ERROR, "Campo Gestor sem dados");
            alertGestor.showAndWait();
            return;
        }

        if (OperadorCB.getValue() == null) {
            Alert alertOperador = new Alert(Alert.AlertType.ERROR, "Campo Operador sem dados");
            alertOperador.showAndWait();
            return;
        }

        if (VendedorCB.getValue() == null) {
            Alert alertVendedor = new Alert(Alert.AlertType.ERROR, "Campo Vendedor sem dados");
            alertVendedor.showAndWait();
            return;
        }

        StandCreateDTO standCreateDTO = new StandCreateDTO(nameTF.getText(),
                phoneNumberTF.getText(),
                addressTF.getText(),
                postalCodeTF.getText(),
                localTF.getText(),
                districtCB.getValue()
        );

        Response standResponse = standCRUDService.createStand(standCreateDTO);

        if (standResponse.getStatus().equals(Responses.Stand.Create.CREATED_SUCCESS)) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Stand criado com sucesso");
            alert1.showAndWait();
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao criar o Stand");
            alert1.showAndWait();
        }
    }
}


