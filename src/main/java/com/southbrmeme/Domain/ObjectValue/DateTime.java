package com.southbrmeme.Domain.ObjectValue;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {


    public static String dataSistema() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date(new Date().getTime()));
    }

    public static Date dateSistema() {
        return new Date(System.currentTimeMillis());
    }

    public static String dia(String data) {
        return data.substring(0, 2);
    }

    public static String mes(String data) {
        return data.substring(3, 5);
    }

    public static String ano(String data) {
        return data.substring(6, 10);
    }

    public static String dataBanco(String data) {
        return ano(data) + "-" + mes(data) + "-" + dia(data);
    }

    public static String dataFormact(String data) {
        return dia(data) + "/" + mes(data) + "/" + ano(data);
    }

    public static String dataFormactAnoMes(String data) {
        return mes(data) + "/" + ano(data);
    }

    public static String dataBancoTime(String data) {
        return ano(data) + "-" + mes(data) + "-" + dia(data) + hourOfBanco(data);
    }

    public static String hour(String data) {
        return data.substring(data.indexOf(" "), data.length());
    }

    public static String hourOfBanco(String data) {
        return data.substring(data.indexOf(" "), data.length());
    }

    public static String secondminOwn(String data) {
        return data.substring(data.indexOf(" "), data.length() - 2)
                + (Integer.parseInt(data.substring(data.length() - 2, data.length() - 1)) - 1);
    }

}
