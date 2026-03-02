package com.fleetforyou.fleetforyou.Controllers;

import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserLoginService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.UserService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoginDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Utils.LoaderFXML;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;

public class LoginController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button loginButton;
    @FXML
    TextField emailTextField;
    @FXML
    PasswordField passwordTextField;

    LoaderFXML loaderFXML;

    private final IUserLoginService userLoginService = InjectorProvider.getUserLoginService();

    @FXML
    public void initialize() {
        // Initialize LoaderFXML instance
        loaderFXML = new LoaderFXML();
    }

    @FXML
    public void onLoginButtonClicked() {
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);

        Response loginResponse = userLoginService.loginUser(userLoginDTO);

        if (loginResponse.getStatus().equals(Responses.Login.LOGIN_SUCCESS)){
            UserLoggedDTO userLoggedDTO = (UserLoggedDTO) loginResponse.getData();

            Session.setUser(userLoggedDTO);

            loaderFXML.loadInitial((Stage) loginButton.getScene().getWindow());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Endereço de email ou senha incorretos.");
            alert.showAndWait();
        }
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            onLoginButtonClicked();
        }
    }
}
