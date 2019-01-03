/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Application.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.southbrmeme.Application.IService.IUserService;
import com.southbrmeme.Application.Model.Model;
import com.southbrmeme.Interface.RestController.CreatTokenAbstract;
import com.southbrmeme.Domain.ObjectValue.CreatToken;
import com.southbrmeme.Application.Model.ReturnModel;
import com.southbrmeme.Application.Model.UserModel;
import com.southbrmeme.Domain.Entity.UserEntity;
import com.southbrmeme.Domain.Interface.IUserRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author dev
 */
public class UserService extends Services implements IUserService {

    @Inject
    private IUserRepository iUserRepository;

    private ResultSet rs;

    @Override
    public Model insert(UserModel user) {

        ReturnModel retorno = new ReturnModel();

        try {
            getConnectOpen();
            iUserRepository.insert(((UserEntity) user.getEntity()), getConnect());
            getConnectClose();
            return login(user);
        } catch (SQLException ex) {
            getConnectClose();
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao cadastrar usuario, este login ou nick ja esta sendo usado");
            return retorno;
        }
    }

    @Override
    public Model update(UserModel user) {

        ReturnModel retorno = new ReturnModel();
        try {
            getConnectOpen();
            int quant = iUserRepository.update(((UserEntity) user.getEntity()), getConnect());
            if (quant > 0) {
                retorno.setAnswer(true);
                retorno.setAnswerText("usuario atualizado com sucesso");
            } else {
                retorno.setAnswer(false);
                retorno.setAnswerText("senha incorreta");
            }
            getConnectClose();
            return retorno;
        } catch (SQLException ex) {
            getConnectClose();
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao atualizar usuario");
            return retorno;
        }
    }

    @Override
    public Model login(UserModel user) {

        CreatTokenAbstract token = new CreatToken();
        ReturnModel retorno = new ReturnModel();
        UserModel iuser = new UserModel();

        try {
            getConnectOpen();
            rs = iUserRepository.login(((UserEntity) user.getEntity()), getConnect());

            if (rs.next()) {
                iuser.setId(rs.getInt(1));
                iuser.setName(rs.getString(2));
                Date dateNow = new Date();
                Date expires = new Date(dateNow.getTime() + 3600);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("iss", dateNow.getTime());
                jsonObject.addProperty("exp", expires.getTime());
                jsonObject.addProperty("id", iuser.getId());
                jsonObject.addProperty("name", iuser.getName());

                getConnectClose();
                retorno.setToken(token.token(jsonObject));
                retorno.setAnswer(true);
                retorno.setAnswerText("Login realizado com sucesso");
                return retorno;
            }
            getConnectClose();
            retorno.setAnswer(false);
            retorno.setAnswerText("Login ou senha invalido");
            return retorno;
        } catch (SQLException ex) {
            getConnectClose();
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro de servidor");
            return retorno;
        }
    }

    @Override
    public Model delete(UserModel user) {

        ReturnModel retorno = new ReturnModel();
        Gson gson = new Gson();

        try {
            getConnectOpen();
            iUserRepository.delete(((UserEntity) user.getEntity()), getConnect());
            getConnectClose();
            retorno.setAnswer(true);
            retorno.setAnswerText("usuario deletado com sucesso");
            return retorno;
        } catch (SQLException ex) {
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao deletar usuario");
            return retorno;
        }
    }

    @Override
    public Model data(UserModel user) {
        ReturnModel retorno = new ReturnModel();
        UserModel userModel = null;

        try {
            getConnectOpen();
            rs = iUserRepository.data(((UserEntity) user.getEntity()), getConnect());

            if (rs.next()) {
                userModel = new UserModel(0,
                        rs.getString(1),
                        rs.getString(2),
                        null,
                        rs.getInt(4),
                        rs.getString(5),
                        true);

                getConnectClose();
                return userModel;
            }

            getConnectClose();
            retorno.setAnswer(true);
            retorno.setAnswerText("usuario deletado com sucesso");
            return retorno;
        } catch (SQLException ex) {
            getConnectClose();
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao deletar usuario");
            return retorno;
        }
    }
}
