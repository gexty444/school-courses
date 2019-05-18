package w4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)

public class QuickSortTest {

    public int[] input, output;

    public QuickSortTest (int[] output, int[] input) {
        this.input = input;
        this.output = output;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{{new int[]{2, 3, 5, 7}, new int[]{3, 7, 2, 5}},
                {new int[]{3, 4, 5, 7, 9}, new int[]{9, 4, 7, 3, 5}},
                {new int[]{1, 7, 8, 8, 9}, new int[]{9, 8, 8, 7, 1}}
        });
    }


    @Test
    public void quickSortTest1(){
        QuickSort a = new QuickSort();
        a.sort(input);
        assertArrayEquals(output,input);

    }

}
