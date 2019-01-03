/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Interface.Controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.southbrmeme.Interface.RestController.CreatTokenAbstract;
import com.southbrmeme.Domain.ObjectValue.CreatToken;
import com.southbrmeme.Application.DTO.Token;
import com.southbrmeme.Application.IService.IMemeService;
import com.southbrmeme.Application.Model.MemeModel;
import java.util.List;
import javax.inject.Inject;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import com.southbrmeme.Interface.RestController.Safe;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * REST Web Service
 *
 * @author dev
 */
@Path("meme")
public class RestMeme {

    @Context
    private UriInfo context;

    private final IMemeService iMemeService;
    private final Gson gson;

    @Inject
    public RestMeme(IMemeService iMemeService) {
        this.iMemeService = iMemeService;
        this.gson = new Gson();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public String getList() {
        return this.gson.toJson(iMemeService.getMeme());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public String getList(String content) {
        List<MemeModel> memes = (List<MemeModel>) gson.fromJson(content, new TypeToken<List<MemeModel>>() {
        }.getType());
        return this.gson.toJson(iMemeService.getMeme(memes));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("download/{id}")
    public String getDownload(@PathParam("id") int id) {
        MemeModel meme = new MemeModel();
        meme.setId(id);
        return this.gson.toJson(iMemeService.getDownload(meme));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("data")
    public String getData() {
        return this.gson.toJson(iMemeService.getData());
    }

    @Safe
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mylist")
    public String getMyList(@HeaderParam("Authorization") String header) {

        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);
        MemeModel meme = new MemeModel();
        meme.setIduser(token.getId());

        return this.gson.toJson(iMemeService.getMyMeme(meme));
    }

    @Safe
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("insert/commit/{commit}")
    public String insert(
            @FormDataParam("image") InputStream fileInputStream,
            @FormDataParam("image") FormDataContentDisposition fileMetaData,
            @PathParam("commit") String commit,
            @HeaderParam("Authorization") String header) {

        MemeModel meme = new MemeModel();
        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);
        meme.setIduser(token.getId());
        meme.setCommit(commit);

        return this.gson.toJson(iMemeService.insert(meme, fileInputStream, fileMetaData));
    }

    @Safe
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("update/id/{id}/commit/{commit}")
    public String update(
            @FormDataParam("image") InputStream fileInputStream,
            @FormDataParam("image") FormDataContentDisposition fileMetaData,
            @PathParam("id") int id,
            @PathParam("commit") String commit,
            @HeaderParam("Authorization") String header) {

        MemeModel meme = new MemeModel();
        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);
        meme.setIduser(token.getId());
        meme.setId(id);
        meme.setCommit(commit);

        return this.gson.toJson(iMemeService.update(meme, fileInputStream, fileMetaData));
    }

    @Safe
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public String delete(@PathParam("id") int id, @HeaderParam("Authorization") String header) {

        MemeModel meme = new MemeModel();
        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);
        meme.setIduser(token.getId());
        meme.setId(id);

        return this.gson.toJson(iMemeService.delete(meme));
    }

}
