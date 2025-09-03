import java.util.*;

public class Driver {

    private Scanner input = new Scanner (System.in);

    public static void main(String[] args) {

        Driver driver = new Driver();
        driver.exercise_1();   //Uncomment the line with the method you want to run.
        //driver.exercise_2();
        //driver.exercise_3();
        //driver.exercise_4();
        //driver.exercise_5();
        //driver.exercise_6();
        //driver.exercise_7();
    }

    public void exercise_1() {

        int[] numbers = new int[6];

        for (int i = 0; i < numbers.length ; i++) {
            System.out.print("Enter value : ");
            numbers[i] = input.nextInt();
        }

        for (int i = 0; i < numbers.length ; i++) {
            System.out.println("value " + (i+1)  + " is: " + numbers[i]);
        }
    }


    public void exercise_2() {

        int[] numbers = new int[6];

        for (int i = 0; i<6 ; i++) {
            System.out.print("Enter value :");
            numbers[i] = input.nextInt();
        }

        for (int i = 5; i>=0 ; i--) {
            System.out.println("value " + i  + " is:" + numbers[i]);
        }
    }

    public void exercise_3() {

        int[] numbers = new int[6];

        for (int i = 0; i<6 ; i++) {
            System.out.print("Enter value :");
            numbers[i] = input.nextInt();
        }

        for (int i = 0; i<6 ; i++) {
            numbers[i]++ ;
        }

        for (int i = 0; i<6 ; i++) {
            System.out.println("value " + i  + " is:" + numbers[i]);
        }
    }

    public void exercise_4() {

        double[] numbers = new double[10];

        for (int i = 0; i<10 ; i++)
        {
            System.out.print("Enter wage :");
            numbers[i] = input.nextDouble() ;
        }

        for (int i = 0; i<10 ; i++)
        {
            System.out.println("Wage " + i  + " is:" + numbers[i]);
        }
    }

    public void exercise_5() {
        double[] numbers = new double[10];

        for (int i = 0; i<10 ; i++)
        {
            System.out.print("Enter wage (" + (i+1) + ") :");
            numbers[i] = input.nextDouble() ;
        }

        for (int i = 0; i<10 ; i++)
        {
            if (numbers[i] > 100 ) {
                System.out.println("Wage " + (i+1)  + " is:" + numbers[i]);
            }
        }
    }

    public void exercise_6()
    {
        double[] numbers = new double[10];
        double limit = 1000.0 ;
        double reduction = 0.1 ;

        for (int i = 0; i<10 ; i++) {
            System.out.print("Enter wage (" + (i+1) + ") :");
            numbers[i] = input.nextDouble() ;
        }

        for (int i = 0; i<10 ; i++) {
            if (numbers[i] > limit ) {
                numbers[i] = numbers[i] - (numbers[i] * reduction ) ;
            }
        }

        for (int i = 0; i<10 ; i++) {
            System.out.println("Wage " + (i+1)  + " is:" + numbers[i]);
        }
    }

    public void exercise_7() {

        int size = 10 ;
        double[] numbers = new double[size];
        double total = 0.0 ;

        for (int i = 0; i<size ; i++) {
            System.out.print("Enter wage (" + (i+1) + ") :");
            numbers[i] = input.nextDouble() ;
        }

        for (int i = 0; i<size ; i++) {
            total += numbers[i]  ;
        }
        System.out.println("Average Wage = " + (total / numbers.length) );
    }

}

