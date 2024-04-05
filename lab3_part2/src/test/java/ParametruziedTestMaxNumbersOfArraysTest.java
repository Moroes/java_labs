import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.muctr.SimpleMethods;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParametruziedTestMaxNumbersOfArraysTest {


    private int[] array1;
    private int[] array2;
    private int[] expectedArray;

    public ParametruziedTestMaxNumbersOfArraysTest(int[] array1, int[] array2, int[] expectedArray) {
        this.array1 = array1;
        this.array2 = array2;
        this.expectedArray = expectedArray;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[]{4, 5, 6} },
                { new int[]{4, 5, 6}, new int[]{1, 8, 2}, new int[]{4, 8, 6} },
                { new int[]{}, new int[]{}, new int[]{1} }
        });
    }


    @Test
    public void testMaxNumbersOfArrays(){
        Assert.assertArrayEquals(expectedArray, SimpleMethods.maxNumbersOfArrays(array1, array2));
    }

}
