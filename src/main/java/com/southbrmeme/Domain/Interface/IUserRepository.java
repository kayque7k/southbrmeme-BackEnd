/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Domain.Interface;

import com.southbrmeme.Domain.Entity.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dev
 */
public interface IUserRepository {

    public boolean insert(UserEntity user, Connection conexao) throws SQLException;

    public int update(UserEntity user, Connection conexao) throws SQLException;

    public boolean delete(UserEntity user, Connection conexao) throws SQLException;

    public ResultSet login(UserEntity user, Connection conexao) throws SQLException;

    public ResultSet data(UserEntity user, Connection conexao) throws SQLException;
}
