import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.muctr.SimpleMethods;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParametrizedIsHaveDigitTest {

    private String word;
    private boolean expected;

    public ParametrizedIsHaveDigitTest(String word, boolean expected) {
        this.word = word;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "asd2asd", true },
                { "2uwu2", true },
                { "uuuuuu", true },
        });
    }

    @Test
    public void testIsHaveDigit(){
        SimpleMethods sm = new SimpleMethods();
        Assert.assertEquals(sm.isHaveDigit(word), expected);
    }
}
