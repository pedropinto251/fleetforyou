package com.fleetforyou.fleetforyou.Domain.Utils;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;

public class CheckBoxTableCell<S> extends TableCell<S, Boolean> {
    private final CheckBox checkBox;

    private IUserCRUDService userCRUDService = InjectorProvider.getUserCRUDService();
    private IStandCRUDService standCRUDService = InjectorProvider.getStandCRUDService();
    private IClientCRUDService clientCRUDService = InjectorProvider.getClientCRUDService();
    private IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();

    public CheckBoxTableCell() {
        this.checkBox = new CheckBox();
        this.checkBox.setOnAction(e -> {
            if (getTableRow() != null) {
                int index = getTableRow().getIndex();
                if (index >= 0 && index < getTableView().getItems().size()) {
                    S item = getTableView().getItems().get(index);
                    Boolean value = checkBox.isSelected();
                    if (item.getClass().getName().endsWith("User")) {
                        ((User) item).setDeleted(value);
                        userCRUDService.removeUser(((User) item).getId_user());
                    } else if (item.getClass().getName().endsWith("Stand")) {
                        ((Stand) item).setDeleted(value);
                        standCRUDService.removeStand(((Stand) item).getId());
                    } else if (item.getClass().getName().endsWith("Client")) {
                        ((Client) item).setDeleted(value);
                        clientCRUDService.removeClient(((Client) item).getId());
                    } else if (item.getClass().getName().endsWith("Vehicle")) {
                        ((Vehicle) item).setDeleted(value);
                        vehicleCRUDService.removeVehicle(((Vehicle) item).getId_vehicle());
                    }
                }
            }
        });
        setGraphic(checkBox);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(checkBox);
            checkBox.setSelected(item != null && item);
        }
    }
}