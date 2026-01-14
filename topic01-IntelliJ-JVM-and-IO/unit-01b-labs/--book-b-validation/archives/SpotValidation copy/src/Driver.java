public class Driver {

    public static void main(String args[]) {

        // Using the validation as shown. We will use this form of validation during this module.
        SpotWithValidation spot = new SpotWithValidation(10, 1000, "Happy New Semester  TOOLONGFROMHERE");
        System.out.println("Calling print on constructed spot - should be defaulted ");
        System.out.println(spot.toString());
        System.out.println("Trying setter with invalid value (-200)");
        spot.setX(-200);
        System.out.println("Calling print  - x value should not have changed");
        System.out.println(spot);
    }
}
