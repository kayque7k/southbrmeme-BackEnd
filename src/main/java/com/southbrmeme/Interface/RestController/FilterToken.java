/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Interface.RestController;

import com.southbrmeme.Domain.ObjectValue.CreatToken;
import java.io.IOException;
import java.security.Principal;
import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Kayque Rodrigues esta classe tem como objetivo analisar o cabeçalho
 * das mensagem json enviadas
 */
@Safe
@Provider
@Priority(Priorities.AUTHENTICATION)
public class FilterToken implements ContainerRequestFilter {

    private static final String AUTHORIZATION_PREFIX = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(AUTHORIZATION_PREFIX)) {
            throw new NotAuthorizedException("Usuario nao logado");
        }
        String tokenjwt = authorizationHeader.substring(AUTHORIZATION_PREFIX.length()).trim();

        try {
            CreatTokenAbstract jwt = new CreatToken();

            if (!jwt.codificarToken(tokenjwt)) {
                throw new NotAuthorizedException("Nao autorizado");
            }
            
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    public void modificarRequestContext(ContainerRequestContext requestContext, String login) {

        final SecurityContext currentSecurityContext = requestContext.getSecurityContext();

        requestContext.setSecurityContext(new SecurityContext() {

            @Override

            public Principal getUserPrincipal() {

                return new Principal() {

                    @Override

                    public String getName() {

                        return login;

                    }

                };

            }

            @Override

            public boolean isUserInRole(String role) {

                return true;

            }

            @Override

            public boolean isSecure() {

                return currentSecurityContext.isSecure();

            }

            @Override

            public String getAuthenticationScheme() {

                return "Bearer";

            }

        });

    }
}
