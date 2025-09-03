package utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    @Test
    void booleanYNConversionReturnsCorrectResult(){
        assertEquals('Y', Utilities.booleanToYN(true));
        assertEquals('N', Utilities.booleanToYN(false));
    }

    @Test
    void YNtoBooleanConversionReturnsCorrectBoolean() {
        assertTrue(Utilities.YNtoBoolean('Y'));
        assertTrue(Utilities.YNtoBoolean('y'));
        assertFalse(Utilities.YNtoBoolean('n'));
        assertFalse(Utilities.YNtoBoolean('N'));
        assertFalse(Utilities.YNtoBoolean('x'));
    }

    @Test
    void validRangeWorksWithPositiveIntTestData(){
        assertTrue(Utilities.validRange(1, 1, 1));
        assertTrue(Utilities.validRange(1, 1, 2));
        assertTrue(Utilities.validRange(1, 0, 1));
        assertTrue(Utilities.validRange(1, 0, 2)) ;
        assertTrue(Utilities.validRange(-1, -2, -1)) ;
    }

    @Test
    void validRangeWorksWithPositiveDoubleTestData(){
        assertTrue(Utilities.validRange(1.0d, 1, 1));
        assertTrue(Utilities.validRange(1.0d, 1, 2));
        assertTrue(Utilities.validRange(1.0d, 0, 1));
        assertTrue(Utilities.validRange(1.0d, 0, 2)) ;
        assertTrue(Utilities.validRange(-1.0d, -2, -1)) ;
    }

    @Test
    void validRangeWorksWithNegativeTestData(){
        assertFalse(Utilities.validRange(1,0,0));
        assertFalse(Utilities.validRange(1,1,0));
        assertFalse(Utilities.validRange(1,2,1));
        assertFalse(Utilities.validRange(-1, -1, -2)) ;
    }

    @Test
    void validRangeInclExclWorksWithPositiveTestData(){
        assertTrue(Utilities.validRangeExclIncl(1, 0, 1));
        assertTrue(Utilities.validRangeExclIncl(1, 0, 2));
        assertTrue(Utilities.validRangeExclIncl(-1, -2, -1));
        assertTrue(Utilities.validRangeExclIncl(-1, -2, 0));
    }

    @Test
    void validRangeInclExclWorksWithNegativeTestData(){
        assertFalse(Utilities.validRangeExclIncl(1,1,1));
        assertFalse(Utilities.validRangeExclIncl(1,1,2));
        assertFalse(Utilities.validRangeExclIncl(-1,-1,0));
        assertFalse(Utilities.validRangeExclIncl(-1,-1,-1));
    }

    @Test
    void toTwoDecimalPlacesTruncatesCorrectly(){
        assertEquals(12, Utilities.toTwoDecimalPlaces(12), 0.01);
        assertEquals(12.9, Utilities.toTwoDecimalPlaces(12.9), 0.01);
        assertEquals(12.00, Utilities.toTwoDecimalPlaces(12.00123456), 0.01);
        assertEquals(12.01, Utilities.toTwoDecimalPlaces(12.01123456), 0.01);
    }

    @Test
    void truncateStringMethodTrucatesCorrectly(){
        assertEquals("123456789", Utilities.truncateString("1234567890", 9));
        assertEquals("1234567890", Utilities.truncateString("1234567890", 10));
        assertEquals("1234567890", Utilities.truncateString("1234567890", 11));
        assertEquals("", Utilities.truncateString("1234567890", 0));
        assertEquals("", Utilities.truncateString("", 0));
        assertEquals("", Utilities.truncateString("", 10));
    }

    @Test
    void validStringlengthReturnsCorrectResult(){
        //testing an empty string
        assertFalse(Utilities.validateStringLength("", -1));
        assertTrue(Utilities.validateStringLength("", 0));
        assertTrue(Utilities.validateStringLength("", 1));
        //testing on boundaries for non-empty String
        assertFalse(Utilities.validateStringLength("12345", 4));
        assertTrue(Utilities.validateStringLength("12345", 5));
        assertTrue(Utilities.validateStringLength("12345", 6));
    }

    @Test
    void isValidIndexReturnsFalseWhenArrayListEmpty(){
        List<String> emptyList = new ArrayList<>();
        assertFalse(Utilities.isValidIndex(emptyList, 0));
        assertFalse(Utilities.isValidIndex(emptyList, 1));
    }

    @Test
    void isValidIndexReturnsCorrectResultWhenArrayListIsPolulated(){
        List<String> list = new ArrayList<>(){{
            add("String one");
            add("String two");
            add("String three");
        }};

        assertFalse(Utilities.isValidIndex(list, -1));
        assertTrue(Utilities.isValidIndex(list,0));
        assertTrue(Utilities.isValidIndex(list,2));
        assertFalse(Utilities.isValidIndex(list, 3));
    }

    @Test
    void greaterThanOrEqualToReturnsCorrectAnswerOnBoundaries(){
        //testing int version
        assertFalse(Utilities.greaterThanOrEqualTo(-1, 0));
        assertTrue(Utilities.greaterThanOrEqualTo(0, 0));
        assertTrue(Utilities.greaterThanOrEqualTo(1, 0));

        //testing double version
        assertFalse(Utilities.greaterThanOrEqualTo(-1.0d, 0d));
        assertTrue(Utilities.greaterThanOrEqualTo(0.0d, 0d));
        assertTrue(Utilities.greaterThanOrEqualTo(1.0d, 0d));
   }
    @Test
    void validURL(){
        assertTrue(Utilities.isValidURL("https://www.wit.ie"));
        assertTrue(Utilities.isValidURL("http://www.wit.ie"));
        assertFalse(Utilities.isValidURL("www.wit.ie"));
        assertFalse(Utilities.isValidURL("http://www,wit,ie"));
        assertFalse(Utilities.isValidURL("https://www.wit,ie"));
    }
}