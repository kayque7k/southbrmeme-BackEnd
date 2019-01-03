/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Interface.RestController;

import com.southbrmeme.InfraStructure.CrossCutting.Injactor;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author Kayque Rodrigues
 */
public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        Injactor.services(this);
        Injactor.repository(this);
        Injactor.model(this);
        Injactor.entity(this);
    }

}
