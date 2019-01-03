/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Application.Service;

import com.southbrmeme.Application.IService.IMemeService;
import com.southbrmeme.Application.Model.MemeModel;
import com.southbrmeme.Application.Model.Model;
import com.southbrmeme.InfraStructure.ExternalServices.DownloadImageAWS;
import com.southbrmeme.Domain.Entity.MemeEntity;
import com.southbrmeme.Application.Model.ReturnModel;
import com.southbrmeme.Domain.Interface.IMemeRepository;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author dev
 */
public class MemeService extends Services implements IMemeService<Model> {

    @Inject
    private IMemeRepository iMemeRepository;
    
    private ResultSet rs;

    @Override
    public Model insert(MemeModel meme, InputStream fileInputStream, FormDataContentDisposition fileMetaData) {

        ReturnModel retorno = new ReturnModel();
        try {
            getConnectOpen();
            iMemeRepository.insert(((MemeEntity) meme.getEntity()), fileInputStream, fileMetaData, getConnect());
            getConnectClose();

            retorno.setAnswer(true);
            retorno.setAnswerText("meme cadastrado com sucesso");
            return retorno;
        } catch (SQLException ex) {
            getConnectClose();
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao cadastrar meme");
            return retorno;
        }
    }

    @Override
    public Model update(MemeModel meme, InputStream fileInputStream, FormDataContentDisposition fileMetaData) {

        ReturnModel retorno = new ReturnModel();
        try {
            getConnectOpen();
            iMemeRepository.update(((MemeEntity) meme.getEntity()), fileInputStream, fileMetaData, getConnect());
            getConnectClose();

            retorno.setAnswer(true);
            retorno.setAnswerText("meme atualizado com sucesso");
            return retorno;
        } catch (SQLException | IOException ex) {
            getConnectClose();
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao atualizar meme");
            return retorno;
        }
    }

    @Override
    public Model delete(MemeModel meme) {

        ReturnModel retorno = new ReturnModel();
        try {
            getConnectOpen();
            iMemeRepository.delete(((MemeEntity) meme.getEntity()), getConnect());
            getConnectClose();

            retorno.setAnswer(true);
            retorno.setAnswerText("meme deletado com sucesso");
            return retorno;
        } catch (SQLException ex) {
            getConnectClose();
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao deletar meme");
            return retorno;
        }
    }

    @Override
    public List<Model> getMyMeme(MemeModel meme) {

        List<Model> list = new ArrayList<>();
        try {
            getConnectOpen();
            rs = iMemeRepository.selectId(((MemeEntity) meme.getEntity()), getConnect());
            while (rs.next()) {
                list.add(new MemeModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        true));
            }
            getConnectClose();
            return list;
        } catch (SQLException e) {
            getConnectClose();
            return null;
        }
    }

    @Override
    public List<Model> getMeme() {

        List<Model> list = new ArrayList<>();
        try {
            getConnectOpen();
            rs = iMemeRepository.select(getConnect());
            while (rs.next()) {
                list.add(new MemeModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        true));
            }
            getConnectClose();
            return list;
        } catch (SQLException e) {
            getConnectClose();
            return null;
        }
    }

    @Override
    public List<Model> getMeme(List<MemeModel> memes) {

        List<Model> list = new ArrayList<>();
        List<MemeEntity> entitys = new ArrayList<>();

        list.forEach((item) -> {
            entitys.add((MemeEntity) item.getEntity());
        });

        try {
            getConnectOpen();
            rs = iMemeRepository.selectDinamic(getConnect(), entitys);
            while (rs.next()) {
                list.add(new MemeModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        true));
            }
            getConnectClose();
            return list;
        } catch (SQLException e) {
            getConnectClose();
            return null;
        }
    }

    @Override
    public Model getDownload(MemeModel meme) {
        DownloadImageAWS downloadImageAWS = new DownloadImageAWS();
        ReturnModel retorno = new ReturnModel();

        try {
            getConnectOpen();
            rs = iMemeRepository.url(((MemeEntity) meme.getEntity()), getConnect());
            if (rs.next()) {
                retorno.setAnswer(true);
                retorno.setImage(downloadImageAWS.download(rs.getString(1)));

                getConnectClose();
                return retorno;
            }
            getConnectClose();

            retorno.setAnswer(true);
            retorno.setAnswerText("imagem nao disponivel");
            return retorno;
        } catch (SQLException ex) {
            getConnectClose();
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao realizar download meme");
            return retorno;
        }
    }

    @Override
    public MemeModel getData() {

        List<Model> list = new ArrayList<>();
        MemeModel memeModel = null;
        try {
            getConnectOpen();
            rs = iMemeRepository.select(getConnect());
            while (rs.next()) {
                memeModel = new MemeModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        true);
            }
            getConnectClose();
            return memeModel;
        } catch (SQLException e) {
            getConnectClose();
            return null;
        }
    }
}
