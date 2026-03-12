package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product productBelow, productExact, productAbove, productZero;

    @BeforeEach
    void setUp() {
        //name, 19 chars, code 999, unitCost 1, inCurrentProductLine true.
        productBelow = new Product("Television 42Inches", 999, 1, true);
        //name, 20 chars, code 1000, unitCost 999, inCurrentProductLine true.
        productExact = new Product("Television 50 Inches", 1000, 999, true);
        //name, 21 chars, code 10000, unitCost 1000, inCurrentProductLine false.
        productAbove = new Product("Television 60 Inches.", 10000, 1000, false);
        //name, 0 chars, code 9999, unitCost 0, inCurrentProductLine false.
        productZero = new Product("", 9999, 0, false);
    }

    @AfterEach
    void tearDown() {
        productBelow = productExact = productAbove = productZero = null;
    }

    @Test
    void getProductName() {
        assertEquals("Television 42Inches", productBelow.getProductName());
        assertEquals("Television 50 Inches", productExact.getProductName());
        assertEquals("Television 60 Inches", productExact.getProductName());
        assertEquals("", productZero.getProductName());
    }


    @Test
    void getUnitCost() {
    }

    @Test
    void getProductCode() {
    }

    @Test
    void isInCurrentProductLine() {
    }

    @Test
    void testToString() {
    }
}