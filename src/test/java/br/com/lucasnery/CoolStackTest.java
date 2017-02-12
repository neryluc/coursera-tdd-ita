package test.java.br.com.lucasnery;

import br.com.lucasnery.CoolStack;
import br.com.lucasnery.exception.FullStackException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Lucas on 30/01/2017.
 */
public class CoolStackTest {

    private CoolStack coolStack;

    @Before
    public void initializeStack(){
        this.coolStack = new CoolStack(10);
    }

    @Test
    public void emptyStack(){
        assertTrue(coolStack.isEmpty());
        assertEquals(0, coolStack.size());
    }

    @Test
    public void pushOneElement(){
        coolStack.push("first");

        assertFalse(coolStack.isEmpty());
        assertEquals(1, coolStack.size());
        assertEquals("first", coolStack.first());
    }

    @Test
    public void pushTwoElementAndThenPopOne(){
        coolStack.push("first");
        coolStack.push("second");
        assertEquals(2, coolStack.size());
        assertEquals("second", coolStack.first());

        Object oldFirstElement = coolStack.pop();

        assertEquals(1, coolStack.size());
        assertEquals("first", coolStack.first());
        assertEquals("second", oldFirstElement);
    }

    @Test(expected = EmptyStackException.class)
    public void popWhenStackIsEmpty(){
        coolStack.pop();
    }

    @Test
    public void pushWhenStackIsFull(){
        for(int i = 0; i < 10; i++){
            coolStack.push("element" + i);
        }
        try{
            coolStack.push("lastElement");
            fail();
        } catch(FullStackException e){}

    }

}
