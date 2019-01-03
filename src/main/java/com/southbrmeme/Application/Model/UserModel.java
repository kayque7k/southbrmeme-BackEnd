/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Application.Model;

import com.southbrmeme.Domain.Entity.Entity;
import com.southbrmeme.Domain.Entity.UserEntity;

/**
 *
 * @author dev
 */
public class UserModel extends Model{
    
    private UserEntity userEntity;
    
    private int id;
    private String name;
    private String login;
    private String password;
    private String newPassword;
    private int idCity;
    private String dataCri;
    private boolean active;

    public UserModel(int id, String name, String login, String password, int idCity, String dataCri, boolean active) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.idCity = idCity;
        this.dataCri = dataCri;
        this.active = active;
    }

    public UserModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getDataCri() {
        return dataCri;
    }

    public void setDataCri(String dataCri) {
        this.dataCri = dataCri;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public Entity getEntity() {
        userEntity = new UserEntity();
        userEntity.setActive(active);
        userEntity.setDataCri(dataCri);
        userEntity.setId(id);
        userEntity.setIdCity(idCity);
        userEntity.setLogin(login);
        userEntity.setName(name);
        userEntity.setNewPassword(newPassword);
        userEntity.setPassword(password);
        return userEntity;
    }
    
    
    
    
}
