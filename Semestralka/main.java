import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.util.Scanner;
import java.util.TreeMap;

public class main {
    public static int n = 500;
    public static int r = 350;
    public static int K =10500;
    private static  int b_A_C[][] = new int[n][3];


    public static void main(String[] args) throws FileNotFoundException {
        nacitajData();

        for (int[] ints : b_A_C) {
            System.out.printf("%d %d %d\n", ints[0], ints[1], ints[2]);
        }

    }

    private static void nacitajData() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Semestralka/H4_c.txt"));
        Scanner scanner1 =  new Scanner(new File("Semestralka/H4_a.txt"));
        int index  = 0;
        while (scanner.hasNextInt() && scanner1.hasNext()) {
            b_A_C[index][0] = 0;
            b_A_C[index][1] = scanner.nextInt();
            b_A_C[index][2] = scanner1.nextInt();
            index++;

        }
        scanner.close();
        scanner1.close();
    }
}