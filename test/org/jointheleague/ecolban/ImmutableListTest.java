package org.jointheleague.ecolban;

import java.util.NoSuchElementException;

import org.junit.Test;

import junit.framework.TestCase;

public class ImmutableListTest extends TestCase {

    @SuppressWarnings("unchecked")
    @Test
    public void testEmptyList() {
        ImmutableList<Integer> e = EmptyList.getInstance();
        assertTrue(e.isEmpty());
        assertEquals(e, e.remove(1));
        assertFalse(e.push(1).isEmpty());
        assertFalse(e.append(1).isEmpty());
        assertEquals(0, e.length());
        assertEquals("()", e.toString());
        try {
            e.head();
            fail("EmptyList.head() should throw a NoSuchElementException");
        } catch (NoSuchElementException exc) {
        } catch (Exception exc) {
            fail("EmptyList.head() should throw a NoSuchElementException");
        }
        try {
            e.tail();
            fail("EmptyList.tail() should throw a NoSuchElementException");
        } catch (NoSuchElementException exc) {
        } catch (Exception exc) {
            fail("EmptyList.tail() should throw a NoSuchElementException");
        }
        
        assertTrue(e.equals(EmptyList.getInstance()));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testListNodeWithInteger() {
        ImmutableList<Integer> lst0 = EmptyList.getInstance();
        ImmutableList<Integer> lst1 = lst0.push(1);
        ImmutableList<Integer> lst2 = lst1.append(2);
        ImmutableList<Integer> lst3 = lst2.append(3);
        ImmutableList<Integer> lst4 = lst3.remove(2);
        ImmutableList<Integer> lst5 = lst4.push(0);

        assertTrue(lst0.isEmpty());
        assertTrue(!lst1.isEmpty());
        assertTrue(!lst2.isEmpty());
        assertTrue(!lst3.isEmpty());
        assertTrue(!lst4.isEmpty());
        assertEquals(1, lst1.length());
        assertEquals(2, lst2.length());
        assertEquals(3, lst3.length());
        assertEquals(2, lst4.length());
        assertEquals("()", lst0.toString());
        assertEquals("(1)", lst1.toString());
        assertEquals("(1 2)", lst2.toString());
        assertEquals("(1 2 3)", lst3.toString());
        assertEquals("(1 3)", lst4.toString());
        assertEquals("(0 1 3)", lst5.toString());
        assertEquals(lst4.tail(), lst3.tail().tail());
        assertEquals(lst4, lst5.tail());
        assertEquals(Integer.valueOf(1), lst1.head());
        assertEquals(Integer.valueOf(0), lst5.head());
        assertTrue(lst1.tail().isEmpty());
        assertTrue(lst5.tail().tail().tail().isEmpty());

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testListNodeWithString() {
        ImmutableList<String> lst0 = EmptyList.getInstance();
        ImmutableList<String> lst1 = lst0.push("two");
        ImmutableList<String> lst2 = lst1.push("one");
        ImmutableList<String> lst3 = lst2.append("three");
        ImmutableList<String> lst4 = lst3.remove("two");
        ImmutableList<String> lst5 = lst4.push("zero");
        ImmutableList<String> lst6 = lst3.append("four").append("four").append("five");
        ImmutableList<String> lst7 = lst6.remove("four").remove("five");

        assertTrue(lst0.isEmpty());
        assertTrue(!lst1.isEmpty());
        assertTrue(!lst2.isEmpty());
        assertTrue(!lst3.isEmpty());
        assertTrue(!lst4.isEmpty());
        assertEquals(1, lst1.length());
        assertEquals(2, lst2.length());
        assertEquals(3, lst3.length());
        assertEquals(2, lst4.length());
        assertEquals(6, lst6.length());
        assertEquals("()", lst0.toString());
        assertEquals("(two)", lst1.toString());
        assertEquals("(one two)", lst2.toString());
        assertEquals("(one two three)", lst3.toString());
        assertEquals("(one three)", lst4.toString());
        assertEquals("(zero one three)", lst5.toString());
        assertEquals("(one two three four four five)", lst6.toString());
        assertEquals("(one two three)", lst7.toString());
        assertNotSame(lst3, lst7);
        assertEquals("two", lst1.head());
        assertEquals("zero", lst5.head());
        assertTrue(lst1.tail().isEmpty());
        assertTrue(lst5.tail().tail().tail().isEmpty());
        assertEquals(lst4.tail(), lst3.tail().tail());
        assertEquals(lst4, lst5.tail());
        assertTrue(lst3.equals(lst7));
        assertFalse(lst2.equals(lst3));
        assertTrue(lst0.push("one").remove("one").equals(lst0));

    }

}
