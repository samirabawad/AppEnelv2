package com.api.proyecto_enel.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidation {

    public static Boolean IsOnlyAlphabetic(String text){
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            System.out.println("La cadena contiene solo caracteres SI alfabéticos.");
            return true;
        } else {
            System.out.println("La cadena contiene caracteres NO alfabéticos.");
            return false;
        }
    }
    public static Boolean IsOnlyNumeric(String text){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            System.out.println("la cadena SI contiene solo caracteres numericos.");
            return true;
        }else{
            System.out.println("La cadena contiene caracteres NO numericos.");
            return false;
        }
    }
}
