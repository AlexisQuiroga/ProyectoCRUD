package com.tuempresa.crudapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {
    private static final String URL = "jdbc:mysql://localhost:3306/tubasededatos";
    private static final String USER = "tuusuario";
    private static final String PASSWORD = "tucontraseña";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
