//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private int[] a = {2,3,4,5,6,7,8,9,10, 11};  // declares and initialises a with these values
    public static void main(String[] args) {
        //To run any method, just uncomment it and run the main method.
       Main menu = new Main();
       menu.exer1();
       //menu.exer2();
      //  menu.exer3();
      //  menu.exer4();
      //  menu.exer5();
      //  menu.exer6();
      //  menu.exer7();
    }

    public void exer1(){
        for (int i = 0; i<a.length; i++) {
            System.out.println(a[i]);
        }
    }
    public void exer2(){
        for (int i = a.length-1; i>=0; i--) {
            System.out.println(a[i]);
        }
    }

    public void exer3(){
        for (int i = 0; i <a.length; i+=2) {
            System.out.println(a[i]);
        }
    }

    public void exer4() {
        int total = 0;
        for (int i = 0; i <a.length; i++) {
           total += a[i];
        }
        System.out.println("Total of the array values is : " +total);
    }

    public void exer5() {
        int total = 0;
        for (int i = 0; i <4; i++) {
            total += a[i];
        }
        System.out.println("Total of the first four number in the array is : " + total);
    }

    public void exer6(){
        System.out.println("The following are the even numbers in the array : ");
        for (int i = 0; i <a.length; i++) {
            if (a[i] % 2 == 0)
                System.out.println(a[i]);
        }
    }

    public void exer7() {
        int totalEvens = 0;
        for (int i = 0; i <a.length; i++) {
            if (a[i] % 2 == 0)
               totalEvens += a[i];
        }
        System.out.println("Total of the even numbers in the array is : " + totalEvens);
    }
}