package com.southbrmeme.Interface.RestController;

import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;

import java.lang.annotation.RetentionPolicy;

import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

@NameBinding

@Retention(RetentionPolicy.RUNTIME)

@Target({ElementType.TYPE, ElementType.METHOD})
//tornando a aplicação segura exigindo autenticaçao
public @interface Safe {
}
