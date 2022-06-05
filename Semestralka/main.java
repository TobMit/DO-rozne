import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class main {
    public static int n = 500;
    public static int r = 350;
    public static int K =10500;
    private static  int b_C_A[][] = new int[n][3];
    private static Stack<Integer> indexSameCValue = new Stack<>();

    public static void main(String[] args) throws FileNotFoundException {
        nacitajData();
        int plnyBatohHmotnost = spocitajHmotnostBatohu();
        int plnyBatohCena = spocitajFx();

        //súčasne riešenie
        int hodnotaFx = spocitajFx();
        // súčasná kapacita
        int az = spocitajHmotnostBatohu(); // kapacita batohu
        // počet prvkov v batohu
        int z = n;

        System.out.printf("PocetPoloziek v batohu pred heuristikou: %d\n", pocetPoloziekVBatohu());


        while (z >= r + 1) {
            if (indexSameCValue.isEmpty()){
                napltStack();
            }
            int index = indexSameCValue.pop();
            int newAz = az - b_C_A[index][2];
            if (newAz < K) {
                break;
            }
            az = newAz;
            b_C_A[index][0] = 0;
            z--;
        }

        ulozData();

        hodnotaFx = spocitajFx();
        System.out.printf("Plny batoh: Fx: %d\tHmotnost: %d\n", plnyBatohCena, plnyBatohHmotnost);
        System.out.printf("Heurestika: Fx: %d\tHmotnost: %d\n", hodnotaFx, spocitajHmotnostBatohu());
        System.out.printf("PocetPoloziek v batohu po heuristike: %d", pocetPoloziekVBatohu());

    }

    private static void ulozData() throws FileNotFoundException {
        PrintWriter zapisovac = new PrintWriter(new File("vystup.csv"));
        zapisovac.println("sep=,"); // aby excel správne rozližil udaje do stlpcov
        for (int i = 0; i < n; i++) {
            if  (b_C_A[i][0] == 1) {
                zapisovac.printf("%d,%d\n",b_C_A[i][1],b_C_A[i][2]);
            }
        }
        zapisovac.println("Vektor,C,A");
        for (int i = 0; i < n; i++) {
            zapisovac.printf("%d,%d,%d\n",b_C_A[i][0],b_C_A[i][1],b_C_A[i][2]);
        }
        zapisovac.close();
    }

    private static void napltStack() {
        int maxCvalue = najdiMaxC();
        for (int i = 0; i < n ; i++) {
            if (maxCvalue == b_C_A[i][1] ){
                indexSameCValue.push(i);
            }
        }
    }

    private static int najdiMaxC() {
        int returnValue = 0;
        for (int i = 0; i < n ; i++) {
            if (returnValue < b_C_A[i][1] && b_C_A[i][0] == 1){
                returnValue = b_C_A[i][1];
            }
        }
        return returnValue;
    }


    private static int pocetPoloziekVBatohu() {
        int returnValue = 0;
        for (int[] ints : b_C_A) {
            if (ints[0] == 1){
                returnValue++;
            }
        }
        return  returnValue;
    }


    private static int spocitajHmotnostBatohu() {
        int retValue = 0;
        for (int[] ints : b_C_A) {
            if (ints[0] == 1){
                retValue += ints[2];
            }
        }
        return retValue;
    }

    private static int spocitajFx() {
        int retValue = 0;
        for (int[] ints : b_C_A) {
            if (ints[0] == 1){
                retValue += ints[1];
            }
        }
        return retValue;
    }

    private static void nacitajData() throws FileNotFoundException {
        Scanner scannerC = new Scanner(new File("H4_c.txt"));
        Scanner scannerA =  new Scanner(new File("H4_a.txt"));
        int index  = 0;
        while (scannerC.hasNextInt() && scannerA.hasNext()) {
            b_C_A[index][0] = 1;
            b_C_A[index][1] = scannerC.nextInt();
            b_C_A[index][2] = scannerA.nextInt();
            index++;

        }
        scannerC.close();
        scannerA.close();
    }
}