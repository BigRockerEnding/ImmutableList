package org.jointheleague.ecolban;

import java.util.NoSuchElementException;

public class EmptyList<T> implements ImmutableList<T> {
    
    @SuppressWarnings("rawtypes")
    private static EmptyList instance = new EmptyList();
    
    private EmptyList() {
        super();
    }
    
    @SuppressWarnings("rawtypes")
    public static EmptyList getInstance() {
        return instance;
    }

    @Override
    public boolean isEmpty() {
    	return true;
    }

    @Override
    public ImmutableList<T> remove(T e) {
    	return this;
    }

    @Override
    public int length() {
    	return 0;
    }

    @Override
    public ImmutableList<T> push(T e) {
    	return append(e);
    }

    @Override
    public ImmutableList<T> append(T e) {
    	return new ListNode<T>(e, this);
    }

    @Override
    public T head() {
    	throw new NoSuchElementException("EmptyList has no head.");
    }

    @Override
    public ListNode<T> tail() {
    	throw new NoSuchElementException("EmptyList has no tail.");
    }
    
    @Override
    public String toString() {
    	return "()";
    }
    
    @Override
    public int hashCode() {
        return 1;
    }
    
    @Override
    public boolean equals(Object that) {
        return that == this;
    }

}
