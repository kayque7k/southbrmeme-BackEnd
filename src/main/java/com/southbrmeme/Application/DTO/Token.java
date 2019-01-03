/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Application.DTO;

/**
 *
 * @author Kayque Rodrigues esta classe tem como objetivo obter um objeto do
 * token(jwt)
 */
public class Token {
    
    private long iss;
    private long exp;
    private int id;
    private String name;

   
    public Token() {
    }
    
    public void setToken(Token token) {
      this.iss = token.getIss();
      this.exp = token.getExp();
      this.id = token.getId();
      this.name = token.getName();
    }
    

    public Token(long iss, long exp, int id, String name) {
        this.iss = iss;
        this.exp = exp;
        this.id = id;
        this.name = name;
    }

    
    public long getIss() {
        return iss;
    }

    public void setIss(long iss) {
        this.iss = iss;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
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


}
