import org.junit.Assert;
import org.junit.Test;
import ru.muctr.SimpleMethods;

public class SimpleMethodsTest {

    @Test
    public void testMaxNumbersOfArrays(){
        int[] a = {1, 3, 4};
        int[] b = {5, 2, 8};
        int[] expected_result = {5, 3, 8};
        Assert.assertArrayEquals(expected_result, SimpleMethods.maxNumbersOfArrays(a, b));
    }

    @Test
    public void testIsHaveDigit(){
        SimpleMethods sm = new SimpleMethods();
        Assert.assertTrue(sm.isHaveDigit("asdolj2hokj"));
    }
}
