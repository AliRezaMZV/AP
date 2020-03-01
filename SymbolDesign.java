import java.util.Scanner;
import java.lang.Math;

public class SymbolDesign{   

    public static void printLine(int lineCapacity, int n, int k){
        int leftStarsCount = Math.min((lineCapacity+1)/2, k);
        int rightStarsCount = Math.min(lineCapacity/2, k);
        int middleSpacesCount = Math.max(0, lineCapacity-2*k);
        int borderSpacesCount = ((2*n+1)-(leftStarsCount + rightStarsCount + middleSpacesCount))/2;
        
        for(int i = 0; i < borderSpacesCount; i++)
            System.out.print(" ");
        
        for(int i = 0; i < leftStarsCount; i++)
            System.out.print("*");
        
        for(int i = 0; i < middleSpacesCount; i++)
            System.out.print(" ");

        for(int i = 0; i < rightStarsCount; i++)
            System.out.print("*");
        
        System.out.println();

        return;
    }

    public static void printUpperHalf(int n, int k){ 
        for(int i = 0; i < n+1; i++){
            int lineCapacity = 2*i+1;

            printLine(lineCapacity, n, k);
        }
    }

    public static void printLowerHalf(int n, int k){ 
        for(int i = n-1; i >= 0; i--){
            int lineCapacity = 2*i+1;

            printLine(lineCapacity, n, k);
        }
    }

    public static void main(String args[]){
        int n, k;

        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();  
        k = scan.nextInt();
        scan.close();

        printUpperHalf(n, k);
        printLowerHalf(n, k);
    }
}