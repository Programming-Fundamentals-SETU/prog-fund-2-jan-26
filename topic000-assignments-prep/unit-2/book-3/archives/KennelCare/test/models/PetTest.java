package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    Owner owner;

    Dog dog;

    @BeforeEach
    void setUp() {
        owner = new Owner(100, "John Doe", "0871234567");
        dog = new Dog("Buddy", 5, owner, 1000, 'M', true, 10, false, "Lab", false);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("Buddy", dog.getName());
        assertEquals(5, dog.getAge());
        assertEquals(owner, dog.getOwner());
        assertEquals(1000, dog.getId());
    }

    @Test
    void testSetters() {
        dog.setName("Max");
        assertEquals("Max", dog.getName());

        dog.setAge(10);
        assertEquals(10, dog.getAge());

        Owner newOwner = new Owner(101, "Jane Smith", "0871234567");
        dog.setOwner(newOwner);
        assertEquals(newOwner, dog.getOwner());
    }

    @Test
    void testToString() {
        String result = dog.toString();
        assertTrue(result.contains("Buddy"));
        assertTrue(result.contains("Age: 5"));
        assertTrue(result.contains("Days attending"));
    }

    @Test
    void testDaysAttendingSetterGetter() {
        boolean[] days = {true, false, false, false, false, false, false};
        dog.setDaysAttending(days);

        assertArrayEquals(days, dog.getDaysAttending());
    }
}