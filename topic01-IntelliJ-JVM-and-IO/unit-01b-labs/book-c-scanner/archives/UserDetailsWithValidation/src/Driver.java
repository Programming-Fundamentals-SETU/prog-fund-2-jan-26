import java.util.Scanner;

/**
 * This class asks the user to enter their name, address, age and date of birth.
 * The entered data is then printed out to the user.
 *
 * @author (SETU)
 * @version (1.0)
 */

public class Driver {

    private Scanner input = new Scanner(System.in);
    private User user;

    public static void main(String[] arg) {
        Driver driver = new Driver();
        driver.addUser();
        driver.printUser();
    }

    private void addUser() {
        //obtaining the data from the user
        System.out.println("Please enter data that will test are the validations working, e.g. longer strings than are valid and an invalid value for height:");
        System.out.println("e.g. Name \'12345678901234567890123\'");  // should truncate to '12345678901234567890'
        System.out.println("e.g. Address \'123456789012345678901234567890123\'"); //should truncate to '123456789012345678901234567890'
        System.out.println("e.g. Date of Birth? \'1234567890123\'"); //should truncate to '1234567890'
        System.out.println("e.g. Height \'0\'"); //should default to .5

        System.out.println("Entering details");
        System.out.println("----------------");
        System.out.print("   Enter your name:          ");
        String name = input.nextLine();
        System.out.print("   Enter your address:       ");
        String address = input.nextLine();
        System.out.print("   Enter your date of birth: ");
        String dateOfBirth = input.nextLine();
        System.out.print("   Enter your height         ");
        double height = input.nextDouble();
        System.out.println();

        user = new User(name, address, dateOfBirth, height);

        System.out.println("If your validations are working, the following should be printed to console:");
        System.out.println("User{name='12345678901234567890', address='123456789012345678901234567890', dateOfBirth='1234567890', height=0.5}");
    }

    private void printUser()
    {
        //printing out the data to the user
        System.out.println("\n\nPrinting details of what your code has implemented");
        System.out.println("----------------");
        System.out.println(user);
        System.out.println("If these two values are the same, enjoy!");
    }

}

