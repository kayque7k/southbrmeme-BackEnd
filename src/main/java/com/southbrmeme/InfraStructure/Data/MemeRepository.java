/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.InfraStructure.Data;

import com.southbrmeme.Domain.ObjectValue.DateTime;
import com.southbrmeme.InfraStructure.ExternalServices.UploadImageAWS;
import com.southbrmeme.Domain.Entity.MemeEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.southbrmeme.Domain.Interface.IMemeRepository;
import com.southbrmeme.Domain.Interface.IRepository;
import java.io.IOException;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author dev
 */
public class MemeRepository extends IRepository implements IMemeRepository {

    @Override
    public boolean insert(MemeEntity meme, InputStream fileInputStream, FormDataContentDisposition fileMetaData, Connection conexao) throws SQLException {
        UploadImageAWS uploadImageAWS = new UploadImageAWS();
        StringBuilder sql = new StringBuilder();
        sql.append("insert into meme(url,commit,iduser,dateCri) values (?, ?, ?, date(now()))");

        pst = conexao.prepareStatement(sql.toString());
        pst.setString(1, uploadImageAWS.upload(fileInputStream, fileMetaData));
        pst.setString(2, meme.getCommit());
        pst.setInt(3, meme.getIduser());

        return pst.execute();
    }

    @Override
    public int update(MemeEntity meme, InputStream fileInputStream, FormDataContentDisposition fileMetaData, Connection conexao) throws SQLException, IOException {
        UploadImageAWS uploadImageAWS = new UploadImageAWS();
        StringBuilder sql = new StringBuilder();
        sql.append("update meme set commit = ?");

        if (fileMetaData.getParameters().size() > 1) {
            sql.append(" , url = ?");
        }

        sql.append(" where id = ? and iduser = ? and active = true");

        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, meme.getCommit());

        if (fileMetaData.getParameters().size() > 1) {
            pst.setString(2, uploadImageAWS.upload(fileInputStream, fileMetaData));

            pst.setInt(3, meme.getId());
            pst.setInt(4, meme.getIduser());
        } else {
            pst.setInt(2, meme.getId());
            pst.setInt(3, meme.getIduser());
        }
        return pst.executeUpdate();
    }

    @Override
    public ResultSet select(Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select m.id,m.url,m.commit,DATE_FORMAT(m.dateCri, '%d/%m/%Y'),m.iduser,u.name from meme as m");
        sql.append(" inner join user as u on u.id = m.iduser");
        sql.append(" where u.idcity = 1 and m.active = true");
        sql.append(" and m.dateCri BETWEEN date_sub(date(now()),interval 30 day) and date(now())");
        sql.append(" order by rand() limit 10");

        pst = conexao.prepareStatement(sql.toString());
        return pst.executeQuery();
    }

    @Override
    public ResultSet selectDinamic(Connection conexao, List<MemeEntity> memes) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select m.id,m.url,m.commit,DATE_FORMAT(m.dateCri, '%d/%m/%Y'),m.iduser,u.name from meme as m");
        sql.append(" inner join user as u on u.id = m.iduser");
        sql.append(" where u.idcity = 1 and m.active = true");
        sql.append(" and MONTH(m.dateCri) = ? and YEAR(m.dateCri) = ?");

        for (int i = 0; i < memes.size(); i++) {
            sql.append(" and m.id != ?");
        }

        sql.append(" order by rand() limit 10");

        pst = conexao.prepareStatement(sql.toString());
        pst.setString(1, DateTime.mes(DateTime.dataSistema()));
        pst.setString(2, DateTime.ano(DateTime.dataSistema()));

        for (int i = 0; i < memes.size(); i++) {
            pst.setInt(3 + i, memes.get(i).getId());
        }

        return pst.executeQuery();
    }

    @Override
    public ResultSet selectData(Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select m.id,m.url,m.commit,DATE_FORMAT(m.dateCri, '%d/%m/%Y'),m.iduser,u.name from meme as m");
        sql.append(" inner join user as u on u.id = m.iduser");
        sql.append(" where u.idcity = 1 and m.active = true");
        sql.append(" and MONTH(m.dateCri) = ? and YEAR(m.dateCri) = ?");
        sql.append(" order by rand() limit 1");

        pst = conexao.prepareStatement(sql.toString());
        pst.setString(1, DateTime.mes(DateTime.dataSistema()));
        pst.setString(2, DateTime.ano(DateTime.dataSistema()));
        return pst.executeQuery();
    }

    @Override
    public ResultSet selectId(MemeEntity meme, Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select m.id,m.url,m.commit,DATE_FORMAT(m.dateCri, '%d/%m/%Y'),m.iduser,u.name from meme as m");
        sql.append(" inner join user as u on u.id = m.iduser");
        sql.append(" where u.id = ? and m.active = true");
        sql.append(" order by m.id DESC");

        pst = conexao.prepareStatement(sql.toString());
        pst.setInt(1, meme.getIduser());
        return pst.executeQuery();
    }

    @Override
    public boolean delete(MemeEntity meme, Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("update meme set active = false");
        sql.append(" where id = ? and iduser = ?");

        pst = conexao.prepareStatement(sql.toString());
        pst.setInt(1, meme.getId());
        pst.setInt(2, meme.getIduser());
        return pst.execute();
    }

    @Override
    public ResultSet url(MemeEntity meme, Connection conexao) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("select url from meme where active = true");
        sql.append(" and id = ?");

        pst = conexao.prepareStatement(sql.toString());
        pst.setInt(1, meme.getId());
        return pst.executeQuery();
    }
}
