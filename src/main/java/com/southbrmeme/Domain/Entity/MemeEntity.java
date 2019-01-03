/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Domain.Entity;

import com.southbrmeme.Application.Model.MemeModel;
import com.southbrmeme.Application.Model.Model;

/**
 *
 * @author dev
 */
public class MemeEntity extends Entity {

    private MemeModel memeModel;

    private int id;
    private String name;
    private String url;
    private String commit;
    private String dateCri;
    private int iduser;
    private boolean active;

    public MemeEntity(int id, String url, String commit, String dateCri, int iduser, String name, boolean active) {
        this.id = id;
        this.url = url;
        this.commit = commit;
        this.dateCri = dateCri;
        this.iduser = iduser;
        this.name = name;
        this.active = active;
    }

    public MemeEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getDateCri() {
        return dateCri;
    }

    public void setDateCri(String dateCri) {
        this.dateCri = dateCri;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Model getModel() {
        memeModel = new MemeModel();
        memeModel.setId(id);
        memeModel.setActive(active);
        memeModel.setCommit(commit);
        memeModel.setDateCri(dateCri);
        memeModel.setIduser(iduser);
        memeModel.setName(name);
        memeModel.setUrl(url);
        return memeModel;
    }

}
