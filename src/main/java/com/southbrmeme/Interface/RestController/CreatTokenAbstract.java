/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Interface.RestController;

import com.google.gson.JsonObject;
import com.southbrmeme.Application.DTO.Token;
import com.southbrmeme.Domain.Entity.UserEntity;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 *
 * @author Kayque Rodrigues
 */
public abstract class CreatTokenAbstract extends Token {

    public CreatTokenAbstract() {
        super();
    }

    public abstract boolean codificarToken(String token);

    public abstract UserEntity tokemUser(String token);

    public abstract String header();

    public abstract String payload(JsonObject object);

    public abstract String computeSignature(String baseString, String keyString) throws GeneralSecurityException, UnsupportedEncodingException;

    public abstract String token(JsonObject payloadObject);

    public abstract Token decodeToken(String token);
}
