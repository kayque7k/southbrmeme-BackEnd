/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Application.Service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Kayque Rodrigues
 */
public abstract class Services {

    private Connection connection;

    protected void getConnectOpen() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = com.southbrmeme.InfraStructure.Data.Connection.conectorDev();
        }
    }

    protected Connection getConnect() {
        return connection;
    }

    protected void getConnectClose() {
        try {
            if (connection != null || !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
        }
    }

}
