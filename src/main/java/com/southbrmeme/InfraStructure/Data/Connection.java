package com.southbrmeme.InfraStructure.Data;

import java.sql.*;

/*
*esta entidade tem como objetivo estabelecer conexao com o banco de dados
 */
public class Connection {

    public static java.sql.Connection conectorProd() {
        Connection conexao = null;

        String driver = "com.mysql.jdbc.Driver";
        String url = "";
        String user = "";
        String password = "";

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            return null;
        }
    }

    public static java.sql.Connection conectorDev() {
        String driver = "com.mysql.jdbc.Driver";

        //dev
        String url = "jdbc:mysql://localhost:3306/southbr?reconnect=true&useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
}
