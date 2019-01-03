/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Application.Model;

import com.southbrmeme.Domain.Entity.Entity;
import com.southbrmeme.Domain.Entity.MemeEntity;

/**
 *
 * @author dev
 */
public class MemeModel extends Model{
    
    private MemeEntity memeEntity;
    
    private int id;
    private String name;
    private String url;
    private String commit;
    private String dateCri;
    private int iduser;
    private boolean active;

    public MemeModel(int id, String url, String commit, String dateCri, int iduser,String name, boolean active) {
        this.id = id;
        this.url = url;
        this.commit = commit;
        this.dateCri = dateCri;
        this.iduser = iduser;
        this.name= name;
        this.active = active;
    }

    public MemeModel() {
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
    public Entity getEntity() {
        memeEntity = new MemeEntity();
        memeEntity.setId(id);
        memeEntity.setActive(active);
        memeEntity.setCommit(commit);
        memeEntity.setDateCri(dateCri);
        memeEntity.setIduser(iduser);
        memeEntity.setName(name);
        memeEntity.setUrl(url);
        return memeEntity;
    }
    
    
}
