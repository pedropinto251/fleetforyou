package com.fleetforyou.fleetforyou.Controllers.Extras;

import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class DashboardExtrasDeleteController {
    @FXML
    AnchorPane anchorPane;

    private UserLoggedDTO userLoggedDTO;


    private final IUserCRUDService userCRUDService = InjectorProvider.getUserCRUDService();
    private final IStandCRUDService standCRUDService = InjectorProvider.getStandCRUDService();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();
    }

    //TODO: DELETE USER
}
