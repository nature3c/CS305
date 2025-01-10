/**
 * Find if a string is Palindrome
 *
 * @author (Andrew Bae)
 * @version (8/29/24)
 */

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        String palindrome = args[0];
        
        System.out.println(calculate(palindrome));
        
        
    }
    
    public static boolean calculate(String palindrome) {
        if(palindrome.length() < 2) {
            return true;
        }
        
        if(palindrome.charAt(0) != palindrome.charAt(palindrome.length() - 1)) {
            return false;
        }
        
        palindrome = palindrome.substring(1, palindrome.length()-1);
        return calculate(palindrome);
    } 
}
