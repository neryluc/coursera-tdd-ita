package br.com.lucasnery;

import br.com.lucasnery.exception.FullStackException;

import java.util.EmptyStackException;

/**
 * Created by Lucas on 30/01/2017.
 */
public class CoolStack {

    private Object[] elements = new Object[10];
    private int qnt = 0;

    public CoolStack(int initialQnt) {
        this.elements = new Object[initialQnt];
    }

    public boolean isEmpty() {
        return qnt == 0;
    }

    public int size() {
        return this.qnt;
    }

    public void push(Object element) {
        if(qnt == elements.length) throw new FullStackException();
        this.elements[qnt] = element;
        this.qnt++;
    }

    public Object first() {
        return this.elements[qnt - 1];
    }

    public Object pop() {
        if(isEmpty()) throw new EmptyStackException();
        Object first = first();
        elements[--qnt] = null;
        return first;
    }
}
