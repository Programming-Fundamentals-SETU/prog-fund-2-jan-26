import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Test Classe for the Manager class
 */

/**
 * @author Siobhan Drohan & Mairead Meagher
 * @version 15/3/2016
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
		manNormal1 = new Manager("Padraig", "Computing", "1234567aa",30, 20 );
		admin1 = new AdminWorker("Rachel", "Green", "1234567aa",20, 10, 10);
		sales1 = new SalesWorker("Selly", "Selly", "1234567aa",20, 10, 10);
		sales2 = new SalesWorker("Mad", "Men", "1234567aa",20, 10, 10);
		
		manNormal2 = new Manager("Big", "Boss", "1234567aa",20, 100);
		manNormal2.addDeptEmployee(admin1);
		manNormal2.addDeptEmployee(sales1);
	}

	/**
	 * Test method for Manager constructor
	 */
	@Test
	public void testManager() {
		assertEquals("Padraig", manNormal1.getFirstName());
		assertEquals("Computing", manNormal1.getSecondName());
		assertEquals(30, manNormal1.getHoursWorked(), 0.01);
		assertEquals(20, manNormal1.getHourlyRate(), 0.01);	
		assertEquals(0, manNormal1.numberInDept());
		assertEquals("Big", manNormal2.getFirstName());
		assertEquals("Boss", manNormal2.getSecondName());
		assertEquals(20, manNormal2.getHoursWorked(), 0.01);
		assertEquals(100, manNormal2.getHourlyRate(), 0.01);
		assertEquals(2, manNormal2.numberInDept());
	}

	/**
	 * Test method for getDept and setDept. (No validation on this field)
	 */
	@Test
	public void testConstructorsGetSetDept() {
		assertEquals(manNormal1.getFirstName(), "Padraig");
		manNormal1.setFirstName("Paddy");
		assertEquals(manNormal1.getFirstName(),"Paddy");
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
	     assertEquals(0, manNormal1.numberInDept());		
	     manNormal1.addDeptEmployee(sales1);
	     assertEquals(1, manNormal1.numberInDept());
	     assertEquals("Selly", manNormal1.getDept().get(0).getFirstName());
	}
	@Test
	public void removeEmployee() {
		assertEquals(2, manNormal2.numberInDept());
		manNormal2.removeEmployee(0);
		assertEquals(1, manNormal2.numberInDept());
		assertEquals("Selly", manNormal2.getDept().get(0).getFirstName());

	}
	@Test
	public void setDept() {
	    ArrayList<Employee> newDept = new ArrayList<Employee>();
	    newDept.add(new AdminWorker("Mairead", "Meagher", "1234567aa",20, 10, 100));
	    newDept.add(new SalesWorker("Siobhan", "Drohan", "1234567aa",20, 10, 100));
		newDept.add(new AdminWorker("Sinead", "O Leary", "1234567aa",20, 10, 100));
		manNormal2.setDept(newDept);
		assertEquals(3, manNormal2.numberInDept());
		assertEquals("Mairead", manNormal2.getDept().get(0).getFirstName());


	}
}
