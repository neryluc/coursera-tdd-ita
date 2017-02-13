package br.com.lucasnery;

import br.com.lucasnery.exception.StringInvalidaException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 12/02/2017.
 */
public class CamelCaseParser {

    public List<String> converterCamelCase(String original) {
        if(original == null || original == "")
            return new ArrayList<String>();
        else{
            validarEstrada(original);
            return separaStringCamelCaseEmListaSemCamelCase(original);
        }
    }

    private void validarEstrada(String original) {
        if(Character.isDigit(original.charAt(0))){
            throw new StringInvalidaException();
        }

        for(int i = 1; i < original.length(); i++){
            if(!Character.isLetterOrDigit(original.charAt(i)))
                throw new StringInvalidaException();
        }
    }

    private ArrayList<String> separaStringCamelCaseEmListaSemCamelCase(String stringOriginal){
        ArrayList<String> listaPalavrasSemCamelCase = new ArrayList<String>();
        char charAtual, charAFrente;

        for(int i = 0; i < stringOriginal.length(); i++){
            if(i == stringOriginal.length() - 1) {
                listaPalavrasSemCamelCase.add(parsePalavraCamelCaseParaSemCamelCase(stringOriginal));
            } else if(i != 0) {
                charAtual = stringOriginal.charAt(i);
                charAFrente = stringOriginal.charAt(i+1);

                if (Character.isLowerCase(charAtual) && (Character.isUpperCase(charAFrente) || Character.isDigit(charAFrente)) && i!=0) {
                    listaPalavrasSemCamelCase.add(parsePalavraCamelCaseParaSemCamelCase(stringOriginal.substring(0, i + 1)));
                    stringOriginal = stringOriginal.substring(i+1);
                    i = -1;
                } else if (Character.isDigit(charAtual) && !Character.isDigit(charAFrente) && i!=0) {
                    listaPalavrasSemCamelCase.add(parsePalavraCamelCaseParaSemCamelCase(stringOriginal.substring(0, i + 1)));
                    stringOriginal = stringOriginal.substring(i+1);
                    i = -1;
                } else if (Character.isUpperCase(charAtual) && (Character.isDigit(charAFrente) || Character.isLowerCase(charAFrente)) && i!=0) {
                    listaPalavrasSemCamelCase.add(parsePalavraCamelCaseParaSemCamelCase(stringOriginal.substring(0, i)));
                    stringOriginal = stringOriginal.substring(i);
                    i = -1;
                }
            }
        }

        return listaPalavrasSemCamelCase;
    }

    private String parsePalavraCamelCaseParaSemCamelCase(String palavraCamelCase) {
        if(palavraCamelCase == null && palavraCamelCase == "")
            return "";

        if(palavraCamelCase.length() == 1)
            return Character.toLowerCase(palavraCamelCase.charAt(0)) + "";

        if(deveDiminuirPrimeiroCaractere(palavraCamelCase))
           return Character.toLowerCase(palavraCamelCase.charAt(0)) + palavraCamelCase.substring(1);
        else
           return palavraCamelCase;
    }

    private boolean deveDiminuirPrimeiroCaractere(String stringOriginal){
        if(stringOriginal == null || stringOriginal == "")
            return false;

        if(stringOriginal.length() == 1)
            return true;

        return Character.isUpperCase(stringOriginal.charAt(0)) && Character.isLowerCase(stringOriginal.charAt(1));
    }

}
