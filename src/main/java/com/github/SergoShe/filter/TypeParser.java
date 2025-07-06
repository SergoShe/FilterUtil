package com.github.SergoShe.filter;

public class TypeParser {

    public static Type detectType(String string) {
        if(isInteger(string.trim())){
            return Type.INTEGER;
        }
        if (isFloat(string.trim())){
            return Type.FLOAT;
        }
        return Type.STRING;
    }

    private static boolean isInteger(String string){
        try {
            Long.parseLong(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isFloat(String string){
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
