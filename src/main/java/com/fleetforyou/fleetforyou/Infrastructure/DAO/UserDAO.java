package com.fleetforyou.fleetforyou.Infrastructure.DAO;

import com.fleetforyou.fleetforyou.Application.Services.StandService.IStandCRUDService;
import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoginDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Utils.InjectorProvider;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Domain.Utils.Response;
import com.fleetforyou.fleetforyou.Infrastructure.Connection.DBConnection;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IUserDAO;

import java.sql.*;
import java.util.*;

public class UserDAO implements IUserDAO {

    private final IStandCRUDService standCRUDService = InjectorProvider.getStandCRUDService();

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();

        String getAllQuery = """
                SELECT *
                FROM "User"
                """;

        String getPermsById = """
                SELECT "p"."description"
                FROM "Permission" "p"
                JOIN "User_Permission" "up"
                ON "p"."id_permission" = "up"."id_permission"
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
                int id_user = rsGetAll.getInt("id_user");
                String name = rsGetAll.getString("name");
                String phone_number = rsGetAll.getString("phone_number");
                String address = rsGetAll.getString("address");
                String postal_code = rsGetAll.getString("postal_code");
                String local = rsGetAll.getString("local");
                String district = rsGetAll.getString("district");
                String email = rsGetAll.getString("email");
                String password = rsGetAll.getString("password");
                int id_stand = rsGetAll.getInt("id_stand");
                boolean deleted = rsGetAll.getBoolean("deleted");
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

                ResultSet rsGetPermissions = statementGetPerms.executeQuery();

                while (rsGetPermissions.next()) {
                    // Process permissions
                    String perm = rsGetPermissions.getString("description");
                    permissions.add(Permission.fromString(perm));
                }

                User user = new User(id_user, name, phone_number, address, postal_code, local, district, email, password, stand, permissions, deleted);

                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }

        return list;
    }

    @Override
    public List<User> getAllSameDistrict(String district) {
        List<User> list = new ArrayList<>();

        String getAllQuery = """
                SELECT *
                FROM "User"
                WHERE "deleted" = 0 AND "district" = ?
                """;

        String getPermsById = """
                SELECT "p"."description"
                FROM "Permission" "p"
                JOIN "User_Permission" "up"
                ON "p"."id_permission" = "up"."id_permission"
                WHERE "up"."id_user" = ?
                """;

        String getStandByUser = """
                SELECT *
                FROM "Stand"
                WHERE "id_stand" = ?
                """;

        try {
            PreparedStatement statementGetAll = DBConnection.getConnection().prepareStatement(getAllQuery);

            // Define o valor do parâmetro na consulta com base no ID fornecido
            statementGetAll.setString(1, district);

            ResultSet rsGetAll = statementGetAll.executeQuery();

            while (rsGetAll.next()) {
                int id_user = rsGetAll.getInt("id_user");
                String name = rsGetAll.getString("name");
                String phone_number = rsGetAll.getString("phone_number");
                String address = rsGetAll.getString("address");
                String postal_code = rsGetAll.getString("postal_code");
                String local = rsGetAll.getString("local");
                String districtRS = rsGetAll.getString("district");
                String email = rsGetAll.getString("email");
                String password = rsGetAll.getString("password");
                int id_stand = rsGetAll.getInt("id_stand");
                boolean deleted = rsGetAll.getBoolean("deleted");
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

                ResultSet rsGetPermissions = statementGetPerms.executeQuery();

                while (rsGetPermissions.next()) {
                    // Process permissions
                    String perm = rsGetPermissions.getString("description");
                    permissions.add(Permission.fromString(perm));
                }

                User user = new User(id_user, name, phone_number, address, postal_code, local, districtRS, email, password, stand, permissions, deleted);

                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }

        return list;
    }

    public List<User> getAllExceptDeleted() {
        List<User> list = new ArrayList<>();

        String getAllQuery = """
                SELECT *
                FROM "User"
                WHERE "deleted" = 0
                """;

        String getPermsById = """
                SELECT "p"."description"
                FROM "Permission" "p"
                JOIN "User_Permission" "up"
                ON "p"."id_permission" = "up"."id_permission"
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
                int id_user = rsGetAll.getInt("id_user");
                String name = rsGetAll.getString("name");
                String phone_number = rsGetAll.getString("phone_number");
                String address = rsGetAll.getString("address");
                String postal_code = rsGetAll.getString("postal_code");
                String local = rsGetAll.getString("local");
                String district = rsGetAll.getString("district");
                String email = rsGetAll.getString("email");
                String password = rsGetAll.getString("password");
                int id_stand = rsGetAll.getInt("id_stand");
                boolean deleted = rsGetAll.getBoolean("deleted");
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

                ResultSet rsGetPermissions = statementGetPerms.executeQuery();

                while (rsGetPermissions.next()) {
                    // Process permissions
                    String perm = rsGetPermissions.getString("description");
                    permissions.add(Permission.fromString(perm));
                }

                User user = new User(id_user, name, phone_number, address, postal_code, local, district, email, password, stand, permissions, deleted);

                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }

        return list;
    }

    @Override
    public Optional<User> getById(int id) {
        String getByIdQuery = """
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

        try {
            PreparedStatement statementGetById = DBConnection.getConnection().prepareStatement(getByIdQuery);

            statementGetById.setInt(1, id);

            ResultSet rsGetById = statementGetById.executeQuery();

            if (rsGetById.next()) {
                int id_user = rsGetById.getInt("id_user");
                String name = rsGetById.getString("name");
                String phone_number = rsGetById.getString("phone_number");
                String address = rsGetById.getString("address");
                String postal_code = rsGetById.getString("postal_code");
                String local = rsGetById.getString("local");
                String district = rsGetById.getString("district");
                String email = rsGetById.getString("email");
                String password = rsGetById.getString("password");
                int id_stand = rsGetById.getInt("id_stand");
                boolean deleted = rsGetById.getBoolean("deleted");
                Response standResponse = standCRUDService.getStandById(id_stand);
                Stand stand = (Stand) standResponse.getData();
                Set<Permission> permissions = new HashSet<>();

                PreparedStatement statementGetPerms = DBConnection.getConnection().prepareStatement(getPermsById);
                statementGetPerms.setInt(1, id_user);

                ResultSet rsGetPerms = statementGetPerms.executeQuery();

                while (rsGetPerms.next()) {
                    String perm = rsGetPerms.getString("description");

                    permissions.add(Permission.fromString(perm));
                }

                User user = new User(id_user, name, phone_number, address, postal_code, local, district, email, password, stand, permissions, deleted);

                return Optional.of(user);
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
    public Insert insert(User data) {
        String insertQuery = """
                INSERT INTO "User" ("name", "phone_number", "address", "postal_code", "local", "district", "email", "password", "id_stand", "deleted")
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        String insertPermissionsQuery = """
                INSERT INTO "User_Permission"("id_user", "id_permission")
                VALUES (?, ?)
                """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            PreparedStatement statementInsertUser = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            statementInsertUser.setString(1, data.getName());
            statementInsertUser.setString(2, data.getPhone_number());
            statementInsertUser.setString(3, data.getAddress());
            statementInsertUser.setString(4, data.getPostal_code());
            statementInsertUser.setString(5, data.getLocal());
            statementInsertUser.setString(6, data.getDistrict());
            statementInsertUser.setString(7, data.getEmail());
            statementInsertUser.setString(8, data.getPassword());
            statementInsertUser.setString(9, String.valueOf(data.getStand().getId()));
            statementInsertUser.setBoolean(10, data.isDeleted());

            int affectedLines = statementInsertUser.executeUpdate();

            if (affectedLines == 1) {
                ResultSet generatedKeys = statementInsertUser.getGeneratedKeys();
                int id_user = -1;

                if (generatedKeys.next()) {
                    id_user = generatedKeys.getInt(1);
                }

                if (id_user != -1) {
                    if (data.getPermissions() != null) {
                        PreparedStatement statementInsertPermissions = DBConnection.getConnection().prepareStatement(insertPermissionsQuery);
                        // Itera sobre as roles do utilizador e insere cada uma na base de dados
                        for (Permission perm : data.getPermissions()) {
                            statementInsertPermissions.setInt(1, id_user);
                            statementInsertPermissions.setInt(2, perm.getIdPerm(perm));
                            statementInsertPermissions.addBatch();
                        }

                        // Executa a inserção das roles do utilizador
                        statementInsertPermissions.executeBatch();
                    }

                    DBConnection.getConnection().commit();
                    DBConnection.getConnection().setAutoCommit(true);

                    return new Insert(true, id_user);
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
    public boolean update(User data) {
        String updateQuery = """
                UPDATE "User"
                SET "name" = ?, "phone_number" = ?, "address" = ?, "postal_code" = ?, "local" = ?, "district" = ?, "email" = ?, "password" = ?, "deleted" = ?
                WHERE "id_user" = ?
                """;

        /*
        // Define a consulta SQL para remover todas as roles do utilizador
        String deletePermissionsQuery = """
                DELETE FROM "Users_Permission"
                WHERE "id_user" = ?
                """;

        // Define a consulta SQL para inserir novas roles no utilizador
        String insertPermissionsQuery = """
                INSERT INTO "Users_Permission" ("id_user", "role_id")
                VALUES (?, ?)
                """;
         */

        try {
            DBConnection.getConnection().setAutoCommit(false);

            PreparedStatement statementUpdate = DBConnection.getConnection().prepareStatement(updateQuery);

            statementUpdate.setString(1, data.getName());
            statementUpdate.setString(2, data.getPhone_number());
            statementUpdate.setString(3, data.getAddress());
            statementUpdate.setString(4, data.getPostal_code());
            statementUpdate.setString(5, data.getLocal());
            statementUpdate.setString(6, data.getDistrict());
            statementUpdate.setString(7, data.getEmail());
            statementUpdate.setString(8, data.getPassword());
            statementUpdate.setBoolean(9, data.isDeleted());
            statementUpdate.setInt(10, data.getId_user());

            int affectedLines = statementUpdate.executeUpdate();

            if (affectedLines == 1) {
                /*
                // Remover todas as roles do utilizador
                PreparedStatement statementDeletePermissions = DBConnection.getConnection().prepareStatement(deletePermissionsQuery);
                statementDeletePermissions.setInt(1, data.getUser_id());
                statementDeletePermissions.executeUpdate();

                // Inserir novas roles ao utilizador
                PreparedStatement statementInsertPermissions = DBConnection.getConnection().prepareStatement(insertPermissionsQuery);
                for (Permission role : data.getPermissions()) {
                    statementInsertPermissions.setInt(1, data.getUser_id());
                    statementInsertPermissions.setInt(2, role.getIdPermission(role));
                    //Adicionar operação à lista de operações em lote (batch).
                    //O seu uso permite que várias operações sejam executadas numa única operação.
                    //Melhora o desempenho e consistência do banco de dados.
                    statementInsertPermissions.addBatch();
                }
                statementInsertPermissions.executeBatch();
                 */

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
        String deleteUserQuery = """
                UPDATE "User"
                SET "deleted" = 1
                WHERE "id_user" = ?
                """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            PreparedStatement statementDeleteUser = DBConnection.getConnection().prepareStatement(deleteUserQuery);

            statementDeleteUser.setInt(1, id);

            int affectedLines = statementDeleteUser.executeUpdate();

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
    public Optional<User> loginUser(UserLoginDTO data) {
        String getQuery = """
                SELECT *
                FROM "User"
                WHERE "email" = ? AND "deleted" = 0
                """;

        String getPermsById = """
                SELECT "p"."description"
                FROM "Permission" "p"
                JOIN "User_Permission" "up"
                ON "p"."id_permission" = "up"."id_permission"
                WHERE "up"."id_user" = ?
                """;

        try {
            PreparedStatement statementGet = DBConnection.getConnection().prepareStatement(getQuery);
            statementGet.setString(1, data.email());

            ResultSet rsGet = statementGet.executeQuery();

            while (rsGet.next()) {
                int id_user = rsGet.getInt("id_user");
                String name = rsGet.getString("name");
                String phone_number = rsGet.getString("phone_number");
                String address = rsGet.getString("address");
                String postal_code = rsGet.getString("postal_code");
                String local = rsGet.getString("local");
                String district = rsGet.getString("district");
                String email = rsGet.getString("email");
                String password = rsGet.getString("password");
                int id_stand = rsGet.getInt("id_stand");
                boolean deleted = rsGet.getBoolean("deleted");
                Response standResponse = standCRUDService.getStandById(id_stand);
                Stand stand = (Stand) standResponse.getData();
                Set<Permission> permissions = new HashSet<>();

                PreparedStatement statementGetPerms = DBConnection.getConnection().prepareStatement(getPermsById);
                statementGetPerms.setInt(1, id_user);

                ResultSet rsGetPerms = statementGetPerms.executeQuery();

                while (rsGetPerms.next()) {
                    String perm = rsGetPerms.getString("description");

                    permissions.add(Permission.fromString(perm));
                }

                if (email.equals(data.email()) && password.equals(data.password())){
                    User user = new User(id_user, name, phone_number, address, postal_code, local, district, email, password, stand, permissions, deleted);

                    return Optional.of(user);
                }
                return Optional.empty();
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
    public boolean existsByEmail(String email) {
        String query = """
                SELECT 1 FROM "User"
                WHERE "email" = ? AND "deleted" = 0
                """;

        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, email);

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
