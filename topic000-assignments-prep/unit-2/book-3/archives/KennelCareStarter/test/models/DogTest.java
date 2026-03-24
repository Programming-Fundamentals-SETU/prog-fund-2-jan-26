package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    Owner owner;

    Dog dog;

    @BeforeEach
    void setUp() {
        owner = new Owner(1234, "John Doe", "12345678");
        dog = new Dog("Rex", 5, owner, 1000, 'M', true, 10, false, "Labrador", false);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Labrador", dog.getBreed());
        assertFalse(dog.isDangerousBreed());
    }

    @Test
    void testSetters() {
        dog.setBreed("Bulldog");
        assertEquals("Bulldog", dog.getBreed());

        dog.setDangerousBreed(true);
        assertTrue(dog.isDangerousBreed());
    }

    @Test
    void testToString() {
        String result = dog.toString();
        assertTrue(result.contains("[Dog]"));
        assertTrue(result.contains("Breed"));
        assertTrue(result.contains("Weekly Fee"));
    }

    @Test
    void testWeeklyFee() {
        boolean[] days = {true, true, false, false, false, false, false};
        dog.setDaysAttending(days);

        assertEquals(2 * 30, dog.calculateWeeklyFee());
    }

    @Test
    void testEquals() {
        Dog sameDog = new Dog("Max", 3, owner, 2000, 'F', true, 12, true, "Labrador", false);
        assertEquals(dog, sameDog);

        Dog differentDog = new Dog("Buddy", 2, owner, 3000, 'M', true, 8, false, "Bulldog", false);
        assertNotEquals(dog, differentDog);
    }
}