/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Domain.Interface;

import com.southbrmeme.Domain.Entity.CityEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 *  @author joao.vrevangelista
 */
public interface ICityRepository {

    public boolean insert(CityEntity city, Connection conexao) throws SQLException;

    public int update(CityEntity city, Connection conexao) throws SQLException;

    public boolean delete(CityEntity city, Connection conexao) throws SQLException;

}
