/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Domain.Interface;

import com.southbrmeme.Domain.Entity.MemeEntity;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author dev
 */
public interface IMemeRepository {

    public boolean insert(MemeEntity meme, InputStream fileInputStream, FormDataContentDisposition fileMetaData, Connection conexao) throws SQLException;

    public int update(MemeEntity meme, InputStream fileInputStream, FormDataContentDisposition fileMetaData, Connection conexao) throws SQLException,IOException;

    public boolean delete(MemeEntity meme, Connection conexao) throws SQLException;

    public ResultSet select(Connection conexao) throws SQLException;

    public ResultSet selectData(Connection conexao) throws SQLException;

    public ResultSet selectId(MemeEntity meme, Connection conexao) throws SQLException;

    public ResultSet url(MemeEntity meme, Connection conexao) throws SQLException;

    public ResultSet selectDinamic(Connection conexao, List<MemeEntity> memes) throws SQLException;

}
