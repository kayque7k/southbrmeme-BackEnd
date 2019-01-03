/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Application.IService;

import com.southbrmeme.Application.Model.Model;
import com.southbrmeme.Application.Model.UserModel;

/**
 *
 * @author Kayque Rodrigues
 */
public interface IUserService<T extends Model> {
    
    public T insert(UserModel user);
    
    public T update(UserModel user);
    
    public T login(UserModel user);
   
    public T delete(UserModel user);
    
    public T data(UserModel user);
}
