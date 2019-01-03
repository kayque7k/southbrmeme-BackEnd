package com.southbrmeme.Interface.RestController;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("southbrmeme")
public class ApplicationConfig extends ResourceConfig {


    public ApplicationConfig() {
        register(new ApplicationBinder());
        addRest();
        property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
    }

    private void addRest() {
        register(com.southbrmeme.Interface.Controller.RestMeme.class);
        register(com.southbrmeme.Interface.Controller.RestUser.class);
        register(com.southbrmeme.Interface.RestController.FilterToken.class);
        register(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
        register(MultiPartFeature.class);      
    }

}