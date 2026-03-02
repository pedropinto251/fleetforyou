package com.fleetforyou.fleetforyou.Infrastructure.DAO;

import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Utils.Insert;
import com.fleetforyou.fleetforyou.Infrastructure.Connection.DBConnection;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IClientDAO;
import com.fleetforyou.fleetforyou.Infrastructure.DAO.Interfaces.IRentalDAO;

import java.sql.*;
import java.util.*;

public class ClientDAO implements IClientDAO {

    /**
     * Faz uma query à base de dados para retornar todos os Stand
     *
     * @return
     */
    private final IRentalDAO rentalDAO;

    public ClientDAO() {
        this.rentalDAO = new RentalDAO();
    }

    @Override
    public List<Client> getAll() {//listar clientes
        List<Client> list = new ArrayList<>();

        String getAllQuery = """
                SELECT *
                FROM "Client"
                WHERE "deleted" = 0
                """;

        try {
            PreparedStatement statementGetAll = DBConnection.getConnection().prepareStatement(getAllQuery);

            ResultSet rsGetAll = statementGetAll.executeQuery();

            while (rsGetAll.next()) {
                int id = rsGetAll.getInt("id_client");
                String name = rsGetAll.getString("name");
                String nc = rsGetAll.getString("vat_number");
                String phone_number = rsGetAll.getString("phone_number");
                String address = rsGetAll.getString("address");
                String postal_code = rsGetAll.getString("postal_code");
                String local = rsGetAll.getString("local");
                String district = rsGetAll.getString("district");
                boolean deleted = rsGetAll.getBoolean("deleted");

                Client client = new Client(id, name, nc, phone_number, address, postal_code, local, district, deleted);

                list.add(client);
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
     *
     * @param id
     * @return Retorna o Stand
     */
    @Override
    public Optional<Client> getById(int id) {
        // Define a consulta SQL para obter um utilizador com base no ID
        String getByIdQuery = """
                SELECT *
                FROM "Client"
                WHERE "id_client" = ? AND "deleted" = 0
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
                int id_client = rsGetById.getInt("id_client");
                String name = rsGetById.getString("name");
                String nc = rsGetById.getString("vat_number");
                String phone_number = rsGetById.getString("phone_number");
                String address = rsGetById.getString("address");
                String postal_code = rsGetById.getString("postal_code");
                String local = rsGetById.getString("local");
                String district = rsGetById.getString("district");
                boolean deleted = rsGetById.getBoolean("deleted");

                Client client = new Client(id_client, name, nc, phone_number, address, postal_code, local, district, deleted);

                // Define o resultado como um Optional contendo o cliente
                return Optional.of(client);
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
     *
     * @param data
     * @return
     */
    @Override
    public Insert insert(Client data) {
        // Define a consulta SQL para adicionar um novo utilizador na tabela 'Stand' com valores parametizados
        String insertQuery = """
                INSERT INTO "Client" ("name", "vat_number", "phone_number", "address", "postal_code", "local", "district", "deleted")
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            // Prepara uma declaração PreparedStatement para a inserção dos dados
            PreparedStatement statementInsertStand = DBConnection.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            //Define os valores dos parâmetros na consulta tendo em conta os atributos do objeto 'data'
            statementInsertStand.setString(1, data.getName());
            statementInsertStand.setString(2, data.getNc());
            statementInsertStand.setString(3, data.getPhone_number());
            statementInsertStand.setString(4, data.getAddress());
            statementInsertStand.setString(5, data.getPostalCode());
            statementInsertStand.setString(6, data.getLocal());
            statementInsertStand.setString(7, data.getDistrict());
            statementInsertStand.setBoolean(8, false);

            // Executa a inserção e obtém o número de linhas afetadas
            int affectedLines = statementInsertStand.executeUpdate();

            // Se uma linha for afetada (sucesso na inserção), retorna 'true'
            if (affectedLines == 1) {
                // Inserção bem-sucedida, inserir as roles no utilizador
                ResultSet generatedKeys = statementInsertStand.getGeneratedKeys();
                int id_client = -1;

                if (generatedKeys.next()) {
                    id_client = generatedKeys.getInt(1);
                }

                if (id_client != -1) {

                    DBConnection.getConnection().commit();
                    DBConnection.getConnection().setAutoCommit(true);

                    return new Insert(true, id_client);
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
     *
     * @param data
     * @return
     */
    @Override
    public boolean update(Client data) {
        // Define a consulta SQL para atualizar os dados de um utilizador com base ID
        String updateQuery = """
                UPDATE "Client"
                SET "name" = ?, "vat_number" = ?, "phone_number" = ?, "address" = ?, "postal_code" = ?, "local" = ?, "district" = ?, "deleted" = ?
                WHERE "id_client" = ?
                """;

        try {
            DBConnection.getConnection().setAutoCommit(false);

            // Prepara a declaração PreparedStatement para a atualização
            PreparedStatement statementUpdate = DBConnection.getConnection().prepareStatement(updateQuery);

            // Define os valores dos parâmetros na consulta com base nos atributos do objeto 'data'
            statementUpdate.setString(1, data.getName());
            statementUpdate.setString(2, data.getNc());
            statementUpdate.setString(3, data.getPhone_number());
            statementUpdate.setString(4, data.getAddress());
            statementUpdate.setString(5, data.getPostalCode());
            statementUpdate.setString(6, data.getLocal());
            statementUpdate.setString(7, data.getDistrict());
            statementUpdate.setBoolean(8, data.isDeleted());
            statementUpdate.setInt(9, data.getId());

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
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        // Define a consulta SQL para excluir um utilizador com um ID específico
        String deleteStandQuery = """
                UPDATE "Client"
                SET "deleted" = 1
                WHERE "id_client" = ?
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
    public boolean existsByNif(String nif) {
        String query = """
                SELECT 1 FROM "Client"
                WHERE "vat_number" = ? AND "deleted" = 0
                """;

        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, nif);

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

