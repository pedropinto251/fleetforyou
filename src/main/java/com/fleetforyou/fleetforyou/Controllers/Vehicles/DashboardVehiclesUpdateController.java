package com.fleetforyou.fleetforyou.Controllers.Vehicles;

import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Vehicle.VehicleDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardVehiclesUpdateController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField idVehicleTF,
            priceTF,
            priceSellingTF;

    @FXML
    CheckBox statusCB;

    private UserLoggedDTO userLoggedDTO;

    private final IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();
    }

    public void onUpdateButtonClicked(MouseEvent mouseEvent) {
        // Verificação do campo ID do veículo
        if (idVehicleTF.getText().isEmpty() || idVehicleTF.getText().isBlank()) {
            Alert alertId = new Alert(Alert.AlertType.ERROR, "Campo Id sem dados");
            alertId.showAndWait();
            return;
        } else {
            String regexID = "^\\d+$";
            Pattern patternID = Pattern.compile(regexID, Pattern.CASE_INSENSITIVE);
            Matcher matcherID = patternID.matcher(idVehicleTF.getText());
            if (!matcherID.matches()) {
                Alert alertId = new Alert(Alert.AlertType.ERROR, "Campo ID inválido. O campo ID deve conter apenas dígitos");
                alertId.showAndWait();
                return;
            }
        }

        // Verificação do campo Preço de Venda
        if (priceSellingTF.getText().isEmpty() || priceSellingTF.getText().isBlank()) {
            Alert alertPriceSelling = new Alert(Alert.AlertType.ERROR, "Campo Preço Venda sem dados");
            alertPriceSelling.showAndWait();
            return;
        } else {
            String regexPriceSelling = "^\\d+$";
            Pattern patternPriceSelling = Pattern.compile(regexPriceSelling, Pattern.CASE_INSENSITIVE);
            Matcher matcherPriceSelling = patternPriceSelling.matcher(priceSellingTF.getText());
            if (!matcherPriceSelling.matches()) {
                Alert alertPriceSelling = new Alert(Alert.AlertType.ERROR, "Campo Preço Venda inválido. O campo Preço Venda deve conter apenas dígitos");
                alertPriceSelling.showAndWait();
                return;
            } else {
                int priceSelling = Integer.parseInt(priceSellingTF.getText());
                if (priceSelling < 1) {
                    Alert alertPriceSelling = new Alert(Alert.AlertType.ERROR, "Campo Preço Venda inválido. Campo Preço Venda tem que ser superior a 0");
                    alertPriceSelling.showAndWait();
                    return;
                }
            }
        }

        // Verificação do campo Preço
        if (priceTF.getText().isEmpty() || priceTF.getText().isBlank()) {
            Alert alertPrice = new Alert(Alert.AlertType.ERROR, "Campo Preço Diario sem dados");
            alertPrice.showAndWait();
            return;
        } else {
            String regexPrice = "^\\d+$";
            Pattern patternPrice = Pattern.compile(regexPrice, Pattern.CASE_INSENSITIVE);
            Matcher matcherPrice = patternPrice.matcher(priceTF.getText());
            if (!matcherPrice.matches()) {
                Alert alertPrice = new Alert(Alert.AlertType.ERROR, "Campo Preço Diario inválido. O campo Preço Diario deve conter apenas dígitos");
                alertPrice.showAndWait();
                return;
            } else {
                int price = Integer.parseInt(priceTF.getText());
                if (price < 1) {
                    Alert alertPrice = new Alert(Alert.AlertType.ERROR, "Campo Preço Diario inválido. Campo Preço Diario tem que ser superior a 0");
                    alertPrice.showAndWait();
                    return;
                }
            }
        }

        Response vehicleSearchResponse = vehicleCRUDService.getVehicleById(Integer.parseInt(idVehicleTF.getText()));

        if (vehicleSearchResponse.getStatus().equals(Responses.Vehicle.Find.FOUND)) {
            Vehicle vehicle = (Vehicle) vehicleSearchResponse.getData();
            VehicleDTO vehicleDTO = new VehicleDTO(
                    Integer.parseInt(idVehicleTF.getText()),
                    vehicle.getRegistration(),
                    vehicle.getBrand(),
                    vehicle.getModel(),
                    vehicle.getSegment(),
                    vehicle.isStatus(),
                    vehicle.getFuel(),
                    vehicle.getYear_built(),
                    vehicle.getNum_doors(),
                    vehicle.getNum_km(),
                    vehicle.getEngine_capacity(),
                    vehicle.getPotency(),
                    vehicle.getColor(),
                    Double.parseDouble(priceTF.getText()),
                    vehicle.getType(),
                    vehicle.isDeleted(),
                    Double.parseDouble(priceSellingTF.getText()),
                    false
            );

            Response vehicleUpdateResponse = vehicleCRUDService.updateVehicle(vehicleDTO);

            if (vehicleUpdateResponse.getStatus().equals(Responses.Vehicle.Update.UPDATED_SUCCESS)) {
                Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION, "Veículo atualizado com sucesso");
                alertSuccess.showAndWait();
            } else {
                Alert alertError = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao atualizar o Veículo");
                alertError.showAndWait();
            }
        } else {
            Alert alertNotFound = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao encontrar o Veículo. ID do veiculo não encontrado");
            alertNotFound.showAndWait();
        }
    }
}
