import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Class for the Employee class
 * Only methods from the abstract class are tested.
 * @author Siobhan Drohan & Mairead Meagher
 * @version 10/19
 */

public class EmployeeTest {
	
	private AdminWorker empNormal1, empNormal2;
	private AdminWorker empTestValidation;

	/**
	 * Method to set up data for testing. 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		empNormal1 = new AdminWorker("Donald", "Duck", "1234567xx", 20, 10, 0);
		empNormal2 = new AdminWorker("David", "Brent", "1234567xx", 40.5, 10, 0);
		empTestValidation = new AdminWorker("1234567890123", "1234567890123", "xx34567xx", -1, 5, 0);
	}

	/**
	 * Test method for Employee constructor
	 */
	@Test
	public void testConstructor() {
		//test on valid data
		assertEquals("Donald", empNormal1.getFirstName());
		assertEquals("Duck", empNormal1.getSecondName());
		assertEquals(20, empNormal1.getHoursWorked(), 0.01);
		assertEquals(10, empNormal1.getHourlyRate(), 0.01);
		assertEquals("1234567xx",  empNormal1.getPpsNumber ());


		//test on invalid data
		assertEquals("1234567890", empTestValidation.getFirstName());
		assertEquals("1234567890", empTestValidation.getSecondName());
		assertEquals(0, empTestValidation.getHoursWorked(), 0.01);
		assertEquals(9.80, empTestValidation.getHourlyRate(), 0.01);
		assertEquals("0000000XX",  empTestValidation.getPpsNumber ());
	}
	
	/**
	 * Test method for getSalary(), testing for employees with and without overtime.
	 */
	@Test	
	public void testgetSalary(){
		//employee under NORMAL_WORKING_WEEKS hours
		assertEquals(200, empNormal1.getSalary(), 0.01 );
		//employee with overtime + fixed bonus
		assertEquals(415, empNormal2.getSalary(), 0.01 );
		empNormal1.setHourlyRate(0);
		assertEquals(200, empNormal1.getSalary(), 0.01 );
		empNormal1.setHourlyRate(20);
		assertEquals(400, empNormal1.getSalary(), 0.01 );
		empNormal1.setHourlyRate(20);
		assertEquals(400, empNormal1.getSalary(), 0.01 );
		empNormal1.setHoursWorked(40.5);
		empNormal1.setHourlyRate(20);
		assertEquals(830, empNormal1.getSalary(), 0.01);
	}
	
	/**
	 * Test method for getters and setters. 
	 */
	@Test	
	public void testSettersGetters() {
		assertEquals("Donald", empNormal1.getFirstName());
		empNormal1.setFirstName("John");
		assertEquals("John", empNormal1.getFirstName());
		empNormal1.setFirstName(("1234567890123"));
		assertEquals("John", empNormal1.getFirstName());


		assertEquals("1234567xx", empNormal1.getPpsNumber());
		empNormal1.setPpsNumber("2222222aa");
		assertEquals("2222222aa", empNormal1.getPpsNumber());
		empNormal1.setPpsNumber("123xxxxaa");
		assertEquals("2222222aa", empNormal1.getPpsNumber());

		assertEquals("Duck", empNormal1.getSecondName());
		empNormal1.setSecondName("Mouse");
		assertEquals("Mouse", empNormal1.getSecondName());
		empNormal1.setSecondName("Mickey Mouse");
		assertEquals("Mouse", empNormal1.getSecondName());

		assertEquals(20, empNormal1.getHoursWorked(), 0.01);
		empNormal1.setHoursWorked(40);
		assertEquals(40, empNormal1.getHoursWorked(), 0.01);
		empNormal1.setHoursWorked(-1);
		assertEquals(40, empNormal1.getHoursWorked(), 0.01);
		empNormal1.setHourlyRate(40);
		assertEquals(40, empNormal1.getHourlyRate(), 0.01);
		empNormal1.setHourlyRate(9.79);
		//ensure no change when invalid data used
		assertEquals(40, empNormal1.getHourlyRate(), 0.01);

	}

	/**
	 * Test method for getOverTime (= hourly-rate*2 * (the number of hours overtime))
	 */
	@Test
	public void getOverTime() {
		assertEquals(0, empNormal1.getOverTime(), 0.01);
		assertEquals(20, empNormal2.getOverTime(), 0.01);
		empNormal2.setHourlyRate(20);
		assertEquals(40, empNormal2.getOverTime(), 0.01);
	}
	/**
	 * Test method for toString - checks that all data fields are present.
	 */
	@Test
	public void testToString() {
		assertTrue(empNormal1.toString().contains("Donald"));
		assertTrue(empNormal1.toString().contains("Duck"));
		assertTrue(empNormal1.toString().contains("Hours Worked"));
		assertTrue(empNormal1.toString().contains("20"));
		assertTrue(empNormal1.toString().contains("per hour"));
		assertTrue(empNormal1.toString().contains("10"));
	}
}
