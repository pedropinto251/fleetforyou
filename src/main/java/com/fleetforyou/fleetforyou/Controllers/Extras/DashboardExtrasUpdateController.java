package com.fleetforyou.fleetforyou.Controllers.Extras;

import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Districts;
import com.fleetforyou.fleetforyou.Domain.Enums.UserType;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class DashboardExtrasUpdateController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField idEmployeeTF,
            nameTF,
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


    //TODO: UPDATE USER
    public void onUpdateEmployeeButtonClicked(MouseEvent mouseEvent) {
    }
}