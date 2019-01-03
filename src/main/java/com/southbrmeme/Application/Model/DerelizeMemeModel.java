/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Application.Model;

import com.southbrmeme.Domain.Entity.Entity;
import com.southbrmeme.Domain.Entity.MemeEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dev
 */
public class DerelizeMemeModel extends Model{
    
    private ArrayList<MemeEntity> memes;

    public DerelizeMemeModel(ArrayList<MemeEntity> memes) {
        this.memes = memes;
    }
    
    public DerelizeMemeModel() {
    }

    public ArrayList<MemeEntity> getMemes() {
        return memes;
    }

    public void setMemes(ArrayList<MemeEntity> memes) {
        this.memes = memes;
    }

    @Override
    public Entity getEntity() {
        return null;
    }
}
