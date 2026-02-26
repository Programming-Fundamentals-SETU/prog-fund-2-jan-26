import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLargest {

    @Test
    public void testOrder ()
    {
        assertEquals(9, Largest.largest(new int[] { 9, 8, 7 }));
        assertEquals(9, Largest.largest(new int[] { 8, 9, 7 }));
        assertEquals(9, Largest.largest(new int[] { 7, 8, 9 }));
    }

    @Test
    public void testDups ()
    {
        assertEquals(9, Largest.largest(new int[] { 9, 7, 9, 8 }));
    }

    @Test
    public void testOne ()
    {
        assertEquals(1, Largest.largest(new int[] { 1 }));
    }

    @Test
    public void testNegative ()
    {
        int[] negativeList = {-7, -8, -9};
        assertEquals(-7, Largest.largest(negativeList));
    }

/*
    @Test
    public void testOrder() {
        int[] arr = new int[3];
        arr[0] = 8;
        arr[1] = 9;
        arr[2] = 7;
        assertEquals(9, Largest.largest(arr));
    }
*/

}
