/**
 * Write a description of class Exponent here.
 *
 * @author (Andrew Bae)
 * @version (8/27/24)
 */

import java.util.Scanner;

public class Exponent {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Base: "); //base numer
        int base = scanner.nextInt();
        
        System.out.print("Power: "); //exponent
        int power = scanner.nextInt();
                
        System.out.println(base + " raised to " + power + " is " + calculate(base, power)); //print the result of the calculate method
        scanner.close();
    }
    
    public static int calculate(int base, int power) {
        if(power == 0) {
            return 1; //if power = 0 then return 1 to terminate recursion
        } else {
            return base * calculate(base, power - 1); //keep reducing power by 1 and multiply it to the base from before
        }
    }
}
