package com.fleetforyou.fleetforyou.Controllers.Clients;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.Client.ClientDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Districts;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardClientsUpdateController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField idClientTF,
            nameTF,
            phoneTF,
            addressTF,
            postalCodeTF,
            localTF,
            ncTF;
    @FXML
    ChoiceBox<String> districtCB;
    @FXML
    Button addClientButton;

    private UserLoggedDTO userLoggedDTO;

    private IClientCRUDService clientCRUDService = InjectorProvider.getClientCRUDService();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        List<String> districtNames = Districts.iterateByDistricts();
        ObservableList<String> districtNamesOL = FXCollections.observableArrayList(districtNames);
        districtCB.setItems(districtNamesOL);
    }

    public void onUpdateClientButtonClicked(MouseEvent mouseEvent) {
        Response existsByNif = clientCRUDService.existsByNif(ncTF.getText());
        if (existsByNif.getStatus().equals(Responses.Client.Nif.NIF_EXISTS)) {
            Alert alertNif = new Alert(Alert.AlertType.ERROR,"Este NIF já se encontra em uso");
            alertNif.showAndWait();
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

        if (phoneTF.getText().isEmpty() || phoneTF.getText().isBlank()) {
            Alert alertphone = new Alert(Alert.AlertType.ERROR, "Campo Telefone sem dados");
            alertphone.showAndWait();
            return;
        }else{
            String phone = "\\d{9}";
            Pattern patternPhone = Pattern.compile(phone);
            Matcher matcherPhone = patternPhone.matcher(phoneTF.getText());
            if (!matcherPhone.matches()) {
                Alert alertphone = new Alert(Alert.AlertType.ERROR, "Campo Telefone/Telemóvel inválido.Tem de conter 9 digitos");
                alertphone.showAndWait();
                return;
            }
        }

        if (addressTF.getText().isEmpty() || addressTF.getText().isBlank()) {
            Alert alertaddress = new Alert(Alert.AlertType.ERROR, "Campo Morada sem dados");
            alertaddress.showAndWait();
            return;
        }

        if (postalCodeTF.getText().isEmpty() || postalCodeTF.getText().isBlank()) {
            Alert alertpostal = new Alert(Alert.AlertType.ERROR, "Campo Código-Postal sem dados");
            alertpostal.showAndWait();
            return;
        }else{
            String postal = "[1-9][0-9]{3}-[0-9]{3}";
            Pattern postal1 = Pattern.compile(postal);
            Matcher postalCode1 = postal1.matcher(postalCodeTF.getText());
            if (!postalCode1.matches()) {
                Alert alertpsotalCode = new Alert(Alert.AlertType.ERROR, "Campo Código-Postal inválido.Tente em formato xxxx-xxx");
                alertpsotalCode.showAndWait();
                return;
            }
        }

        if (localTF.getText().isEmpty() || localTF.getText().isBlank()) {
            Alert alertlocality = new Alert(Alert.AlertType.ERROR, "Campo Localidade sem dados");
            alertlocality.showAndWait();
            return;
        }else {
            String regexLocality = "^[a-zA-Z\\s]+$";
            Pattern patternLocality = Pattern.compile(regexLocality, Pattern.CASE_INSENSITIVE);
            Matcher matcherLocality = patternLocality.matcher(localTF.getText());
            if (!matcherLocality.matches()) {
                Alert alertName = new Alert(Alert.AlertType.ERROR, "Campo Localidade inválido. O campo Localidade não deve conter dígitos");
                alertName.showAndWait();
                return;
            }
        }

        if (districtCB.getValue() == null) {
            Alert alertdistrict = new Alert(Alert.AlertType.ERROR, "Campo Distrito sem dados");
            alertdistrict.showAndWait();
            return;
        }

        if (ncTF.getText().isEmpty() || ncTF.getText().isBlank()) {
            Alert alertnif = new Alert(Alert.AlertType.ERROR, "Campo Nº Contribuinte sem dados");
            alertnif.showAndWait();
            return;
        }else {
            String nc = "\\d{9}";
            Pattern NIF = Pattern.compile(nc);
            Matcher matcherNIF = NIF.matcher(ncTF.getText());
            if (!matcherNIF.matches()) {
                Alert alertNIF = new Alert(Alert.AlertType.ERROR, "Campo Nº Contribuinte inválido.O campo Nº Contribuinte tem de conter 9 digitos");
                alertNIF.showAndWait();
                return;
            }
        }


        Response clientSearchResponse = clientCRUDService.getClientById(Integer.parseInt(idClientTF.getText()));

        if (clientSearchResponse.getStatus().equals(Responses.Client.Find.FOUND)) {
            Client client = (Client) clientSearchResponse.getData();
            ClientDTO clientDTO = new ClientDTO(
                    Integer.parseInt(idClientTF.getText()),
                    nameTF.getText(),
                    ncTF.getText(),
                    phoneTF.getText(),
                    addressTF.getText(),
                    postalCodeTF.getText(),
                    localTF.getText(),
                    districtCB.getValue(),
                    client.isDeleted()
            );

            Response clientUpdateResponse = clientCRUDService.updateClient(clientDTO);

            if (clientUpdateResponse.getStatus().equals(Responses.Client.Update.UPDATED_SUCCESS)) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Cliente atualizado com sucesso");
                alert1.showAndWait();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao atualizar o Cliente");
                alert1.showAndWait();
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao encontrar o Cliente,ID Cliente não encontrado");
            alert1.showAndWait();
        }
    }
}
