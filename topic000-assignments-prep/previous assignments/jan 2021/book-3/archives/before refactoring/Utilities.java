
//TODO JavaDoc
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class UtilitiesTest {
//No need for fields here as using all methods as static.


        //TODO - change this to max10Chars
        @Test
        public void max30Chars() {
            assertEquals("1234567890", Utilities.max30Chars("1234567890"));
            assertEquals("123456789012345678901234567890", Utilities.max30Chars("123456789012345678901234567890-123"));
            assertEquals("", Utilities.max30Chars(""));
        }



        @Test
        public void validIntRange() {
            assertTrue(Utilities.validIntRange(1, 10, 5));
            assertTrue(Utilities.validIntRange(1, 10, 1));
            assertTrue(Utilities.validIntRange(1, 10, 10));
            assertFalse(Utilities.validIntRange(1, 10, 0));
            assertFalse(Utilities.validIntRange(1, 10, 11));

        }

        @Test
        public void validIntNonNegative() {
            assertTrue(Utilities.validIntNonNegative(0));
            assertTrue(Utilities.validIntNonNegative(1));
            assertTrue(Utilities.validIntNonNegative(1000));
            assertFalse(Utilities.validIntNonNegative(-1));


        }


        @Test
        public void validIndex() {
            ArrayList<String> strs = new ArrayList<String>();
            strs.add("hello");
            strs.add("there");
            strs.add("how are");
            strs.add("you today");
            assertTrue(Utilities.validIndex(2, strs));
            assertTrue(Utilities.validIndex(0, strs));
            assertFalse(Utilities.validIndex(-1, strs));
            assertTrue(Utilities.validIndex(3, strs));
            assertFalse(Utilities.validIndex(4, strs));
        }

        @Test
        public void validPercentBonus() {
        //TODO - Write validPercentBonus()
        }

        @Test
        public void validDoubleNonNegative() {
        //TODO - Write validDoubleNonNegative()
        }


        @Test
        public void validPPS() {
        //TODO - Write validPPS()
        }

    }

