package _18_DebuggingAndUnitTesting.testing;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChallengeUtilitesTest {

    private ChallengeUtilites utilites;

    @Before
    public void setup() {
        utilites = new ChallengeUtilites();
    }


    @Test
    public void everyNthChar() {
        assertArrayEquals("el".toCharArray(), utilites.everyNthChar("hello".toCharArray(), 2));
        assertArrayEquals("гиперовка".toCharArray(), utilites.everyNthChar("гиперовка".toCharArray(), 22));
    }

    @Test
    public void removePares() {
        assertEquals("QWERTY", utilites.removePares("QQWERRTYYYY"));
        assertEquals("123456", utilites.removePares("122334556"));
        assertEquals("Й", utilites.removePares("Й"));
        assertEquals("", utilites.removePares(""));
        assertNull(utilites.removePares(null));

    }

    @Test
    public void converter() {
        assertEquals(300, utilites.converter(10, 5));
    }

    @Test(expected = ArithmeticException.class)
    public void converterDividedByZero() {
        utilites.converter(10, 0);
        fail("Дожнобыть быть исключние ArithmeticException");

    }

    @Test
    public void nullIfOddlength() {
        assertNull(utilites.nullIfOddLength("odd"));
        assertNotNull(utilites.nullIfOddLength("ghbdftn2"));
    }
}