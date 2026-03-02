package com.fleetforyou.fleetforyou.Infrastructure.DAO;

import com.fleetforyou.fleetforyou.Application.Services.ClientService.IClientCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.UserService.IUserCRUDService;
import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Enums.Responses;
import com.fleetforyou.fleetforyou.Domain.Models.*;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IRentalDAO;
import com.fleetforyou.fleetforyou.Infrastructure.Connection.DBConnection;
import javafx.scene.control.Alert;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class RentalDAO implements IRentalDAO {
    /**
     * Faz uma query à base de dados para retornar todos os Stand
     *
     * @return
     */
    private IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();
    private IUserCRUDService userCRUDService = InjectorProvider.getUserCRUDService();
    private IClientCRUDService clientCRUDService = InjectorProvider.getClientCRUDService();

    public List<Rental> getAll() {
        List<Rental> list = new ArrayList<>();

        String getAllQuery = """
                SELECT *
                FROM "Rental"
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
                int id_rental = rsGetAll.getInt("id_rental");
                Date date_start = rsGetAll.getDate("date_start");
                Date date_end = rsGetAll.getDate("date_end");
                Date date_return = rsGetAll.getDate("date_return");
                int id_vehicle = rsGetAll.getInt("id_vehicle");
                int id_client = rsGetAll.getInt("id_client");
                int id_user = rsGetAll.getInt("id_user");
                String vehicle_status = rsGetAll.getString("vehicle_status");

                Vehicle vehicle = null;
                Client client = null;
                User user = null;

                PreparedStatement statementGetVehicle = DBConnection.getConnection().prepareStatement(getVehicleByRental);
                statementGetVehicle.setInt(1, id_vehicle);

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
                statementGetClient.setInt(1, id_client);

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
                statementGetUser.setInt(1, id_user);

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
                    statementGetPerms.setInt(1, id_user);

                    ResultSet rsGetPerms = statementGetPerms.executeQuery();

                    while (rsGetPerms.next()) {
                        String perm = rsGetPerms.getString("description");

                        permissions.add(Permission.fromString(perm));
                    }

                    user = new User(id_user2, name, phone_number, address, postal_code, local, district, email, password, stand, permissions, deleted);
                }

                Rental RentalCar = new Rental(id_rental, date_start, date_end, date_return, vehicle, client, user, vehicle_status);
                list.add(RentalCar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }

        return list;
    }

    @Override
    public Optional<Rental> getById(int id) {
        // Define a consulta SQL para obter um utilizador com base no ID
        String getByIdQuery = """
                SELECT *
                FROM "Rental"
                WHERE "id_rental" = ?
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
                int id_rental = rsGetById.getInt("id_rental");
                Date date_start = rsGetById.getDate("date_start");
                Date date_end = rsGetById.getDate("date_end");
                Date date_return = rsGetById.getDate("date_return");
                int id_vehicle = rsGetById.getInt("id_vehicle");
                int id_client = rsGetById.getInt("id_client");
                int id_user = rsGetById.getInt("id_user");
                String vehicle_status = rsGetById.getString("vehicle_status");

                Response vehicleResponse = vehicleCRUDService.getVehicleById(id_vehicle);
                if (vehicleResponse.getStatus().equals(Responses.Vehicle.Find.FOUND)) {
                    Response userResponse = userCRUDService.getUserById(id_user);
                    if (userResponse.getStatus().equals(Responses.User.Find.FOUND)) {
                        Response ClientResponse = clientCRUDService.getClientById(id_client);
                        if (ClientResponse.getStatus().equals(Responses.Client.Find.FOUND)) {
                            Rental rental = new Rental(id_rental, date_start, date_end, date_return, (Vehicle) vehicleResponse.getData(), (Client) ClientResponse.getData(), (User) userResponse.getData(), vehicle_status);

                            // Define o resultado como um Optional contendo o cliente
                            return Optional.of(rental);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Cliente não encontrado");//mostra um alerta do erro
                            alert.showAndWait();//espera que o utilizador interega
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "User não encontrado");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Veiculo não encontrado");
                    alert.showAndWait();
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

    @Override
    public Insert insert(Rental data) {
        // Define a consulta SQL para adicionar um novo utilizador na tabela 'Stand' com valores parametizados
        String insertQuery = """
                INSERT INTO "Rental" ("date_start", "date_end", "id_vehicle", "id_client", "id_user", "vehicle_status")
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            // Prepara uma declaração PreparedStatement para a inserção dos dados
            PreparedStatement statementInsertStand = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            Date date_start = new Date(data.getDate_start().getTime());
            Date date_eend = new Date(data.getDate_end().getTime());
            
            //Define os valores dos parâmetros na consulta tendo em conta os atributos do objeto 'data'
            statementInsertStand.setDate(1, date_start);
            statementInsertStand.setDate(2, date_eend);
            statementInsertStand.setInt(3, data.getVehicle().getId_vehicle());
            statementInsertStand.setInt(4, data.getClient().getId());
            statementInsertStand.setInt(5, data.getUser().getId_user());
            statementInsertStand.setString(6, data.getVehicleStatus());

            // Executa a inserção e obtém o número de linhas afetadas
            int affectedLines = statementInsertStand.executeUpdate();

            // Se uma linha for afetada (sucesso na inserção), retorna 'true'
            if (affectedLines == 1) {
                // Inserção bem-sucedida, inserir as roles no utilizador
                ResultSet generatedKeys = statementInsertStand.getGeneratedKeys();
                int id_rental = -1;

                if (generatedKeys.next()) {
                    id_rental = generatedKeys.getInt(1);
                }

                if (id_rental != -1) {

                    DBConnection.getConnection().commit();
                    DBConnection.getConnection().setAutoCommit(true);
                    return new Insert(true, id_rental);
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

    @Override
    public boolean update(Rental data) {
        // Define a consulta SQL para atualizar os dados de um utilizador com base ID
        String updateQuery = """
                UPDATE "Rental"
                SET "date_start" = ?, "date_end" = ?, "date_return" = ?, "id_vehicle" = ?, "id_client" = ?, "id_user" = ?, "vehicle_status" = ?
                WHERE "id_rental" = ?
                """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            // Prepara a declaração PreparedStatement para a atualização
            PreparedStatement statementUpdate = DBConnection.getConnection().prepareStatement(updateQuery);

            Date date_start = new Date(data.getDate_start().getTime());
            Date date_end = new Date(data.getDate_end().getTime());
            Date date_return = new Date(data.getDate_return().getTime());

            // Define os valores dos parâmetros na consulta com base nos atributos do objeto 'data'
            statementUpdate.setDate(1, date_start);
            statementUpdate.setDate(2, date_end);
            statementUpdate.setDate(3, date_return);
            statementUpdate.setInt(4, data.getVehicle().getId_vehicle());
            statementUpdate.setInt(5, data.getClient().getId());
            statementUpdate.setInt(6, data.getUser().getId_user());
            statementUpdate.setString(7, data.getVehicleStatus());

            statementUpdate.setInt(8, data.getId_rental());

            // Executa a atualização e obtém o número de linhas afetadas
            int affectedLines = statementUpdate.executeUpdate();

            // Se uma linha for afetada (sucesso na atualização), retorna 'true'
            if (affectedLines == 1) {
                DBConnection.getConnection().commit();
                DBConnection.getConnection().setAutoCommit(true);

                return true;
            } else {
                // Se o update não for bem-sucedido, faz um rollback da transação
                DBConnection.getConnection().rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.closeConnection();
        }
        // Se ocorrer um erro, retorne 'false'.
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Optional<Rental> getByClientId(int id){
        // Define a consulta SQL para obter um utilizador com base no ID
        String getByIdQuery = """
                SELECT *
                FROM "Rental"
                WHERE "id_client" = ?
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
                int id_rental = rsGetById.getInt("id_rental");
                Date date_start = rsGetById.getDate("date_start");
                Date date_end = rsGetById.getDate("date_end");
                Date date_return = rsGetById.getDate("date_return");
                int id_vehicle = rsGetById.getInt("id_vehicle");
                int id_client = rsGetById.getInt("id_client");
                int id_user = rsGetById.getInt("id_user");
                String vehicle_status = rsGetById.getString("vehicle_status");

                Response vehicleResponse = vehicleCRUDService.getVehicleById(id_vehicle);
                if (vehicleResponse.getStatus().equals(Responses.Vehicle.Find.FOUND)) {
                    Response userResponse = userCRUDService.getUserById(id_user);
                    if (userResponse.getStatus().equals(Responses.User.Find.FOUND)) {
                        Response ClientResponse = clientCRUDService.getClientById(id_client);
                        if (ClientResponse.getStatus().equals(Responses.Client.Find.FOUND)) {
                            Rental rental = new Rental(id_rental, date_start, date_end, date_return, (Vehicle) vehicleResponse.getData(), (Client) ClientResponse.getData(), (User) userResponse.getData(), vehicle_status);

                            // Define o resultado como um Optional contendo o cliente
                            return Optional.of(rental);
                        }
                    }
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

    @Override
    public Optional<Rental> getByVehicleId(int id){
        // Define a consulta SQL para obter um utilizador com base no ID
        String getByIdQuery = """
                SELECT *
                FROM "Rental"
                WHERE "id_vehicle" = ?
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
                int id_rental = rsGetById.getInt("id_rental");
                Date date_start = rsGetById.getDate("date_start");
                Date date_end = rsGetById.getDate("date_end");
                Date date_return = rsGetById.getDate("date_return");
                int id_vehicle = rsGetById.getInt("id_vehicle");
                int id_client = rsGetById.getInt("id_client");
                int id_user = rsGetById.getInt("id_user");
                String vehicle_status = rsGetById.getString("vehicle_status");

                Response vehicleResponse = vehicleCRUDService.getVehicleById(id_vehicle);
                if (vehicleResponse.getStatus().equals(Responses.Vehicle.Find.FOUND)) {
                    Response userResponse = userCRUDService.getUserById(id_user);
                    if (userResponse.getStatus().equals(Responses.User.Find.FOUND)) {
                        Response ClientResponse = clientCRUDService.getClientById(id_client);
                        if (ClientResponse.getStatus().equals(Responses.Client.Find.FOUND)) {
                            Rental rental = new Rental(id_rental, date_start, date_end, date_return, (Vehicle) vehicleResponse.getData(), (Client) ClientResponse.getData(), (User) userResponse.getData(), vehicle_status);

                            // Define o resultado como um Optional contendo o cliente
                            return Optional.of(rental);
                        }
                    }
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

    @Override
    public Optional<Rental> hasRentalClient(int id) {
        // Define a consulta SQL para obter um utilizador com base no ID
        String getByIdQuery = """
                SELECT *
                FROM "Rental"
                WHERE "id_client" = ? AND "date_return" IS NULL
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
                int id_rental = rsGetById.getInt("id_rental");
                Date date_start = rsGetById.getDate("date_start");
                Date date_end = rsGetById.getDate("date_end");
                Date date_return = rsGetById.getDate("date_return");
                int id_vehicle = rsGetById.getInt("id_vehicle");
                int id_client = rsGetById.getInt("id_client");
                int id_user = rsGetById.getInt("id_user");
                String vehicle_status = rsGetById.getString("vehicle_status");

                Response vehicleResponse = vehicleCRUDService.getVehicleById(id_vehicle);
                if (vehicleResponse.getStatus().equals(Responses.Vehicle.Find.FOUND)) {
                    Response userResponse = userCRUDService.getUserById(id_user);
                    if (userResponse.getStatus().equals(Responses.User.Find.FOUND)) {
                        Response ClientResponse = clientCRUDService.getClientById(id_client);
                        if (ClientResponse.getStatus().equals(Responses.Client.Find.FOUND)) {
                            Rental rental = new Rental(id_rental, date_start, date_end, date_return, (Vehicle) vehicleResponse.getData(), (Client) ClientResponse.getData(), (User) userResponse.getData(), vehicle_status);

                            // Define o resultado como um Optional contendo o cliente
                            return Optional.of(rental);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Cliente não encontrado");//mostra um alerta do erro
                            alert.showAndWait();//espera que o utilizador interega
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "User não encontrado");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Veiculo não encontrado");
                    alert.showAndWait();
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

    @Override
    public Optional<Rental> hasRentalVehicle(int id) {
        // Define a consulta SQL para obter um utilizador com base no ID
        String getByIdQuery = """
                SELECT *
                FROM "Rental"
                WHERE "id_vehicle" = ? AND "date_return" IS NULL
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
                int id_rental = rsGetById.getInt("id_rental");
                Date date_start = rsGetById.getDate("date_start");
                Date date_end = rsGetById.getDate("date_end");
                Date date_return = rsGetById.getDate("date_return");
                int id_vehicle = rsGetById.getInt("id_vehicle");
                int id_client = rsGetById.getInt("id_client");
                int id_user = rsGetById.getInt("id_user");
                String vehicle_status = rsGetById.getString("vehicle_status");

                Response vehicleResponse = vehicleCRUDService.getVehicleById(id_vehicle);
                if (vehicleResponse.getStatus().equals(Responses.Vehicle.Find.FOUND)) {
                    Response userResponse = userCRUDService.getUserById(id_user);
                    if (userResponse.getStatus().equals(Responses.User.Find.FOUND)) {
                        Response ClientResponse = clientCRUDService.getClientById(id_client);
                        if (ClientResponse.getStatus().equals(Responses.Client.Find.FOUND)) {
                            Rental rental = new Rental(id_rental, date_start, date_end, date_return, (Vehicle) vehicleResponse.getData(), (Client) ClientResponse.getData(), (User) userResponse.getData(), vehicle_status);

                            // Define o resultado como um Optional contendo o cliente
                            return Optional.of(rental);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Cliente não encontrado");//mostra um alerta do erro
                            alert.showAndWait();//espera que o utilizador interega
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "User não encontrado");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Veiculo não encontrado");
                    alert.showAndWait();
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

    @Override
    public Optional<List<Client>> getAllNoRentalClient() {
        List<Client> listClient = new ArrayList<>();

        // Define a consulta SQL para obter um utilizador com base no ID
        String getAllNoRentalClientQuery = """
            SELECT "c".*
            FROM "Client" "c"
            WHERE "c"."deleted" = 0
            AND NOT EXISTS (
                SELECT 1
                FROM "Rental" "r"
                WHERE "c"."id_client" = "r"."id_client"
                AND "r"."date_return" IS NULL
            )
        """;
        // must select from "Client" and use inner joins to check for the client id in the "Rental"

        try {
            // Prepara uma declaração PreparedStatement para a consulta de dados
            PreparedStatement statementGetAllNoRentalClient = DBConnection.getConnection().prepareStatement(getAllNoRentalClientQuery);

            // Executa a consulta e obtém o resultado
            ResultSet rsGetAllNoRentalClient = statementGetAllNoRentalClient.executeQuery();

            // Verifca se existe um resultado válido
            while (rsGetAllNoRentalClient.next()) {
                int id = rsGetAllNoRentalClient.getInt("id_client");
                String name = rsGetAllNoRentalClient.getString("name");
                String nc = rsGetAllNoRentalClient.getString("vat_number");
                String phone_number = rsGetAllNoRentalClient.getString("phone_number");
                String address = rsGetAllNoRentalClient.getString("address");
                String postal_code = rsGetAllNoRentalClient.getString("postal_code");
                String local = rsGetAllNoRentalClient.getString("local");
                String district = rsGetAllNoRentalClient.getString("district");
                boolean deleted = rsGetAllNoRentalClient.getBoolean("deleted");

                Client client = new Client(id, name, nc, phone_number, address, postal_code, local, district, deleted);

                listClient.add(client);
            }
            return Optional.of(listClient);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public Optional<List<Vehicle>> getAllNoRentalVehicle() {
        List<Vehicle> listVehicle = new ArrayList<>();

        // Define a consulta SQL para obter um utilizador com base no ID
        String getAllNoRentalVehicleQuery = """
            SELECT "v".*
            FROM "Vehicle" "v"
            WHERE "v"."deleted" = 0
            AND NOT EXISTS (
                SELECT 1
                FROM "Rental" "r"
                WHERE "v"."id_vehicle" = "r"."id_vehicle"
                AND "r"."date_return" IS NULL
            )
        """;

        try {
            // Prepara uma declaração PreparedStatement para a consulta de dados
            PreparedStatement statementGetAllNoRentalVehicleQuery = DBConnection.getConnection().prepareStatement(getAllNoRentalVehicleQuery);

            // Executa a consulta e obtém o resultado
            ResultSet rsGetAllNoRentalVehicle = statementGetAllNoRentalVehicleQuery.executeQuery();

            // Verifca se existe um resultado válido
            while (rsGetAllNoRentalVehicle.next()) {
                int id_vehicle = rsGetAllNoRentalVehicle.getInt("id_vehicle");
                String registration = rsGetAllNoRentalVehicle.getString("registration");
                String brand = rsGetAllNoRentalVehicle.getString("brand");
                String model = rsGetAllNoRentalVehicle.getString("model");
                String segment = rsGetAllNoRentalVehicle.getString("segment");
                boolean status = rsGetAllNoRentalVehicle.getBoolean("status");
                String fuel = rsGetAllNoRentalVehicle.getString("fuel");
                int year_built = rsGetAllNoRentalVehicle.getInt("year_built");
                String num_doors = rsGetAllNoRentalVehicle.getString("num_doors");
                int num_km = rsGetAllNoRentalVehicle.getInt("num_km");
                int engine_capacity = rsGetAllNoRentalVehicle.getInt("engine_capacity");
                int potency = rsGetAllNoRentalVehicle.getInt("potency");
                String color = rsGetAllNoRentalVehicle.getString("color");
                double rental_price = rsGetAllNoRentalVehicle.getDouble("rental_price");
                String type = rsGetAllNoRentalVehicle.getString("type");
                boolean deleted = rsGetAllNoRentalVehicle.getBoolean("deleted");
                double selling_price = rsGetAllNoRentalVehicle.getDouble("selling_price");
                boolean sold = rsGetAllNoRentalVehicle.getBoolean("sold");

                Vehicle vehicle = new Vehicle(id_vehicle, registration, brand, model, segment, status, fuel, year_built, num_doors, num_km, engine_capacity, potency, color, rental_price, type, deleted, selling_price, sold);

                listVehicle.add(vehicle);
            }
            return Optional.of(listVehicle);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            DBConnection.closeConnection();
        }
    }
}