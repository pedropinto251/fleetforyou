package com.fleetforyou.fleetforyou.Controllers.Vehicles;

import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;
import com.fleetforyou.fleetforyou.Domain.DTO.Vehicle.VehicleCreateDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.*;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Domain.Utils.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.Year;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DashboardVehiclesCreateController {

    @FXML
    AnchorPane anchorPane;

    @FXML
    JFXButton backBTN;

    @FXML
    ChoiceBox<String> brandCB;
    @FXML
    TextField colorTF;
    @FXML
    TextField doorsTF;

    @FXML
    TextField engineTF;

    @FXML
    ChoiceBox<String> fuelCB;

    @FXML
    TextField kmTF;

    @FXML
    ChoiceBox<Cars> modelCB;

    @FXML
    TextField powerTF;

    @FXML
    Button registerBTN;

    @FXML
    TextField registrationTF;

    @FXML
    ChoiceBox<String> segmentCB;

    @FXML
    TextField stateTF;

    @FXML
    ChoiceBox<String> typeCB;

    @FXML
    TextField yearTF;
    @FXML
    TextField selling_priceTC;
    @FXML
    TextField priceTC;

    @FXML
    void onRegisterButtonClick(MouseEvent event) {
        Response exists = vehicleCRUDService.existsByRegistration(registrationTF.getText());
        if (exists.getStatus().equals(Responses.Vehicle.Registration.REGISTRATION_EXISTS)) {
            Alert alertRegistration = new Alert(Alert.AlertType.ERROR, "Esta matricula já se encontra na base de dados");
            alertRegistration.showAndWait();
            return;
        }

        if (brandCB.getValue() == null) { //
            Alert alertMarcas = new Alert(Alert.AlertType.ERROR, "Campo Marca e Modelo sem dados.Selecione a marca para selecionar o modelo");
            alertMarcas.showAndWait();
            return;
        }

        if (modelCB.getValue() == null) { //
            Alert alertModels = new Alert(Alert.AlertType.ERROR, "Campo Modelo sem dados");
            alertModels.showAndWait();
            return;
        }

        if (segmentCB.getValue() == null) { //
            Alert alertSegments = new Alert(Alert.AlertType.ERROR, "Campo Segmentos sem dados");
            alertSegments.showAndWait();
            return;
        }

        if (typeCB.getValue() == null) {
            Alert alertType = new Alert(Alert.AlertType.ERROR, "Campo Tipo sem dados");
            alertType.showAndWait();
            return;
        }

        if (fuelCB.getValue() == null) { //
            Alert alertFuel = new Alert(Alert.AlertType.ERROR, "Campo Combustivel sem dados");
            alertFuel.showAndWait();
            return;
        }


        if (registrationTF.getText().isBlank() || registrationTF.getText().isEmpty()) {
            Alert alertRegistration = new Alert(Alert.AlertType.ERROR, "Campo Matricula sem dados");
            alertRegistration.showAndWait();
            return;
        } else {
            String regex = "^[A-Z]{2}-\\d{2}-\\d{2}$|^\\d{2}-\\d{2}-[A-Z]{2}$|^\\d{2}-[A-Z]{2}-\\d{2}$|^[A-Z]{2}-\\d{2}-[A-Z]{2}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(registrationTF.getText());
            if (!matcher.matches()) {
                Alert alertRegistration = new Alert(Alert.AlertType.ERROR, "Matricula inválida.Tente o formato português");
                alertRegistration.showAndWait();
                return;
            }
        }

        if (yearTF.getText().isEmpty() || yearTF.getText().isBlank()) {
            Alert alertYear = new Alert(Alert.AlertType.ERROR, "Campo Ano Construido sem dados");
            alertYear.showAndWait();
            return;
        }

        String regexYear = "^\\d{4}$";
        Pattern patternYear = Pattern.compile(regexYear);
        Matcher matcherYear = patternYear.matcher(yearTF.getText());
        if (!matcherYear.matches()) {
            Alert alertYear = new Alert(Alert.AlertType.ERROR, "Campo Ano Construido inválido. Tente o formato 0000");
            alertYear.showAndWait();
            return;
        }

        int yearBuilt = Integer.parseInt(yearTF.getText());
        int currentYear = Year.now().getValue();
        if (yearBuilt > currentYear || yearBuilt < 1886) {
            Alert alertYear = new Alert(Alert.AlertType.ERROR, "Campo Ano Construído inválido. Deve ser entre 1886 e o ano corrente.");
            alertYear.showAndWait();
            return;
        }


        if (doorsTF.getText().isEmpty() || doorsTF.getText().isBlank()) {
            Alert alertDoors = new Alert(Alert.AlertType.ERROR, "Campo Numero De Portas sem dados");
            alertDoors.showAndWait();
            return;
        } else {
            if (segmentCB.getValue() != "Scooter") {
                String regexDoor = "^[1-6]$";
                Pattern patternDoor = Pattern.compile(regexDoor);
                Matcher matcherDoor = patternDoor.matcher(doorsTF.getText());
                if (!matcherDoor.matches()) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR, "Campo Numero De Portas inválido.Campo Numero de portas tem que ser entre 1 e 6,só pode conter um digito");
                    alert1.showAndWait();
                    return;
                }
            }
        }

        String km = "0";
        if (!kmTF.getText().isEmpty() || !kmTF.getText().isBlank()) {
            String regex = "^\\d+$";
            Pattern patternKM = Pattern.compile(regex);
            Matcher matcher = patternKM.matcher(km);
            if (!matcher.matches()) {
                Alert alertKM = new Alert(Alert.AlertType.ERROR, "Campo KMs inválido. Campo KMs só pode conter dígitos.");
                alertKM.showAndWait();
                return;
            }
        }

        if (engineTF.getText().isEmpty() || engineTF.getText().isBlank()) {
            Alert alertCapacity = new Alert(Alert.AlertType.ERROR, "Campo Cilindrada sem dados");
            alertCapacity.showAndWait();
            return;
        } else {
            String regexCapacity = "^\\d+$";
            Pattern patternCapacity = Pattern.compile(regexCapacity, Pattern.CASE_INSENSITIVE);
            Matcher matcherCapacity = patternCapacity.matcher(engineTF.getText());
            if (!matcherCapacity.matches()) {
                Alert alertCapacity = new Alert(Alert.AlertType.ERROR, "Campo Cilindrada inválido. O campo Cilindrada deve conter apenas dígitos");
                alertCapacity.showAndWait();
                return;
            } else {
                int capacity = Integer.parseInt(engineTF.getText());
                if (capacity < 50 || capacity > 8000) {
                    Alert alertCapacity = new Alert(Alert.AlertType.ERROR, "Campo Cilindrada inválido.Campo Cilindrada tem que ser entre 50 e 8000");
                    alertCapacity.showAndWait();
                    return;
                }

            }
        }

        if (powerTF.getText().isEmpty() || powerTF.getText().isBlank()) {
            Alert alertPotency = new Alert(Alert.AlertType.ERROR, "Campo Pontência sem dados");
            alertPotency.showAndWait();
            return;
        } else {
            String regexPotency = "^\\d+$";
            Pattern patternPontency = Pattern.compile(regexPotency, Pattern.CASE_INSENSITIVE);
            Matcher matcherPotency = patternPontency.matcher(powerTF.getText());
            if (!matcherPotency.matches()) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Campo Potência inválido. O campo Potência deve conter apenas dígitos");
                alert1.showAndWait();
                return;
            } else {
                int potency = Integer.parseInt(powerTF.getText());
                if (potency < 1 || potency > 10000) {
                    Alert alertPotency = new Alert(Alert.AlertType.ERROR, "Camppo Potência inválido.Campo Potência deve ser superior a 0 e inferior a 10000 ");
                    alertPotency.showAndWait();
                    return;
                }

            }

        }

        if (colorTF.getText().isEmpty() || colorTF.getText().isBlank()) {
            Alert alertColor = new Alert(Alert.AlertType.ERROR, "Campo Cor sem dados");
            alertColor.showAndWait();
            return;
        } else {
            String regexColor = "^\\D*$";
            Pattern patternColor = Pattern.compile(regexColor, Pattern.CASE_INSENSITIVE);
            Matcher matcherColor = patternColor.matcher(colorTF.getText());
            if (!matcherColor.matches()) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "Campo Cor não pode conter dígitos");
                alert1.showAndWait();
                return;
            }
        }

        if (priceTC.getText().isBlank() || priceTC.getText().isEmpty()) {
            Alert alertPrice = new Alert(Alert.AlertType.ERROR, "Campo Preço Diário sem dados");
            alertPrice.showAndWait();
            return;
        } else {
            String regexPrice = "^\\d+$";
            Pattern patternPrice = Pattern.compile(regexPrice);
            Matcher matcherPrice = patternPrice.matcher(priceTC.getText());
            if (!matcherPrice.matches()) {
                Alert alertPriceComparing = new Alert(Alert.AlertType.ERROR, "Campo Preço Diário inválido. O campo Preço Diario deve conter apenas dígitos");
                alertPriceComparing.showAndWait();
                return;
            } else {
                int pricecomparing = Integer.parseInt(priceTC.getText());
                if (pricecomparing < 1) {
                    Alert alertPotency = new Alert(Alert.AlertType.ERROR, "Campo Preço Diário inválido. O preço deve ser maior que 0");
                    alertPotency.showAndWait();
                    return;
                }
            }
        }


        if (selling_priceTC.getText().isEmpty() || selling_priceTC.getText().isBlank()) {
            Alert alertComparingPrice = new Alert(Alert.AlertType.ERROR, "Campo Preço de venda tem de ser declarado");
            alertComparingPrice.showAndWait();
            return;
        } else {
            String regexSelling = "^\\d+$";
            Pattern patternSellingPrice = Pattern.compile(regexSelling);
            Matcher matcherSellingPrice = patternSellingPrice.matcher(selling_priceTC.getText());
            if (!matcherSellingPrice.matches()) {
                Alert alertSellingPrice = new Alert(Alert.AlertType.ERROR, "Campo Preço De Venda inválido. Campo Preço de venda só pode conter dígitos");
                alertSellingPrice.showAndWait();
                return;
            } else {
                int sellingPrice = Integer.parseInt(selling_priceTC.getText());
                if (sellingPrice < 1) {
                    Alert alertComparingPrice = new Alert(Alert.AlertType.ERROR, "Campo Preço de venda tem de ser maior que 0");
                    alertComparingPrice.showAndWait();
                    return;
                }
            }
        }


        VehicleCreateDTO vehicleCreateDTO = new VehicleCreateDTO(registrationTF.getText(),
                brandCB.getValue(),
                modelCB.getValue().toString(),
                segmentCB.getValue(),
                fuelCB.getValue(),
                Integer.parseInt(yearTF.getText()),
                doorsTF.getText(),
                Integer.parseInt(km),
                Integer.parseInt(engineTF.getText()),
                Integer.parseInt(powerTF.getText()),
                colorTF.getText(),
                typeCB.getValue(),
                Double.parseDouble(selling_priceTC.getText()),
                Double.parseDouble(priceTC.getText()),
                false

        );

        Response vehicleResponse = vehicleCRUDService.createVehicle(vehicleCreateDTO);

        Alert alert1;
        if (vehicleResponse.getStatus().equals(Responses.Vehicle.Create.CREATED_SUCCESS)) {
            alert1 = new Alert(Alert.AlertType.INFORMATION, "Veículo criado com sucesso");
        } else {
            alert1 = new Alert(Alert.AlertType.ERROR, "Ocorreu um erro ao criar o Veículo");
        }
        alert1.showAndWait();
    }


    @FXML
    void retrocederClicar(MouseEvent event) {

    }

    private UserLoggedDTO userLoggedDTO;

    private final IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();

    @FXML
    public void initialize() {
        userLoggedDTO = Session.getUser();

        List<String> brandNames = Cars.iterateByBrands();
        ObservableList<String> brandNamesOL = FXCollections.observableArrayList(brandNames);

        brandCB.setItems(brandNamesOL);

        brandCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Add your code to handle the selection change here
                Cars[] specificModels = Cars.getSpecificModels(newValue); // Substituir "Abarth" pelo texto selecionado no dropdown
                ObservableList<Cars> modelsOL = FXCollections.observableArrayList(specificModels);
                modelCB.setItems(modelsOL);
            }
        });

        List<String> typesegments = Segments.iterateBySegments();
        ObservableList<String> typeSegmentsOl = FXCollections.observableArrayList(typesegments);
        segmentCB.setItems(typeSegmentsOl);

        segmentCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Code to handle the change
            if (segmentCB.getValue().equals("Scooter")) {
                doorsTF.setDisable(true);
                doorsTF.setText("0");
            } else {
                doorsTF.setDisable(false);
            }
        });

        List<String> typeFuel = Fuel.iterateByFuel();
        ObservableList<String> typeFuelOl = FXCollections.observableArrayList(typeFuel);
        fuelCB.setItems(typeFuelOl);

        List<String> typeCar = TypeCars.iterateByType();
        ObservableList<String> typeCarOl = FXCollections.observableArrayList(typeCar);
        typeCB.setItems(typeCarOl);
    }
}
