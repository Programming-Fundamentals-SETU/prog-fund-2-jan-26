#EmployeeAPI

This controller class is responsible  for storing and managing ALL the employees entered by the user via the console.  Note that there are no System.out.println statements in this class, nor are there any Scanner reads; all the I/O is in the other controller class, the *Driver* (and the utility classes, if you are using them).

![](img/EmployeeAPI.png)


##Fields

There is one private field, *employees*, which is an ArrayList of Employee.  It should be instantiated at declaration time.  


##Constructor

We will use the default constructor for this class (no need to code it).  For this reason, there is no constructor shown in the diagram above.


##Methods (getters and setters)

There are no getter or setter methods for the instance field *employees*.  Instead, we will use the following methods to manage the ArrayList.


##Methods (addEmployee)

This method will add a Employee object (passed as a parameter) to the ArrayList *employees*.  There is no validation in this method.


##Methods (getEmployee)

This method will return a Employee object at the location *index*, which is passed as a paramter.  There is some validation in this method:

- Check that the passed index exists in the ArrayList:

    - if it does exist, retrieve the associated Employee object and return it.
	- if the passed index is not valid, return *null*. 


##Methods (removeEmployee)

This method removes a Employee object at the location *index*, which is passed as a paramter.  There is some validation in this method:

- Check that the passed index exists in the ArrayList:

    - if it does exist, remove it from the ArrayList and return *true*.
    - if the passed index is not valid, return *false*. 


##Methods (numberOfEmployees)

This method returns the number of Employee objects stored in the ArrayList *Employees*.

CHANGE THESE TO 
##Methods (listOfEmployees, no parameter)

This method is similar-ish to a toString.  It builds (and returns) a String of all Employee objects in the ArrayList.  Each object is on a new line.  The beginning of the line has the index number of the object in the ArrayList e.g.

![](img/10.png)

If there are no songs in the ArrayList, the following message should be returned:  **No Employees stored**.


##Methods (listOfEmployees, String parameter)

This method is used when reporting on Employees by category.  It builds and returns a list of Employees (and their associated index number in the ArrayList) by either *Intern*, *Specialist* or *General*.  Each Employee object should be on a new line.  Sample output would be:

![](img/11.png)

If there are no Employees of the chosen type, return a message stating **No *Employee-type* Employees stored**. 


##Methods (searchEmployeesByName)

This method builds (and returns) an ArrayList of Employees containing all the Employee objects whose name matches the String entered by the user.  

Note: cater for case sensitivity in your searches i.e. *Siobhan, SIOBHAN and siobhan* should be considered the same for search purposes. 


##Methods (calculateAnnualFees)

This method adds all the fees for each Employee stored in the Employees ArrayList.


##Methods (save)

This method saves all Employee objects from the ArrayList songs to an XML file *Employees.xml*.  You can use the *xstream* component for this (like we did in class) or any approach you wish.


##Methods (load)

This method loads all saved Employee objects back into the program (i.e into the ArrayList Employees) from the XML file *Employees.xml*.  You can use the *xstream* component for this (like we did in class) or any approach you wish.

