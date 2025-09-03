package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeveloperTest {
    Developer validDeveloperShort,validDeveloper29Chars, validDeveloper30Chars,inValidDeveloper31Chars, inValidDeveloperManyChars;
    @BeforeEach
    void setUp() {
        validDeveloperShort = new Developer("Google", "http://google.com");
        validDeveloper29Chars = new Developer("12345678901234567890123456789", "http://google.com");
        validDeveloper30Chars = new Developer("123456789012345678901234567890", "http://google.com");
        inValidDeveloper31Chars = new Developer("1234567890123456789012345678901", "http://dodgy,com");
        inValidDeveloperManyChars = new Developer("123456789012345678901234567890112345678901234567", "http://dodgy,com");
    }

    @AfterEach
    void tearDown() {
        validDeveloperShort = validDeveloper29Chars = validDeveloper30Chars = inValidDeveloper31Chars = inValidDeveloperManyChars =  null;
    }
    @Test
    void constructorForValidDeveloper() {
        assertEquals("Google", validDeveloperShort.getDeveloperName());
        assertEquals("12345678901234567890123456789", validDeveloper29Chars.getDeveloperName());
        assertEquals("123456789012345678901234567890", validDeveloper30Chars.getDeveloperName());
        assertEquals("http://google.com", validDeveloperShort.getDeveloperWebsite());

    }
    @Test
    void constructorForInvalidDeveloper() {
        assertEquals("123456789012345678901234567890", inValidDeveloper31Chars.getDeveloperName());
        assertEquals("123456789012345678901234567890", inValidDeveloperManyChars.getDeveloperName());
        assertEquals("<no website>", inValidDeveloper31Chars.getDeveloperWebsite());

    }
    @Test
    void setDeveloperName() {
        assertEquals("Google", validDeveloperShort.getDeveloperName());
        validDeveloperShort.setDeveloperName("Alphabet");
        assertEquals("Alphabet", validDeveloperShort.getDeveloperName());
        validDeveloperShort.setDeveloperName("12345678901234567890123456789"); //29 chars - valid
        assertEquals("12345678901234567890123456789", validDeveloperShort.getDeveloperName());
        validDeveloperShort.setDeveloperName("123456789012345678901234567890"); //30 chars - valid
        assertEquals("123456789012345678901234567890", validDeveloperShort.getDeveloperName());
        validDeveloperShort.setDeveloperName("Alphabet");
        validDeveloperShort.setDeveloperName("1234567890123456789012345678901"); //31 chars invalid
        assertEquals("Alphabet", validDeveloperShort.getDeveloperName());
        validDeveloperShort.setDeveloperName("1234567890123456789012345678901234567890"); //40 chars invalid
        assertEquals("Alphabet", validDeveloperShort.getDeveloperName());

    }

    @Test
    void setDeveloperWebsite() {
        assertEquals("http://google.com", validDeveloperShort.getDeveloperWebsite());
        validDeveloperShort.setDeveloperWebsite("http://alphabet,com");
        assertEquals("http://google.com", validDeveloperShort.getDeveloperWebsite());
        validDeveloperShort.setDeveloperWebsite("https://www.wit.ie");
        assertEquals("https://www.wit.ie", validDeveloperShort.getDeveloperWebsite());

        assertEquals("<no website>", inValidDeveloper31Chars.getDeveloperWebsite());
        inValidDeveloper31Chars.setDeveloperWebsite("http://www.wit.ie");
        assertEquals(  "http://www.wit.ie" ,inValidDeveloper31Chars.getDeveloperWebsite());
    }

    @Test
    void testToString() {
      String devString = validDeveloperShort.toString();
      assertTrue(devString.contains("Developer Name"));
      assertTrue(devString.contains("Google"));
      assertTrue(devString.contains("http://google.com"));

      String invalDevString = inValidDeveloper31Chars.toString();
      assertTrue(invalDevString.contains("Developer Name"));
      assertTrue(invalDevString.contains("123456789012345678901234567890"));
      assertTrue(invalDevString.contains("<no website>"));

    }
}