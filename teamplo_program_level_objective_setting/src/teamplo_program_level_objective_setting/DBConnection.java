package teamplo_program_level_objective_setting;

import java.sql.*;

public class DBConnection {
    private static Connection conn;
    private static Statement stmt;

    public static void connect(String dbName) {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTable() {
        try {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS program_lo (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "prog_lo_code TEXT NOT NULL," +
                    "prog_lo_no TEXT," +
                    "prog_lo_name TEXT," +
                    "prog_lo_details TEXT);");
        } catch (Exception e) {
            System.out.println("Table creation error: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    public static Statement getStatement() {
        return stmt;
    }

    public static void closeConnection() {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 