package jUnit;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class jUnit {

    @Test
    public void testingAssert(){

        //Variable declarations
        String string1 = "alex";
        String string2 = "alex";
        String string3 = "test";
        String string4 = "test";
        String string5 = null;
        int[] Array1 = { 1, 2, 3 };
        int[] Array2 = { 1, 2, 3 };
        boolean boolean1 = Boolean.parseBoolean(null);
        int variable1 = 1;
        int	variable2 = 2;

        //Assert statements
        assertArrayEquals(Array1, Array2);
        assertEquals(string1,string2);
        assertFalse(boolean1);
        assertNotNull(string4);
        assertNotSame(string1, string3);
        assertNull(string5);
        assertSame(string3, string4);
        assertThat(string1, is(string2));
        assertTrue(variable1<variable2);
        //fail("Here's a fail for fun.");

    }
}
