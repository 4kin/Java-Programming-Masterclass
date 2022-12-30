package _18_DebuggingAndUnitTesting.testing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class ChallengeUtilitesTestParameterized {
    private ChallengeUtilites utilites;

    private String input;
    private String expected;

    public ChallengeUtilitesTestParameterized(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Before
    public void setUp() throws Exception {
        utilites = new ChallengeUtilites();
    }

    @Test
    public void removePares() {
        assertEquals(expected, utilites.removePares(input));
    }


    @Parameterized.Parameters
    public static Collection<Object[]> testConditions() {
        return Arrays.asList(new Object[][]{
                {"abcdeff", "abcdef"},
                {"ab88effg", "ab8efg"},
                {"12233445566", "123456"},
                {"zyzqqb", "zyzqb"},
                {"a", "a"},
                {"", ""}
        });
    }
}