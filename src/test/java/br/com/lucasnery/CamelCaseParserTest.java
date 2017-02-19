package br.com.lucasnery;

import br.com.lucasnery.exception.StringInvalidaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Lucas on 12/02/2017.
 */
public class CamelCaseParserTest {

    private CamelCaseParser parser;

    @Before
    public void setup(){
        parser = new CamelCaseParser();
    }

    @Test
    public void testa_stringVazia(){
        // Dado que
        String camelCasedString = "";

        // Quando
        List<String> camelCaseParsed = parser.converterCamelCase(camelCasedString);

        // Entao
        Assert.assertTrue(camelCaseParsed.isEmpty());
    }

    @Test
    public void testa_nome(){
        // Dado que
        String palavraSemCamelCase = "nome";

        // Quando
        List<String> camelCasedParsed = parser.converterCamelCase(palavraSemCamelCase);

        // Entao
        Assert.assertEquals(palavraSemCamelCase, camelCasedParsed.get(0));
    }

    @Test
    public void testa_Nome(){
        // Dado que
        String palavraComCamelCase = "Nome";

        // Quando
        List<String> camelCaseParsed = parser.converterCamelCase(palavraComCamelCase);

        // Entao
        Assert.assertEquals("nome", camelCaseParsed.get(0));
    }

    @Test
    public void testa_nomeCompleto(){
        // Dado que
        String palavrasCamelCase = "nomeCompleto";

        // Quando
        List<String> camelCaseParsed = parser.converterCamelCase(palavrasCamelCase);

        // Entao
        Assert.assertEquals(2, camelCaseParsed.size());
        Assert.assertEquals("nome", camelCaseParsed.get(0));
        Assert.assertEquals("completo", camelCaseParsed.get(1));
    }

    @Test
    public void testa_NomeCompleto(){
        // Dado que
        String palavrasCamalCase = "NomeCompleto";

        // Quando
        List<String> camelCaseParsed = parser.converterCamelCase(palavrasCamalCase);

        // Entao
        Assert.assertEquals(2, camelCaseParsed.size());
        Assert.assertEquals("nome", camelCaseParsed.get(0));
        Assert.assertEquals("completo", camelCaseParsed.get(1));
    }

    @Test
    public void testa_CPF(){
        // Dado que
        String palavrasCamalCase = "CPF";

        // Quando
        List<String> camelCaseParsed = parser.converterCamelCase(palavrasCamalCase);

        // Entao
        Assert.assertEquals("CPF", camelCaseParsed.get(0));
        Assert.assertEquals(1, camelCaseParsed.size());
    }

    @Test
    public void testa_nomeCPF(){
        // Dado que
        String palavrasCamalCase = "nomeCPF";

        // Quando
        List<String> camelCaseParsed = parser.converterCamelCase(palavrasCamalCase);

        // Entao
        Assert.assertEquals("nome", camelCaseParsed.get(0));
        Assert.assertEquals("CPF", camelCaseParsed.get(1));
        Assert.assertEquals(2, camelCaseParsed.size());
    }

    @Test
    public void testa_numeroCPFContribuinte(){
        // Dado que
        String palavrasCamalCase = "numeroCPFContribuinte";

        // Quando
        List<String> camelCaseParsed = parser.converterCamelCase(palavrasCamalCase);

        // Entao
        Assert.assertEquals("numero", camelCaseParsed.get(0));
        Assert.assertEquals("CPF", camelCaseParsed.get(1));
        Assert.assertEquals("contribuinte", camelCaseParsed.get(2));
        Assert.assertEquals(3, camelCaseParsed.size());
    }

    @Test
    public void testa_recupera10Primeiros() {
        // Dado que
        String palavrasCamalCase = "recupera10Primeiros";

        // Quando
        List<String> camelCaseParsed = parser.converterCamelCase(palavrasCamalCase);

        // Entao
        Assert.assertEquals("recupera", camelCaseParsed.get(0));
        Assert.assertEquals("10", camelCaseParsed.get(1));
        Assert.assertEquals("primeiros", camelCaseParsed.get(2));
        Assert.assertEquals(3, camelCaseParsed.size());
    }

    @Test(expected = StringInvalidaException.class)
    public void testa_10Primeiros(){
        // Dado que
        String palavrasCamalCase = "10Primeiros";

        // Quando
        parser.converterCamelCase(palavrasCamalCase);
    }

    @Test(expected = StringInvalidaException.class)
    public void testa_nomeCerquilhaComposto(){
        // Dado que
        String palavrasCamalCase = "nome#Composto";

        // Quando
        parser.converterCamelCase(palavrasCamalCase);
    }
}
