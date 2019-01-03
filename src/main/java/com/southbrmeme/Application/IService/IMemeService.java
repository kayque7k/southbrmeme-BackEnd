/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Application.IService;

import com.southbrmeme.Application.Model.MemeModel;
import com.southbrmeme.Application.Model.Model;
import com.southbrmeme.Domain.Entity.MemeEntity;
import java.io.InputStream;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author Kayque Rodrigues
 */
public interface IMemeService<T extends Model> {

    public T insert(MemeModel meme, InputStream fileInputStream, FormDataContentDisposition fileMetaData);

    public T update(MemeModel meme, InputStream fileInputStream, FormDataContentDisposition fileMetaData);

    public T delete(MemeModel meme);

    public List<T> getMyMeme(MemeModel meme);

    public List<T> getMeme();

    public List<T> getMeme(List<MemeModel> memes);

    public T getDownload(MemeModel meme);

    public T getData();
}
