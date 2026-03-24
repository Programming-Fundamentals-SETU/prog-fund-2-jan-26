package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MammalTest {

    Owner owner;

    Dog dog;

    @BeforeEach
    void setUp() {
        owner = new Owner(123, "John Doe", "098666736");
        dog = new Dog("Rex", 5, owner, 1000, 'M', true, 10, false, "Lab", false);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals('M', dog.getSex());
        assertTrue(dog.isVaccinated());
        assertEquals(10, dog.getWeight());
        assertFalse(dog.isNeutered());
    }

    @Test
    void testSetters() {
        dog.setSex('F');
        assertEquals('F', dog.getSex());

        dog.setVaccinated(false);
        assertFalse(dog.isVaccinated());

        dog.setWeight(20);
        assertEquals(20, dog.getWeight());

        dog.setNeutered(true);
        assertTrue(dog.isNeutered());
    }

    @Test
    void testToString() {
        String result = dog.toString();
        assertTrue(result.contains("sex="));
        assertTrue(result.contains("vaccinated="));
        assertTrue(result.contains("weight="));
    }
}