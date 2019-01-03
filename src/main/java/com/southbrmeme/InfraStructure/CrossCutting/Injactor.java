/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.InfraStructure.CrossCutting;

import com.southbrmeme.Application.IService.IMemeService;
import com.southbrmeme.Application.IService.IUserService;
import com.southbrmeme.Application.Model.Model;
import com.southbrmeme.Application.Service.MemeService;
import com.southbrmeme.Application.Service.UserService;
import com.southbrmeme.Domain.Entity.CityEntity;
import com.southbrmeme.Domain.Interface.ICityRepository;
import com.southbrmeme.Domain.Interface.IMemeRepository;
import com.southbrmeme.Domain.Interface.IUserRepository;
import com.southbrmeme.InfraStructure.Data.CityRepository;
import com.southbrmeme.InfraStructure.Data.MemeRepository;
import com.southbrmeme.InfraStructure.Data.UserRepository;
import org.glassfish.hk2.utilities.binding.AbstractBinder;


/**
 *
 * @author Kayque Rodrigues
 */
public class Injactor {

    public static void services(AbstractBinder abstractBinder) {
        abstractBinder.bind(MemeService.class).to(IMemeService.class);
        abstractBinder.bind(UserService.class).to(IUserService.class);
    }

    public static void repository(AbstractBinder abstractBinder) {
        abstractBinder.bind(MemeRepository.class).to(IMemeRepository.class);
        abstractBinder.bind(UserRepository.class).to(IUserRepository.class);
        abstractBinder.bind(CityRepository.class).to(ICityRepository.class);
    }

    public static void model(AbstractBinder abstractBinder) { 
        
    }
    
    public static void entity(AbstractBinder abstractBinder) {
    }
  
}
