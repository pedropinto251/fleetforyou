package com.fleetforyou.fleetforyou.Infrastructure.DAO;

import com.fleetforyou.fleetforyou.Application.Services.RentalService.IRentalCRUDService;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.*;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Infrastructure.Connection.DBConnection;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IFormDAO;
import javafx.scene.control.Alert;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class FormDAO implements IFormDAO {
    /**
     * Faz uma query à base de dados para retornar todos os Stand
     * @return
     */

    IRentalCRUDService rentalCRUDService = InjectorProvider.getRentalCRUDService();

    @Override
    public List<Form> getAll() {
        List<Form> list = new ArrayList<>();

        String getAllQuery = """
                SELECT *
                FROM "Form"
                """;

        String getRentalById = """
                SELECT *
                FROM "Rental"
                WHERE "id_rental" = ?
                """;

        String getVehicleByRental = """
                SELECT *
                FROM "Vehicle"
                WHERE "id_vehicle" = ?
                """;

        String getClientByRental = """
                SELECT *
                FROM "Client"
                WHERE "id_client" = ?
                """;

        String getUserByRental = """
                SELECT *
                FROM "User"
                WHERE "id_user" = ?
                """;

        String getPermsById = """
                SELECT "p"."description"
                FROM "Permission" "p"
                JOIN "User_Permission" "up" ON "p"."id_permission" = "up"."id_permission"
                WHERE "up"."id_user" = ?
                """;

        String getStandByUser = """
                SELECT *
                FROM "Stand"
                WHERE "id_stand" = ?
                """;

        try {
            PreparedStatement statementGetAll = DBConnection.getConnection().prepareStatement(getAllQuery);

            ResultSet rsGetAll = statementGetAll.executeQuery();

            while (rsGetAll.next()) {
                int id_form = rsGetAll.getInt("id_form");
                int satisfaction = rsGetAll.getInt("satisfaction");
                String observation = rsGetAll.getString("observation");
                int id_rental = rsGetAll.getInt("id_rental");
                Rental rental = null;

                PreparedStatement statementGetRental = DBConnection.getConnection().prepareStatement(getRentalById);
                statementGetRental.setInt(1, id_rental);

                ResultSet rsGetRental = statementGetRental.executeQuery();

                if (rsGetRental.next()){
                    int id_rentalRental = rsGetRental.getInt("id_rental");
                    Date date_startRental = rsGetRental.getDate("date_start");
                    Date date_endRental = rsGetRental.getDate("date_end");
                    Date date_returnRental = rsGetRental.getDate("date_return");
                    int id_vehicleRental = rsGetRental.getInt("id_vehicle");
                    int id_clientRental = rsGetRental.getInt("id_client");
                    int id_userRental = rsGetRental.getInt("id_user");
                    String vehicle_statusRental = rsGetRental.getString("vehicle_status");

                    Vehicle vehicle = null;
                    Client client = null;
                    User user = null;

                    PreparedStatement statementGetVehicle = DBConnection.getConnection().prepareStatement(getVehicleByRental);
                    statementGetVehicle.setInt(1, id_vehicleRental);

                    ResultSet rsGetVehicle = statementGetVehicle.executeQuery();

                    if (rsGetVehicle.next()){
                        int id_vehicle2 = rsGetVehicle.getInt("id_vehicle");
                        String registration = rsGetVehicle.getString("registration");
                        String brand = rsGetVehicle.getString("brand");
                        String model = rsGetVehicle.getString("model");
                        String segment = rsGetVehicle.getString("segment");
                        boolean status = rsGetVehicle.getBoolean("status");
                        String fuel = rsGetVehicle.getString("fuel");
                        int year_built = rsGetVehicle.getInt("year_built");
                        String num_doors = rsGetVehicle.getString("num_doors");
                        int num_km = rsGetVehicle.getInt("num_km");
                        int engine_capacity = rsGetVehicle.getInt("engine_capacity");
                        int potency = rsGetVehicle.getInt("potency");
                        String color = rsGetVehicle.getString("color");
                        double rental_price = rsGetVehicle.getDouble("rental_price");
                        String type = rsGetVehicle.getString("type");
                        boolean deleted = rsGetVehicle.getBoolean("deleted");
                        double selling_price = rsGetVehicle.getDouble("selling_price");
                        boolean sold = rsGetVehicle.getBoolean("sold");

                        vehicle = new Vehicle(id_vehicle2, registration, brand, model, segment, status, fuel, year_built, num_doors, num_km, engine_capacity, potency, color, rental_price, type, deleted, selling_price,sold );
                    }

                    PreparedStatement statementGetClient = DBConnection.getConnection().prepareStatement(getClientByRental);
                    statementGetClient.setInt(1, id_clientRental);

                    ResultSet rsGetClient = statementGetClient.executeQuery();

                    if (rsGetClient.next()){
                        int id_client2 = rsGetClient.getInt("id_client");
                        String name = rsGetClient.getString("name");
                        String nc = rsGetClient.getString("vat_number");
                        String phone_number = rsGetClient.getString("phone_number");
                        String address = rsGetClient.getString("address");
                        String postal_code = rsGetClient.getString("postal_code");
                        String local = rsGetClient.getString("local");
                        String district = rsGetClient.getString("district");
                        boolean deleted = rsGetClient.getBoolean("deleted");

                        client = new Client(id_client2, name, nc, phone_number, address, postal_code, local, district, deleted);
                    }

                    PreparedStatement statementGetUser = DBConnection.getConnection().prepareStatement(getUserByRental);
                    statementGetUser.setInt(1, id_userRental);

                    ResultSet rsGetUser = statementGetUser.executeQuery();

                    if (rsGetUser.next()){
                        int id_user2 = rsGetUser.getInt("id_user");
                        String name = rsGetUser.getString("name");
                        String phone_number = rsGetUser.getString("phone_number");
                        String address = rsGetUser.getString("address");
                        String postal_code = rsGetUser.getString("postal_code");
                        String local = rsGetUser.getString("local");
                        String district = rsGetUser.getString("district");
                        String email = rsGetUser.getString("email");
                        String password = rsGetUser.getString("password");
                        int id_stand = rsGetUser.getInt("id_stand");
                        boolean deleted = rsGetUser.getBoolean("deleted");
                        Stand stand = null;

                        PreparedStatement statementGetStand = DBConnection.getConnection().prepareStatement(getStandByUser);
                        statementGetStand.setInt(1, id_stand);

                        ResultSet rsGetStand = statementGetStand.executeQuery();

                        if (rsGetStand.next()){
                            int id_standStand = rsGetStand.getInt("id_stand");
                            String nameStand = rsGetStand.getString("name");
                            String phone_numberStand = rsGetStand.getString("phone_number");
                            String addressStand = rsGetStand.getString("address");
                            String postal_codeStand = rsGetStand.getString("postal_code");
                            String localStand = rsGetStand.getString("local");
                            String districtStand = rsGetStand.getString("district");
                            boolean deletedStand = rsGetStand.getBoolean("deleted");

                            stand = new Stand(id_standStand, nameStand, phone_numberStand, addressStand, postal_codeStand, localStand, districtStand, deletedStand);

                        }

                        Set<Permission> permissions = new HashSet<>();

                        PreparedStatement statementGetPerms = DBConnection.getConnection().prepareStatement(getPermsById);
                        statementGetPerms.setInt(1, id_userRental);

                        ResultSet rsGetPerms = statementGetPerms.executeQuery();

                        while (rsGetPerms.next()) {
                            String perm = rsGetPerms.getString("description");

                            permissions.add(Permission.fromString(perm));
                        }

                        user = new User(id_user2, name, phone_number, address, postal_code, local, district, email, password, stand, permissions, deleted);
                    }

                    rental = new Rental(id_rentalRental, date_startRental, date_endRental, date_returnRental, vehicle, client, user, vehicle_statusRental);
                }

                Form form = new Form(id_form,satisfaction,observation,rental);

                list.add(form);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }

        return list;
    }

    @Override
    public Optional<Form> getById(int id) {
        // Define a consulta SQL para obter um utilizador com base no ID
        String getByIdQuery = """
                SELECT *
                FROM "Form"
                WHERE "id_form" = ?
                """;

        try {
            // Prepara uma declaração PreparedStatement para a consulta de dados
            PreparedStatement statementGetById = DBConnection.getConnection().prepareStatement(getByIdQuery);

            // Define o valor do parâmetro na consulta com base no ID fornecido
            statementGetById.setInt(1, id);

            // Executa a consulta e obtém o resultado
            ResultSet rsGetById = statementGetById.executeQuery();

            // Verifca se existe um resultado válido
            if (rsGetById.next()) {
                int id_form = rsGetById.getInt("id_form");
                int satisfaction = rsGetById.getInt("satisfaction");
                String observation = rsGetById.getString("observation");
                int id_rental = rsGetById.getInt("id_rental");

                Response rentalResponse = rentalCRUDService.getRentalById(id_rental);

                if (rentalResponse.getStatus().equals(Responses.Form.Find.FOUND)){
                    Rental rental = (Rental) rentalResponse.getData();

                    Form form = new Form(id_form, satisfaction, observation, rental);

                    return Optional.of(form);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            DBConnection.closeConnection();
        }
        // Se o cliente não for encontrado ou se ocorrer um erro, retorna 'Optional.empty()'
        return Optional.empty();
    }

    /**
     * Recebe dados de um Stand e então faz uma query para inserir esse Stand na base de dados
     * @param data
     * @return
     */
    @Override
    public Insert insert(Form data) {
        // Define a consulta SQL para adicionar um novo utilizador na tabela 'Stand' com valores parametizados
        String insertQuery = """
                INSERT INTO "Form" ("satisfaction", "observation", "id_rental")
                VALUES (?, ?, ?)
                """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            // Prepara uma declaração PreparedStatement para a inserção dos dados
            PreparedStatement statementInsertStand = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            //Define os valores dos parâmetros na consulta tendo em conta os atributos do objeto 'data'
            statementInsertStand.setInt(1, data.getSatisfaction());
            statementInsertStand.setString(2, data.getObservation());
            statementInsertStand.setInt(3,data.getRental().getId_rental());

            // Executa a inserção e obtém o número de linhas afetadas
            int affectedLines = statementInsertStand.executeUpdate();

            // Se uma linha for afetada (sucesso na inserção), retorna 'true'
            if (affectedLines == 1) {
                // Inserção bem-sucedida, inserir as roles no utilizador
                ResultSet generatedKeys = statementInsertStand.getGeneratedKeys();
                int id_form = -1;

                if (generatedKeys.next()) {
                    id_form = generatedKeys.getInt(1);
                }

                if (id_form != -1) {

                    DBConnection.getConnection().commit();
                    DBConnection.getConnection().setAutoCommit(true);

                    return new Insert(true, id_form);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

            // Em caso de erro, desfaz a transação
            try {
                DBConnection.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            // Restaura o comportamento padrão de commit automático
            try {
                DBConnection.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            DBConnection.closeConnection();
        }
        // Se ocorrer um erro, retorna 'false'
        return new Insert(false);
    }
}
