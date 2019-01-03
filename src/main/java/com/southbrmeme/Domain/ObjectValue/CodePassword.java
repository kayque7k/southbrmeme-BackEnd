/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmeme.Domain.ObjectValue;

/**
 *
 * @author dev
 */
public class CodePassword {

    public static String encode(String password) {
        //Criptografa a String passada por parâmetro
        int contador, tamanho, codigoASCII;
        String senhaCriptografada = "";
        tamanho = password.length();
        contador = 0;
        while (contador < tamanho) {
            codigoASCII = password.charAt(contador) + 130;
            senhaCriptografada = senhaCriptografada + (char) codigoASCII;
            contador++;
        }
        return senhaCriptografada;
    }

    public static String decode(String password) {
        int contador, tamanho, codigoASCII;
        String senhaCriptografada = "";
        tamanho = password.length();
        contador = 0;
        while (contador < tamanho) {
            codigoASCII = password.charAt(contador) - 130;
            senhaCriptografada = senhaCriptografada + (char) codigoASCII;
            contador++;
        }
        return senhaCriptografada;
    }
}
