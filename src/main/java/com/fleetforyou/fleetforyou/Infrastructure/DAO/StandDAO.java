package com.fleetforyou.fleetforyou.Infrastructure.DAO;

import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Infrastructure.Connection.DBConnection;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IStandDAO;

import java.sql.*;
import java.util.*;

public class StandDAO implements IStandDAO {
    /**
     * Faz uma query à base de dados para retornar todos os Stand
     * @return
     */

    @Override
    public List<Stand> getAll() {
        List<Stand> list = new ArrayList<>();

        String getAllQuery = """
                SELECT *
                FROM "Stand"
                WHERE "deleted" = 0
                """;

        try {
            PreparedStatement statementGetAll = DBConnection.getConnection().prepareStatement(getAllQuery);

            ResultSet rsGetAll = statementGetAll.executeQuery();

            while (rsGetAll.next()) {
                int id_stand = rsGetAll.getInt("id_stand");
                String name = rsGetAll.getString("name");
                String phone_number = rsGetAll.getString("phone_number");
                String address = rsGetAll.getString("address");
                String postal_code = rsGetAll.getString("postal_code");
                String local = rsGetAll.getString("local");
                String district = rsGetAll.getString("district");
                boolean deleted = rsGetAll.getBoolean("deleted");

                Stand stand = new Stand(id_stand, name, phone_number, address, postal_code, local, district, deleted);

                list.add(stand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }

        return list;
    }

    @Override
    public List<Stand> getAllSameDistrict(String district) {
        List<Stand> list = new ArrayList<>();

        String getAllQuery = """
                SELECT *
                FROM "Stand"
                WHERE "deleted" = 0 AND "district" LIKE ?
                """;

        try {
            PreparedStatement statementGetAll = DBConnection.getConnection().prepareStatement(getAllQuery);

            // Define o valor do parâmetro na consulta com base no ID fornecido
            statementGetAll.setString(1, district);

            ResultSet rsGetAll = statementGetAll.executeQuery();

            while (rsGetAll.next()) {
                int id_stand = rsGetAll.getInt("id_stand");
                String name = rsGetAll.getString("name");
                String phone_number = rsGetAll.getString("phone_number");
                String address = rsGetAll.getString("address");
                String postal_code = rsGetAll.getString("postal_code");
                String local = rsGetAll.getString("local");
                String districtRS = rsGetAll.getString("district");
                boolean deleted = rsGetAll.getBoolean("deleted");

                Stand stand = new Stand(id_stand, name, phone_number, address, postal_code, local, districtRS, deleted);

                list.add(stand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }

        return list;
    }

    /**
     * Faz uma query à base de dados para retornar o Stand através do ID que recebe
     * @param id
     * @return Retorna o Stand
     */
    @Override
    public Optional<Stand> getById(int id) {
        // Define a consulta SQL para obter um utilizador com base no ID
        String getByIdQuery = """
                SELECT *
                FROM "Stand"
                WHERE "id_stand" = ? AND "deleted" = 0
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
                int id_stand = rsGetById.getInt("id_stand");
                String name = rsGetById.getString("name");
                String phone_number = rsGetById.getString("phone_number");
                String address = rsGetById.getString("address");
                String postal_code = rsGetById.getString("postal_code");
                String local = rsGetById.getString("local");
                String district = rsGetById.getString("district");
                boolean deleted = rsGetById.getBoolean("deleted");

                Stand stand = new Stand(id_stand, name, phone_number, address, postal_code, local, district, deleted);

                // Define o resultado como um Optional contendo o utilizador
                return Optional.of(stand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            DBConnection.closeConnection();
        }
        // Se o utilizador não for encontrado ou se ocorrer um erro, retorna 'Optional.empty()'
        return Optional.empty();
    }

    /**
     * Recebe dados de um Stand e então faz uma query para inserir esse Stand na base de dados
     * @param data
     * @return
     */
    @Override
    public Insert insert(Stand data) {
        // Define a consulta SQL para adicionar um novo utilizador na tabela 'Stand' com valores parametizados
        String insertQuery = """
                INSERT INTO "Stand" ("name", "phone_number", "address", "postal_code", "local", "district", "deleted")
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            // Prepara uma declaração PreparedStatement para a inserção dos dados
            PreparedStatement statementInsertStand = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            //Define os valores dos parâmetros na consulta tendo em conta os atributos do objeto 'data'
            statementInsertStand.setString(1, data.getName());
            statementInsertStand.setString(2, data.getPhone_number());
            statementInsertStand.setString(3, data.getAddress());
            statementInsertStand.setString(4, data.getPostalCode());
            statementInsertStand.setString(5, data.getLocal());
            statementInsertStand.setString(6, data.getDistrict());
            statementInsertStand.setBoolean(7, data.isDeleted());

            // Executa a inserção e obtém o número de linhas afetadas
            int affectedLines = statementInsertStand.executeUpdate();

            // Se uma linha for afetada (sucesso na inserção), retorna 'true'
            if (affectedLines == 1) {
                // Inserção bem-sucedida, inserir as roles no utilizador
                ResultSet generatedKeys = statementInsertStand.getGeneratedKeys();
                int id_stand = -1;

                if (generatedKeys.next()) {
                    id_stand = generatedKeys.getInt(1);
                }

                if (id_stand != -1) {

                    DBConnection.getConnection().commit();
                    DBConnection.getConnection().setAutoCommit(true);

                    return new Insert(true, id_stand);
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

    /**
     * Recebe dados de um Stand e, com o seu ID, faz uma query na base de dados para atualizà-lo
     * @param data
     * @return
     */
    @Override
    public boolean update(Stand data) {
        // Define a consulta SQL para atualizar os dados de um utilizador com base ID
        String updateQuery = """
                UPDATE "Stand"
                SET "name" = ?, "phone_number" = ?, "address" = ?, "postal_code" = ?, "local" = ?, "district" = ?, "deleted" = ?
                WHERE "id_stand" = ?
                """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            // Prepara a declaração PreparedStatement para a atualização
            PreparedStatement statementUpdate = DBConnection.getConnection().prepareStatement(updateQuery);

            // Define os valores dos parâmetros na consulta com base nos atributos do objeto 'data'
            statementUpdate.setString(1, data.getName());
            statementUpdate.setString(2, data.getPhone_number());
            statementUpdate.setString(3, data.getAddress());
            statementUpdate.setString(4, data.getPostalCode());
            statementUpdate.setString(5, data.getLocal());
            statementUpdate.setString(6, data.getDistrict());
            statementUpdate.setBoolean(7, data.isDeleted());
            statementUpdate.setInt(8, data.getId());

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

    /**
     * Recebe dados de um Stand e, com o seu ID, faz uma query na base de dados para removê-lo
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        // Define a consulta SQL para excluir um utilizador com um ID específico
        String deleteStandQuery = """
                UPDATE "Stand"
                SET "deleted" = 1
                WHERE "id_stand" = ?
                """;

        try {
            //Inicio de uma transação. Valor 'false' no autoCommit para controlar manualmente os commits, assim evitando possiveis erros.
            DBConnection.getConnection().setAutoCommit(false);

            // Prepara a declaração  PreparedStatement para a exclusão do utilizador
            PreparedStatement statementDeleteStand = DBConnection.getConnection().prepareStatement(deleteStandQuery);

            // Define o valor do parâmetro na consulta com base no ID fornecido
            statementDeleteStand.setInt(1, id);

            // Executa a exclusão e obtém o número de linhas afetadas
            int affectedLines = statementDeleteStand.executeUpdate();

            //Confirma a transação se todas as exclusões forem bem-sucedidas
            DBConnection.getConnection().commit();

            // Se uma linha for afetada (sucesso na exclusão), retorna 'true'
            if (affectedLines == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();

            // Em caso de erro, desfaz a transação
            try {
                DBConnection.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return false;
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
        return false;
    }

    @Override
    public Optional<User> hasUserStand(int id_stand){
        String getUserByStandIdQuery = """
                SELECT *
                FROM "User"
                WHERE "id_stand" = ?
                """;

        String getPermsById = """
                SELECT "p"."description"
                FROM "Permission" "p"
                JOIN "User_Permission" "up" ON "p"."id_permission" = "up"."id_permission"
                WHERE "up"."id_user" = ?
                """;
        String getStandByIdQuery = """
                SELECT *
                FROM "Stand"
                WHERE "id_stand" = ? AND "deleted" = 0
                """;

        try {
            PreparedStatement statementGetById = DBConnection.getConnection().prepareStatement(getUserByStandIdQuery);

            statementGetById.setInt(1, id_stand);

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
                int id_standRS = rsGetById.getInt("id_stand");
                boolean deleted = rsGetById.getBoolean("deleted");
                Stand stand = null;

                PreparedStatement statementGetStand = DBConnection.getConnection().prepareStatement(getStandByIdQuery);
                statementGetStand.setInt(1, id_standRS);

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
    public boolean existsByName(String name) {
        String query = """
                SELECT 1 FROM "Stand"
                WHERE "name" = ? 
                """;

        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, name);

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
