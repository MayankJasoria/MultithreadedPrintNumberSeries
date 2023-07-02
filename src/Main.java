import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the last number in the series");
        int n = sc.nextInt();
        sc.close();

        PrintNumberSeries printer = new PrintNumberSeries(n);
        Thread t1 = new Thread(printer::printZero);
        Thread t2 = new Thread(printer::printOdd);
        Thread t3 = new Thread(printer::printEven);

        t3.start();
        t2.start();
        t1.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
