package com.fleetforyou.fleetforyou.Controllers.Rents;

import com.fleetforyou.fleetforyou.Application.Services.FormService.IFormCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.RentalService.IRentalCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.Form.FormCreateDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Rental.RentalDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Vehicle.VehicleDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.Rental;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardRentsUpdateController {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField idRentalTF;
    @FXML
    private DatePicker dateReturnDP;
    @FXML
    private TextField mileageTF;
    @FXML
    private TextField idUserValue;
    @FXML
    private TextField userNameValue;
    private UserLoggedDTO userLoggedDTO;

    private IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();
    private IRentalCRUDService rentalCRUDService = InjectorProvider.getRentalCRUDService();
    private IFormCRUDService formCRUDService = InjectorProvider.getFormCRUDService();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        if (userLoggedDTO != null) {
            idUserValue.setText(String.valueOf(userLoggedDTO.id_user()));
            userNameValue.setText(userLoggedDTO.name());
        }
    }

    public void onAddRentalButtonClicked(MouseEvent mouseEvent) {
        if (idRentalTF.getText().isBlank() || idRentalTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Campo Id sem dados.");
            alert.showAndWait();
            return;
        } else {
            String regex = "^\\d+$";
            Pattern patternId = Pattern.compile(regex);
            Matcher matcherId = patternId.matcher(idRentalTF.getText());
            if (!matcherId.matches()) {
                Alert alertKM = new Alert(Alert.AlertType.ERROR, "Campo Id inválido. Campo Id só pode conter dígitos.");
                alertKM.showAndWait();
                return;
            }
        }
        if (dateReturnDP.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Campo Data de Retorno sem dados.");
            alert.showAndWait();
            return;
        }
        if (!mileageTF.getText().isEmpty() || !mileageTF.getText().isBlank()) {
            String regex = "^\\d+$";
            Pattern patternKM = Pattern.compile(regex);
            Matcher matcher = patternKM.matcher(mileageTF.getText());
            if (!matcher.matches()) {
                Alert alertKM = new Alert(Alert.AlertType.ERROR, "Campo KM inválido. Campo KM só pode conter dígitos.");
                alertKM.showAndWait();
                return;
            }
        }
        int mileage;
        mileage = Integer.parseInt(mileageTF.getText());
        if (mileage < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Campo Km inválido.Deve inserir os kms corretos de retorno.");
            alert.showAndWait();
            return;
        }

        Response rentalResponse = rentalCRUDService.getRentalById(Integer.parseInt(idRentalTF.getText()));

        if (rentalResponse.getStatus().equals(Responses.Rental.Find.FOUND)) {
            Rental rental = (Rental) rentalResponse.getData();

            java.util.Date utilStartDate = rental.getDate_start();
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());

            LocalDate rentalStartDate = sqlStartDate.toLocalDate();

            if (dateReturnDP.getValue().isBefore(rentalStartDate) || dateReturnDP.getValue().isEqual(rentalStartDate)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "A data de retorno não pode ser antes ou no mesmo dia da data de começo.");
                alert.showAndWait();
                return;
            }

            if (rental.getDate_return() != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Este Aluguer já terminou.");
                alert.showAndWait();
                return;
            }

            java.util.Date dateReturn = java.util.Date.from(dateReturnDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            RentalDTO rentalDTO = new RentalDTO(rental.getId_rental(), rental.getDate_start(), rental.getDate_end(), dateReturn, rental.getVehicle(), rental.getClient(), rental.getUser(), rental.getVehicleStatus());

            Vehicle vehicle = rental.getVehicle();
            if (mileage < vehicle.getNum_km()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "A nova quilometragem não pode ser menor que a atual.");
                alert.showAndWait();
                return;
            }

            VehicleDTO vehicleDTO = new VehicleDTO(vehicle.getId_vehicle(), vehicle.getRegistration(), vehicle.getBrand(), vehicle.getModel(), vehicle.getSegment(), vehicle.isStatus(), vehicle.getFuel(), vehicle.getYear_built(), vehicle.getNum_doors(), mileage, vehicle.getEngine_capacity(), vehicle.getPotency(), vehicle.getColor(), vehicle.getRental_price(), vehicle.getType(), false, vehicle.getSelling_price(), false);

            GridPane gridPaneForm = new GridPane();

            ChoiceBox<Integer> satisfactionCB = new ChoiceBox<>();
            satisfactionCB.setId("satisfactionCB");
            satisfactionCB.getItems().addAll(1, 2, 3, 4, 5);
            gridPaneForm.add(new Label("Satisfação : "), 0, 0);
            gridPaneForm.add(satisfactionCB, 1, 0);

            TextArea observationTA = new TextArea();
            observationTA.setId("observationTA");
            gridPaneForm.add(new Label("Observação : "), 0, 1);
            gridPaneForm.add(observationTA, 1, 1);

            Alert alertForm = new Alert(Alert.AlertType.INFORMATION, "Formulário de Satisfação");
            alertForm.setHeaderText("Formulário de Satisfação");
            alertForm.getDialogPane().setContent(gridPaneForm);
            alertForm.showAndWait();

            if (satisfactionCB.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "A satisfação não pode estar vazia.");
                alert.showAndWait();
                return;
            }

            if (observationTA.getText().isEmpty() || observationTA.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "A observação não pode estar vazia.");
                alert.showAndWait();
                return;
            }

            FormCreateDTO formCreateDTO = new FormCreateDTO(
                    satisfactionCB.getValue(),
                    observationTA.getText(),
                    rental
            );

            Response formResponse = formCRUDService.createForm(formCreateDTO);

            if (formResponse.getStatus().equals(Responses.Form.Create.CREATED_SUCCESS)) {
                Response vehicleUpdateResponse = vehicleCRUDService.updateVehicle(vehicleDTO);
                if (vehicleUpdateResponse.getStatus().equals(Responses.Vehicle.Update.UPDATED_SUCCESS)) {
                    Response rentalUpdateResponse = rentalCRUDService.updateRental(rentalDTO);

                    if (rentalUpdateResponse.getStatus().equals(Responses.Rental.Update.UPDATED_SUCCESS)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Aluguer Terminado com sucesso.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao Terminar o aluguer.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao atualizar a quilometragem do veículo.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao Criar o formulário.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aluguer não encontrado.");
            alert.showAndWait();
        }
    }
}
