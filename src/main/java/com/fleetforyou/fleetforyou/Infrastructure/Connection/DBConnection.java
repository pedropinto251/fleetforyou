package com.fleetforyou.fleetforyou.Infrastructure.Connection;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn = null;
    private static final Dotenv dotenv = Dotenv.load();

    public static Connection getConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                return conn;
            }
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                    dotenv.get("DB_URL"),
                    dotenv.get("DB_USER"),
                    dotenv.get("DB_PASSWORD")
            );
        } catch (SQLException e) {
            System.out.println("Erro ao conectar à base de dados: " + e.getLocalizedMessage());
            System.out.println("A fechar o programa...");
            System.exit(0);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            System.err.println("Error closing the connection: " + e.getLocalizedMessage());
        }
    }
}
