
/**
 * Write a description of class Factorial here.
 *
 * @author (Andrew Bae)
 * @version (8/27)
 */
public class Factorial
{
    /**
     * Constructor for objects of class Factorial
     */
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        System.out.println(factorial(k));
    }
    
    public static int factorial(int n) {
        if(n==1) {
            return 1;
        } else {
            return (n + factorial(n-1));
        }
    }
}
