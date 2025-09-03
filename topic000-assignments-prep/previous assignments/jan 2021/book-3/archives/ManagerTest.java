import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Test Class for the (concrete) Manager class
 *
 * @author Siobhan Drohan & Mairead Meagher
 * @version 03/20
 */


public class ManagerTest {

	private Manager manNormal1, manNormal2;
	private AdminWorker admin1;
	private SalesWorker sales1, sales2;

	/**
	 * Method to set up data for testing. 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//todo: Add your own employee fields into the constructor calls here
		manNormal1 = new Manager(30, 20 );
		admin1 = new AdminWorker(20, 10, 10);
		sales1 = new SalesWorker(20, 10, 10);
		sales2 = new SalesWorker(20, 10, 10);

		manNormal2 = new Manager(20, 100);
		manNormal2.addDeptEmployee(admin1);
		manNormal2.addDeptEmployee(sales1);
	}

	/**
	 * Test method for Manager constructor
	 */
	@Test
	public void testManager() {
		//todo: Also validate your own employee fields in here (to make sure the super call constructor works correctly)
		assertEquals(30, manNormal1.getHoursWorked(), 0.01);
		assertEquals(20, manNormal1.getHourlyRate(), 0.01);	
		//todo: add an assert for manNormal1, to make sure that the number of employees in their department is 0

		assertEquals(20, manNormal2.getHoursWorked(), 0.01);
		assertEquals(100, manNormal2.getHourlyRate(), 0.01);
		//todo: add an assert for manNormal2, to make sure that the number of employees in their department is 2
	}

    /**
	 * Test method for getDept and setDept. (No validation on this field)
	 */
	@Test
	public void testConstructorsGetSetDept() {
		//todo - this is a sample test method idea that you could complete
	}

	@Test
	public void testCalculateSalary() {
		// manager with no employees
		assertEquals(600, manNormal1.calculateSalary(), 0.01);
		assertEquals(210, admin1.calculateSalary(), 0.01);
		assertEquals(220, sales1.calculateSalary(), 0.01);
		assertEquals(2004.3, manNormal2.calculateSalary(), 0.01);
		manNormal2.addDeptEmployee(sales2);
		assertEquals(2006.5, manNormal2.calculateSalary(), 0.01);
	}

    /**
	 * Test method for addDeptEmployee(Employee).
	 */
	@Test
	public void testAddDeptEmployee() {
		//todo: add an assert for manNormal1, to make sure that the number of employees in their department is 0
	     manNormal1.addDeptEmployee(sales1);
		//todo: add an assert for manNormal1, to make sure that the number of employees in their department is now 1
		//todo: get the first employee back from manNormal1's department.  Then do an assert on it to check one of the fields, maybe name
	}

	@Test
	public void removeEmployee() {
		//todo: add an assert for manNormal2, to make sure that the number of employees in their department is 2
		//todo: then remove the first employee from manNormal2's list
		//todo: add an assert for manNormal2, to make sure that the number of employees in their department is 1
		//todo: do an assert on the last remaining employee, maybe check their name is what you are expecting it to be.
	}

	@Test
	public void setDept() {
	    ArrayList<Employee> newDept = new ArrayList<Employee>();
		//todo: Add your own employee fields into the constructor calls here
	    newDept.add(new AdminWorker(20, 10, 100));
	    newDept.add(new SalesWorker(20, 10, 100));
		newDept.add(new AdminWorker(20, 10, 100));
		//todo: for manNormal2, call the setter for the department arraylist...set it to newDept
		//todo: assert that the number of employees in manNormal2 department is 3
		//todo: do an assert on manNormal2's first employee, maybe check their name is what you are expecting it to be.
	}
}
