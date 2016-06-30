package database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBConnection {

    private final static String fileName = "logs.txt";
    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String url = "jdbc:mysql://localhost:3306/twitter";
    private final static String login = "root";
    private final static String pass = "";
    private final static String createDatabaseQuery =
            "CREATE DATABASE IF NOT EXISTS bookstore CHARACTER SET utf8 COLLATE utf8_general_ci";
    private final static String createTableQuery =
            "CREATE TABLE IF NOT EXISTS bookshelf (" +
                    " id INT AUTO_INCREMENT UNSIGNED NOT NULL PRIMARY KEY," +
                    " title TEXT NOT NULL," +
                    " author TEXT NULL," +
                    " imprintDate INT UNSIGNED NULL," +
                    " pages INT UNSIGNED NULL" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private Connection dbConnection = null;

    private static void AddLog(String log) {
        File logs = new File(fileName);
        Date curr = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy\n");
        try {
            if (!logs.exists()) {
                logs.createNewFile();
            }
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(dateFormat.format(curr) + log + "\n");
            fw.close();
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    public DBConnection() {
        try {
            Class.forName(driver); // Driver loading
        } catch (ClassNotFoundException ex) {
            AddLog("The driver could not be found! " + ex.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(url, login, pass); //Establish connection
            AddLog("Connect to the database was successful!");

        } catch (SQLException ex) {
            AddLog("Connection is not available! " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return dbConnection;
    }

    public boolean isConnected() {
        boolean result = true;
        try {
            if (!dbConnection.isClosed()) {
                AddLog("Connect to the database was successful!");
            }
            else {
                result = false;
            }
        } catch (SQLException ex) {
            AddLog("Connection is not available! " + ex.getMessage());
            result = false;
        }
        return result;
    }

    public void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException ex) {
            AddLog("Connection is not available! " + ex.getMessage());
        }
    }

}
