/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.InfraStructure.Data;

import com.southbrmeme.Domain.Entity.CityEntity;
import java.sql.Connection;
import java.sql.SQLException;
import com.southbrmeme.Domain.Interface.ICityRepository;
import com.southbrmeme.Domain.Interface.IRepository;

/**
 *
 * @author joao.vrevangelista
 */
public class CityRepository extends IRepository implements ICityRepository {

    @Override
    public boolean insert(CityEntity city, Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into city(city) values (?)");

        pst = conexao.prepareStatement(sql.toString());
        pst.setString(1, city.getCity());
        return pst.execute();
    }

    @Override
    public int update(CityEntity city, Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("update city set city = ?");
        sql.append(" where id = ? and active = true");

        pst = conexao.prepareStatement(sql.toString());
        pst.setString(1, city.getCity());
        pst.setInt(2, city.getId());
        return pst.executeUpdate();
    }

    @Override
    public boolean delete(CityEntity city, Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("update city set active = false");
        sql.append(" where id = ?");

        pst = conexao.prepareStatement(sql.toString());
        pst.setInt(1, city.getId());
        return pst.execute();
    }
}
