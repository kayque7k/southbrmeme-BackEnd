/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Application.Model;

import com.southbrmeme.Domain.Entity.Entity;

/**
 *
 * @author joao.vrevangelista
 */
public class ReturnModel extends Model{
    
   private int id; 
   private String token; 
   private String answerText;
   private boolean answer;
   private byte[] image;

    public ReturnModel(String answerText, boolean answer) {
        this.answerText = answerText;
        this.answer = answer;
    }

    public ReturnModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

     
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public Entity getEntity() {
        return null;
    }
    
}
