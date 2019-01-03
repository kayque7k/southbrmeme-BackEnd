/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.InfraStructure.Data;

import com.southbrmeme.Domain.ObjectValue.CodePassword;
import com.southbrmeme.Domain.Entity.UserEntity;
import com.southbrmeme.Domain.Interface.IRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.southbrmeme.Domain.Interface.IUserRepository;

/**
 *
 * @author dev
 */
public class UserRepository extends IRepository implements IUserRepository {

    @Override
    public boolean insert(UserEntity user, Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into user(name,login,password,idcity,dateCri) values (?, ?, ?, ?,date(now()))");
        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, user.getName());
        pst.setString(2, user.getLogin());
        pst.setString(3, CodePassword.encode(user.getPassword()));
        pst.setInt(4, 1);
        return pst.execute();
    }

    @Override
    public int update(UserEntity user, Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("update user set name = ? , login = ? , password = ? ");
        sql.append(" where id = ? and password = ? ");

        pst = conexao.prepareStatement(sql.toString());
        pst.setString(1, user.getName());
        pst.setString(2, user.getLogin());
        pst.setString(3, CodePassword.encode(user.getNewPassword()));
        pst.setInt(4, user.getId());
        pst.setString(5, CodePassword.encode(user.getPassword()));
        return pst.executeUpdate();
    }

    @Override
    public ResultSet login(UserEntity user, Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select id,name from user");
        sql.append(" where login = ? and password = ?");
        sql.append(" and active = true");

        pst = conexao.prepareStatement(sql.toString());
        pst.setString(1, user.getLogin());
        pst.setString(2, CodePassword.encode(user.getPassword()));
        return pst.executeQuery();
    }

    @Override
    public ResultSet data(UserEntity user, Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select name,login,password,idcity,DATE_FORMAT(dateCri, '%d/%m/%Y') from user");
        sql.append(" where id = ?");
        sql.append(" and active = true");

        pst = conexao.prepareStatement(sql.toString());
        pst.setInt(1, user.getId());
        return pst.executeQuery();
    }

    @Override
    public boolean delete(UserEntity user, Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("update user set active = false");
        sql.append(" where id = ?");

        pst = conexao.prepareStatement(sql.toString());
        pst.setInt(1, user.getId());
        return pst.execute();
    }
}
