package br.com.lucasnery;

import br.com.lucasnery.exception.StringInvalidaException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 12/02/2017.
 */
public class CamelCaseParser {

    public List<String> converterCamelCase(String original) {
        if(stringEstaVazia(original))
            return new ArrayList<String>();

        validarInputOriginal(original);
        return separaStringCamelCaseEmListaSemCamelCase(original);
    }

    public boolean stringEstaVazia(String original){
        return original == null || original.equals("");
    }

    private void validarInputOriginal(String original) {
        if(Character.isDigit(original.charAt(0))){
            throw new StringInvalidaException();
        }

        for(int i = 1; i < original.length(); i++){
            if(!Character.isLetterOrDigit(original.charAt(i)))
                throw new StringInvalidaException();
        }
    }

    private boolean podeSepararPalavra(char charA, char charB, char charC){
        if(Character.isLowerCase(charA) && (Character.isUpperCase(charB) || Character.isDigit(charB)))
            return true;
        else if(Character.isDigit(charA) && !Character.isDigit(charB))
            return true;
         else if(Character.isUpperCase(charA) && Character.isUpperCase(charB) && Character.isLowerCase(charC))
             return true;
        else
            return false;
    }

    private ArrayList<String> transformaListaCamelCaseEmListaSemCamelCase(ArrayList<String> listaCamelCase){
        ArrayList<String> listaSemCamelCase = new ArrayList<String>();
        for (String palavraCamelCase : listaCamelCase) {
           listaSemCamelCase.add(parsePalavraCamelCaseParaSemCamelCase(palavraCamelCase));
        }
        return listaSemCamelCase;
    }

    private ArrayList<String> separaStringCamelCaseEmListaSemCamelCase(String stringOriginal){
        ArrayList<String> listaPalavras = new ArrayList<String>();
        for(int i = 1, ultimoIndice = 0; i < stringOriginal.length(); i++){
            if(i == stringOriginal.length() - 1){
                listaPalavras.add(stringOriginal.substring(ultimoIndice));
            } else if(podeSepararPalavra(stringOriginal.charAt(i-1), stringOriginal.charAt(i), stringOriginal.charAt(i+1))){
                listaPalavras.add(stringOriginal.substring(ultimoIndice, i));
                ultimoIndice = i;
            }
        }
        return transformaListaCamelCaseEmListaSemCamelCase(listaPalavras);
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
