package com.fleetforyou.fleetforyou.Controllers.Stands;

import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Districts;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DashboardStandsUpdateController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField idStandTF,
            nameTF,
            phoneNumberTF,
            addressTF,
            postalCodeTF,
            localTF;

    @FXML
    ChoiceBox <String> districtCB;


    private UserLoggedDTO userLoggedDTO;

    private final IStandCRUDService standCRUDService = InjectorProvider.getStandCRUDService();
    List<Stand> allStands = standCRUDService.getAllStands();

    // Obtém todos os distritos
    List<String> districtNames = Districts.iterateByDistricts();
    ObservableList<String> districtNamesOL = FXCollections.observableArrayList(districtNames);

    // Unifica todos os distritos utilizados (para não haver duplicatas -> Facilita o processo seguinte)
    Set<String> usedDistricts = allStands.stream()
            .map(Stand::getDistrict) // Obtém o distrito de cada stand
            .collect(Collectors.toSet()); // Coleta num conjunto para garantir a unicidade

    // Filtra os distritos que não são utilizados por nenhum stand
    ObservableList<String> filteredDistricts = districtNamesOL.stream()
            .filter(district -> !usedDistricts.contains(district)) // Filtra os distritos não utilizados
            .collect(Collectors.toCollection(FXCollections::observableArrayList)); // Coleta numa ObservableList

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();
        districtCB.setItems(filteredDistricts);//criação da choicebox
    }

    public void onUpdateStandButtonClicked(MouseEvent mouseEvent) {


        if (idStandTF.getText().isBlank() || idStandTF.getText().isEmpty()) {
            Alert alertid = new Alert(Alert.AlertType.ERROR, "Campo Id sem dados");
            alertid.showAndWait();
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
            Alert alertVendedor = new Alert(Alert.AlertType.ERROR, "Campo Distrito sem dados");
            alertVendedor.showAndWait();
            return;
        }



        Response standSearchResponse = standCRUDService.getStandById(Integer.parseInt(idStandTF.getText()));

        if (standSearchResponse.getStatus().equals(Responses.Stand.Find.FOUND)) {
            Stand stand = (Stand) standSearchResponse.getData();
            StandDTO standDTO = new StandDTO(
                    Integer.parseInt(idStandTF.getText()),
                    nameTF.getText(),
                    phoneNumberTF.getText(),
                    addressTF.getText(),
                    postalCodeTF.getText(),
                    localTF.getText(),
                    districtCB.getValue(),
                    stand.isDeleted()
            );

            Response standUpdateResponse = standCRUDService.updateStand(standDTO);

            if (standUpdateResponse.getStatus().equals(Responses.Stand.Update.UPDATED_SUCCESS)) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Stand atualizado com sucesso");
                alert1.showAndWait();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao atualizar o Stand");
                alert1.showAndWait();
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao encontrar o Stand ID de stand não encontrado");
            alert1.showAndWait();
        }
    }
}
