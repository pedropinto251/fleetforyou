package com.fleetforyou.fleetforyou.Infrastructure.DAO;

import com.fleetforyou.fleetforyou.Application.Services.VehicleService.IVehicleCRUDService;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Infrastructure.Connection.DBConnection;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IVehicleDAO;

import java.sql.*;
import java.util.*;

public class VehicleDAO implements IVehicleDAO {

    private final IVehicleCRUDService vehicleCRUDService = InjectorProvider.getVehicleCRUDService();

    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> list = new ArrayList<>();

        String getAllQuery = """
                SELECT *
                FROM "Vehicle"
                WHERE "deleted" = 0
                """;

        try {
            PreparedStatement statementGetAll = DBConnection.getConnection().prepareStatement(getAllQuery);

            ResultSet rsGetAll = statementGetAll.executeQuery();

            while (rsGetAll.next()) {
                int id_vehicle = rsGetAll.getInt("id_vehicle");
                String registration = rsGetAll.getString("registration");
                String brand = rsGetAll.getString("brand");
                String model = rsGetAll.getString("model");
                String segment = rsGetAll.getString("segment");
                boolean status = rsGetAll.getBoolean("status");
                String fuel = rsGetAll.getString("fuel");
                int year_built = rsGetAll.getInt("year_built");
                String num_doors = rsGetAll.getString("num_doors");
                int num_km = rsGetAll.getInt("num_km");
                int engine_capacity = rsGetAll.getInt("engine_capacity");
                int potency = rsGetAll.getInt("potency");
                String color = rsGetAll.getString("color");
                double rental_price = rsGetAll.getDouble("rental_price");
                String type = rsGetAll.getString("type");
                boolean deleted = rsGetAll.getBoolean("deleted");
                double selling_price = rsGetAll.getDouble("selling_price");
                boolean sold = rsGetAll.getBoolean("sold");

                Vehicle vehicle = new Vehicle(id_vehicle, registration, brand, model, segment, status, fuel, year_built, num_doors, num_km, engine_capacity, potency, color, rental_price, type, deleted, selling_price, sold);

                list.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }

        return list;
    }

    @Override
    public Optional<Vehicle> getById(int id) {
        String getByIdQuery = """
                SELECT *
                FROM "Vehicle"
                WHERE "id_vehicle" = ? AND "deleted" = 0
                """;

        try {
            PreparedStatement statementGetById = DBConnection.getConnection().prepareStatement(getByIdQuery);

            statementGetById.setInt(1, id);

            ResultSet rsGetById = statementGetById.executeQuery();

            if (rsGetById.next()) {
                int id_vehicle = rsGetById.getInt("id_vehicle");
                String registration = rsGetById.getString("registration");
                String brand = rsGetById.getString("brand");
                String model = rsGetById.getString("model");
                String segment = rsGetById.getString("segment");
                boolean status = rsGetById.getBoolean("status");
                String fuel = rsGetById.getString("fuel");
                int year_built = rsGetById.getInt("year_built");
                String num_doors = rsGetById.getString("num_doors");
                int num_km = rsGetById.getInt("num_km");
                int engine_capacity = rsGetById.getInt("engine_capacity");
                int potency = rsGetById.getInt("potency");
                String color = rsGetById.getString("color");
                double rental_price = rsGetById.getDouble("rental_price");
                String type = rsGetById.getString("type");
                boolean deleted = rsGetById.getBoolean("deleted");

                double selling_price = rsGetById.getDouble("selling_price");
                boolean sold = rsGetById.getBoolean("sold");

                Vehicle vehicle = new Vehicle(id_vehicle, registration, brand, model, segment, status, fuel, year_built, num_doors, num_km, engine_capacity, potency, color, rental_price, type, deleted, selling_price, sold);

                return Optional.of(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            DBConnection.closeConnection();
        }
        return Optional.empty();
    }

    @Override
    public Insert insert(Vehicle data) {
        String insertQuery = """
                INSERT INTO "Vehicle" ("registration", "brand", "model", "segment", "status", "fuel", "year_built", "num_doors", "num_km", "engine_capacity", "potency", "color", "rental_price", "type", "deleted", "selling_price", "sold")
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            PreparedStatement statementInsertVehicle = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            statementInsertVehicle.setString(1, data.getRegistration());
            statementInsertVehicle.setString(2, data.getBrand());
            statementInsertVehicle.setString(3, data.getModel());
            statementInsertVehicle.setString(4, data.getSegment());
            statementInsertVehicle.setBoolean(5, data.isStatus());
            statementInsertVehicle.setString(6, data.getFuel());
            statementInsertVehicle.setInt(7, data.getYear_built());
            statementInsertVehicle.setString(8, data.getNum_doors());
            statementInsertVehicle.setInt(9, data.getNum_km());
            statementInsertVehicle.setInt(10, data.getEngine_capacity());
            statementInsertVehicle.setInt(11, data.getPotency());
            statementInsertVehicle.setString(12, data.getColor());
            statementInsertVehicle.setDouble(13, data.getRental_price());
            statementInsertVehicle.setString(14,data.getType());
            statementInsertVehicle.setBoolean(15, data.isDeleted());
            statementInsertVehicle.setDouble(16,data.getSelling_price());
            statementInsertVehicle.setBoolean(17,data.isSold());

            int affectedLines = statementInsertVehicle.executeUpdate();

            if (affectedLines == 1) {
                ResultSet generatedKeys = statementInsertVehicle.getGeneratedKeys();
                int id_vehicle = -1;

                if (generatedKeys.next()) {
                    id_vehicle = generatedKeys.getInt(1);
                }

                if (id_vehicle != -1) {
                    DBConnection.getConnection().commit();
                    DBConnection.getConnection().setAutoCommit(true);

                    return new Insert(true, id_vehicle);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                DBConnection.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                DBConnection.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            DBConnection.closeConnection();
        }
        return new Insert(false);
    }

    @Override
    public boolean update(Vehicle data) {
        String updateQuery = """
            UPDATE "Vehicle"
            SET "registration" = ?, "brand" = ?, "model" = ?, "segment" = ?, "status" = ?, "fuel" = ?, "year_built" = ?, "num_doors" = ?, "num_km" = ?, "engine_capacity" = ?, "potency" = ?, "color" = ?, "rental_price" = ?, "type" = ?, "deleted" = ?, "selling_price" = ?, "sold" = ?
            WHERE "id_vehicle" = ?
            """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            PreparedStatement statementUpdate = DBConnection.getConnection().prepareStatement(updateQuery);

            statementUpdate.setString(1, data.getRegistration());
            statementUpdate.setString(2, data.getBrand());
            statementUpdate.setString(3, data.getModel());
            statementUpdate.setString(4, data.getSegment());
            statementUpdate.setBoolean(5, data.isStatus());
            statementUpdate.setString(6, data.getFuel());
            statementUpdate.setInt(7, data.getYear_built());
            statementUpdate.setString(8, data.getNum_doors());
            statementUpdate.setInt(9, data.getNum_km());
            statementUpdate.setInt(10, data.getEngine_capacity());
            statementUpdate.setInt(11, data.getPotency());
            statementUpdate.setString(12, data.getColor());
            statementUpdate.setDouble(13, data.getRental_price());
            statementUpdate.setString(14, data.getType());
            statementUpdate.setBoolean(15, data.isDeleted());
            statementUpdate.setDouble(16, data.getSelling_price());
            statementUpdate.setBoolean(17, data.isSold());
            statementUpdate.setInt(18, data.getId_vehicle());

            int affectedLines = statementUpdate.executeUpdate();

            if (affectedLines == 1) {
                DBConnection.getConnection().commit();
                DBConnection.getConnection().setAutoCommit(true);

                return true;
            } else {
                DBConnection.getConnection().rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnection.closeConnection();
        }
        return false;
    }


    @Override
    public boolean delete(int id) {
        String deleteVehicleQuery = """                
                UPDATE "Vehicle"
                SET "deleted" = 1
                WHERE "id_vehicle" = ?
                """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            PreparedStatement statementDeleteVehicle = DBConnection.getConnection().prepareStatement(deleteVehicleQuery);

            statementDeleteVehicle.setInt(1, id);

            int affectedLines = statementDeleteVehicle.executeUpdate();

            DBConnection.getConnection().commit();

            if (affectedLines == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                DBConnection.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return false;
        } finally {
            try {
                DBConnection.getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            DBConnection.closeConnection();
        }
        return false;
    }

    @Override
    public boolean existsByRegistration(String registration) {
        String query = """
                SELECT 1 FROM "Vehicle"
                WHERE "registration" = ? 
                """;

        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, registration);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }

        return false;
    }
}

