package com.fleetforyou.fleetforyou.Controllers.Extras;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserLoginService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserRegisterDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Districts;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Enums.UserType;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardExtrasCreateController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField nameTF,
    emailTF,
    addressTF,
    standTF,
    phoneNumberTF,
    postalCodeTF,
    localTF;
    @FXML
    ChoiceBox<String> typeCB,
    districtCB;
    @FXML
    PasswordField passwordPF;

    private UserLoggedDTO userLoggedDTO;


    private final IUserCRUDService userCRUDService = InjectorProvider.getUserCRUDService();
    private final IUserLoginService userLoginService = InjectorProvider.getUserLoginService();
    private final IStandCRUDService standCRUDService = InjectorProvider.getStandCRUDService();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        List<String> userTypes = UserType.iterateByUserTypes();
        ObservableList<String> userOperadorOL = FXCollections.observableArrayList(userTypes);
        typeCB.setItems(userOperadorOL);

        List<String> districtNames = Districts.iterateByDistricts();
        ObservableList<String> districtNamesOL = FXCollections.observableArrayList(districtNames);
        districtCB.setItems(districtNamesOL);
    }

    public void onAddEmployeeButtonClicked(MouseEvent mouseEvent) {
        Response existsByEmail = userLoginService.existsByEmail(emailTF.getText());
        if (existsByEmail.getStatus().equals(Responses.User.Email.EMAIL_EXISTS)) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR,"Este email já se encontra em uso");
            alert2.showAndWait();
            return;
        }

        if (nameTF.getText().isEmpty() || nameTF.getText().isBlank()) {
            Alert alertname = new Alert(Alert.AlertType.ERROR, "Campo Nome sem dados");
            alertname.showAndWait();
            return;
        }else{
            String regexName = "^[a-zA-Z\\s]+$";
            Pattern patternName = Pattern.compile(regexName, Pattern.CASE_INSENSITIVE);
            Matcher matcherName = patternName.matcher(nameTF.getText());
            if (!matcherName.matches()) {
                Alert alertName = new Alert(Alert.AlertType.ERROR, "Campo Nome inválido. O campo Nome não deve conter dígitos");
                alertName.showAndWait();
                return;
            }
        }

        if (emailTF.getText().isEmpty() || emailTF.getText().isBlank()) {
            Alert alertemail = new Alert(Alert.AlertType.ERROR, "Campo Email sem dados");
            alertemail.showAndWait();
            return;
        } else {
            String regexEmail = "^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";
            Pattern patternName = Pattern.compile(regexEmail, Pattern.CASE_INSENSITIVE);
            Matcher matcherName = patternName.matcher(emailTF.getText());
            if (!matcherName.matches()) {
                Alert alertName = new Alert(Alert.AlertType.ERROR, "Campo Email inválido.");
                alertName.showAndWait();
                return;
            }
        }

        if (passwordPF.getText().isEmpty() || passwordPF.getText().isBlank()) {
            Alert alertPassoword = new Alert(Alert.AlertType.ERROR, "Campo Password sem dados");
            alertPassoword.showAndWait();
            return;
        }

        if (addressTF.getText().isEmpty() || addressTF.getText().isBlank()) {
            Alert alertAddress = new Alert(Alert.AlertType.ERROR, "Campo Morada sem dados");
            alertAddress.showAndWait();
            return;
        }

        if (typeCB.getValue() == null ) {
            Alert alertType = new Alert(Alert.AlertType.ERROR, "Campo Tipo sem dados");
            alertType.showAndWait();
            return;
        }

        if (standTF.getText().isEmpty() || standTF.getText().isBlank()) {
            Alert alertStand = new Alert(Alert.AlertType.ERROR, "Campo Stand sem dados");
            alertStand.showAndWait();
            return;
        }else {
            String regexStand = "^\\d+$";
            Pattern patternStand = Pattern.compile(regexStand, Pattern.CASE_INSENSITIVE);
            Matcher matcherStand = patternStand.matcher(standTF.getText());
            if (!matcherStand.matches()) {
                Alert alertName = new Alert(Alert.AlertType.ERROR, "Campo Stand inválido. O campo Stand só pode conter o ID");
                alertName.showAndWait();
                return;
            }
        }

        if (phoneNumberTF.getText().isEmpty() || phoneNumberTF.getText().isBlank()) {
            Alert alertphone = new Alert(Alert.AlertType.ERROR, "Campo Telefone/Telemovel sem dados");
            alertphone.showAndWait();
            return;
        }else {
            String phone = "\\d{9}";
            Pattern patternPhone = Pattern.compile(phone);
            Matcher matcherPhone = patternPhone.matcher(phoneNumberTF.getText());
            if (!matcherPhone.matches()) {
                Alert alertphone = new Alert(Alert.AlertType.ERROR, "Campo Telefone/Telemovel inválido.Tem de conter 9 digitos");
                alertphone.showAndWait();
                return;
            }
        }

        if (postalCodeTF.getText().isEmpty() || postalCodeTF.getText().isBlank()) {
            Alert alertpostal = new Alert(Alert.AlertType.ERROR, "Campo Código-Postal sem dados");
            alertpostal.showAndWait();
            return;
        }else {
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
        }else {
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
            Alert alertDistrict = new Alert(Alert.AlertType.ERROR, "Campo Distrito sem dados");
            alertDistrict.showAndWait();
            return;
        }

        Set<Permission> permissions = new HashSet<>();

        // TODO: Verificar com o professor sobre as permissões
        if (typeCB.getValue().equals("Operador")){
            permissions.add(Permission.MANAGE_CLIENTS );
            permissions.add(Permission.MANAGE_VEHICLES);
            permissions.add(Permission.MANAGE_RENTAL_PRICE );
        }
        if (typeCB.getValue().equals("Vendedor")){
            permissions.add(Permission.CREATE_RENTAL );
        }
        if (typeCB.getValue().equals("Gestor")){
            permissions.add(Permission.MANAGE_EMPLOYEES);
            permissions.add(Permission.MANAGE_STATISTICS);
            permissions.add(Permission.VIEW_RENTAL_HISTORY);
        }

        Stand stand = null;

        Response standResponse = standCRUDService.getStandById(Integer.parseInt(standTF.getText()));
        if (standResponse.getStatus().equals(Responses.Stand.Find.FOUND)) {
            stand = (Stand) standResponse.getData();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR,"Stand não encontrado");
            alert2.showAndWait();
            return;
        }

        boolean deleted = false;

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(
                nameTF.getText(),
                phoneNumberTF.getText(),
                addressTF.getText(),
                postalCodeTF.getText(),
                localTF.getText(),
                districtCB.getValue(),
                emailTF.getText(),
                passwordPF.getText(),
                permissions,
                stand,
                deleted
        );

        Response userResponse = userCRUDService.registerUser(userRegisterDTO);

        if (userResponse.getStatus().equals(Responses.User.Create.CREATED_SUCCESS)){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION,"User adicionado com sucesso");
            alert1.showAndWait();
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR,"Ocorreu um erro ao criar o User");
            alert1.showAndWait();
        }
    }
}
