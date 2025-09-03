//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private int noRows = 5;  //number of rows for all exercises in Exercise 1
    public static void main(String[] args) {
        // TO use : comment out all except the running of the method you wish to run
        Main menu = new Main();
        menu.exer1();
//        menu.exer2();
//        menu.exer3();
//        menu.exer4();
//        menu.exer5();
//        menu.exer6_1();
//        menu.exer6_2();
//        menu.exer6_3();
//        menu.exer6_4();
//        menu.exer6_5();
//        menu.exer6_6();
//        menu.exer7(5, 5);
    }
    public void exer1(){
        for (int i = 1; i<= 10; i++)
            System.out.println(i);
    }
    public void exer2(){
        for (int i = 15; i<= 20; i++)
            System.out.println(i);
    }
    public void exer3(){
        for (int i = 10; i>= 1; i--)
            System.out.println(i);
    }
    public void exer4(){
        for (int i = 20; i>= 15; i--)
            System.out.println(i);
    }
    public void exer5(){
        int total = 0;
        for (int i = 1; i<= 10; i++)
            total += i;
        System.out.println(total);
    }

public void exer6_1(){
    for (int i = 1; i < noRows; i++) {
        for (int j = 1; j <= 10; j++)
        {
            System.out.print('*');
        }
        System.out.println();  // go to next line
    }

}
    public void exer6_2(){
        for (int i = 1; i <= noRows; i++) {
            for (int j = 1; j <= i; j++)
            {
                System.out.print('*');
            }

            System.out.println();  // go to next line
        }

    }
    public void exer6_3() {
        for (int i = 1; i <= noRows; i++) {
            for (int j = 1; j <= (noRows-i); j++) {
                System.out.print(' ');
            }
            for (int j = (noRows-i+1); j <= noRows; j++) {
                System.out.print('*');
            }
            System.out.println();  // go to next line
        }
    }

    public void exer6_4() {
        for (int i = 1; i <= noRows; i++) {
            for (int j = 1; j <= (noRows-i); j++) {
                System.out.print(' ');
            }
            for (int j = 1; j < i*2; j+=1) {
                System.out.print('*');
            }
            System.out.println();  // go to next line
        }
    }
    public void exer6_5() {
        for (int i = 1; i <= noRows; i++) {
            for (int j = 1; j <= (noRows-i); j++) {
                System.out.print(' ');
            }
            for (int j = 1; j < i*2; j+=1) {
                System.out.print(i);
            }
            System.out.println();  // go to next line
        }
    }

    public void exer6_6() {
        for (int i = 1; i <= noRows; i++) {
            for (int j = 1; j <= (noRows-i); j++) { //   print leading spaces
                System.out.print(' ');
            }

            for (int k = i; k >= 1; k--)  // print down to 1
                    System.out.print(k);
            for (int k = 2; k <= i; k++)  // print up from 2
                    System.out.print(k);
            System.out.println();  // go to next line
            }

        }
        public void exer7(int numRows, int numCols) {
            for (int i = 1; i < noRows; i++) {
                for (int j = 1; j <= numCols; j++)
                {
                    System.out.print('*');
                }
                System.out.println();  // go to next line
            }

        }

    }