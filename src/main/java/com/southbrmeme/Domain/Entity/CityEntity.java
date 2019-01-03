/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Domain.Entity;

import com.southbrmeme.Application.Model.Model;

/**
 *
 * @author dev
 */
public class CityEntity extends Entity {

    private int id;
    private String city;

    public CityEntity(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public CityEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public Model getModel() {
        return null;
    }

}
