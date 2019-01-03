/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Domain.Interface;

import java.sql.PreparedStatement;

/**
 *
 * @author Kayque Rodrigues
 */
public abstract class IRepository {

    protected PreparedStatement pst = null;

    public PreparedStatement generyPst() {
        PreparedStatement pst = null;
        return pst = null;
    }

}
