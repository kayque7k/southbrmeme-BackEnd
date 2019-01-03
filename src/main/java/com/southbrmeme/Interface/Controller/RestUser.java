/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Interface.Controller;

import com.google.gson.Gson;
import com.southbrmeme.Interface.RestController.CreatTokenAbstract;
import com.southbrmeme.Domain.ObjectValue.CreatToken;
import com.southbrmeme.Application.DTO.Token;
import com.southbrmeme.Application.IService.IUserService;
import com.southbrmeme.Application.Model.UserModel;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import com.southbrmeme.Interface.RestController.Safe;
import javax.inject.Inject;

/**
 * REST Web Service
 *
 * @author dev
 */
@Path("user")
public class RestUser {

    @Context
    private UriInfo context;

    private final Gson gson;
    private final IUserService iUserService;

    @Inject
    public RestUser(IUserService iUserService) {
        this.iUserService = iUserService;
        this.gson = new Gson();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public String login(String content) {
        UserModel user = (UserModel) gson.fromJson(content, UserModel.class);
        return this.gson.toJson(iUserService.login(user));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("insert")
    public String insert(String content) {
        UserModel user = (UserModel) gson.fromJson(content, UserModel.class);
        return this.gson.toJson(iUserService.insert(user));
    }

    @Safe
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("update")
    public String update(String content, @HeaderParam("Authorization") String header) {

        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);
        UserModel user = (UserModel) gson.fromJson(content, UserModel.class);
        user.setId(token.getId());

        return this.gson.toJson(iUserService.update(user));
    }

    @Safe
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("delete")
    public String delete(@HeaderParam("Authorization") String header) {

        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);
        UserModel user = new UserModel();
        user.setId(token.getId());

        return this.gson.toJson(iUserService.delete(user));
    }

    @Safe
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("data")
    public String data(@HeaderParam("Authorization") String header) {

        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);
        UserModel user = new UserModel();
        user.setId(token.getId());

        return this.gson.toJson(iUserService.data(user));
    }

}
