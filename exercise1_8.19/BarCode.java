import java.util.HashMap;
import java.util.Scanner;

public class BarCode
{   
    HashMap<String, String> codes; //global variables
    HashMap<String, String> rCodes;
    
    public BarCode() {
        codes = new HashMap<String, String>(); //list of all the numbers and corresponding patterns
        codes.put("0", "||:::");
        codes.put("1", ":::||");
        codes.put("2", "::|:|");
        codes.put("3", "::||:");
        codes.put("4", ":|::|");
        codes.put("5", ":|:|:");
        codes.put("6", ":||::");
        codes.put("7", "|:::|");
        codes.put("8", "|::|:");
        codes.put("9", "|:|::");

        rCodes = new HashMap<String, String>(); //reverse of the list above
        rCodes.put("||:::", "0");
        rCodes.put(":::||", "1");
        rCodes.put("::|:|", "2");
        rCodes.put("::||:", "3");
        rCodes.put(":|::|", "4");
        rCodes.put(":|:|:", "5");
        rCodes.put(":||::", "6");
        rCodes.put("|:::|", "7");
        rCodes.put("|::|:", "8");
        rCodes.put("|:|::", "9");
    }
    
    public String encode(String number) {
        StringBuilder barcode = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            String digit = number.substring(i, i + 1); //substring of every letter 
            barcode.append(codes.get(digit)); //append each barcode to the final result
        }
        return barcode.toString();
    }
    
    public String decode(String barcode) {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < barcode.length(); i += 5) { //cycle through every 5 characters 
            String pattern = barcode.substring(i, i + 5); //look at the 5 characters in the pattern
            number.append(rCodes.get(pattern)); //append each number to the number string 
        }
        return number.toString();
    }
    
    public String singleDigit(String digit) {
        return codes.get(digit); //return the digits pattern equivalent
    }
    
    public String singlePattern(String pattern) {
        return rCodes.get(pattern); //return the patterns digit equivalent
    }
    
    public static String cierto(String input) { //y or no checker at the end
        if (input.equals("y")) { //if it is given y
            return "Great!";
        } else if (input.equals("n")) { //if it is given n 
            return "No it's not.";
        }
        return null; //need a return statement
    }
    
    public static void main(String args[]) {
        BarCode barCode = new BarCode(); //declares barCode of type BarCode and creates a new instance of the BarCode class
        Scanner scanner = new Scanner(System.in); //scanner for user inputs
        
        System.out.println("Choose an option:"); //choose an option
        System.out.println("1. Convert a single digit to barcode");
        System.out.println("2. Convert a barcode to a single digit");
        System.out.println("3. Encode a 5-digit zipcode to barcode");
        System.out.println("4. Decode a barcode to a 5-digit zipcode");
        System.out.println("5. Zipcode that you would like to live in");
        
        int option = scanner.nextInt(); //take the number of the option
        scanner.nextLine(); //clear the "\n" from the input
        
        switch(option) {
            case 1: //changing a digit to pattern
                System.out.print("Enter a digit (0-9) >>> ");
                String digit = scanner.nextLine();
                if (digit.length() > 1) {
                    System.out.println("That's not a single digit.");
                    break;
                }
                String encodedDigit = barCode.singleDigit(digit); //var encodedDigit is equal to the pattern of the digit
                System.out.println("Barcode pattern >>> " + encodedDigit); //prints result
                break;
                
            case 2:
                System.out.print("Enter a barcode >>> ");
                String pattern = scanner.nextLine();
                if (pattern.length() != 5) {
                    System.out.println("That's not 5 characters.");
                    break;
                }
                String decodedPattern = barCode.singlePattern(pattern); //decodedPattern is the digit corresponding to the pattern
                System.out.println("Digit >>> " + decodedPattern);
                break;
                
            case 3:
                System.out.print("Enter a 5-digit zipcode >>> ");
                String zipcode = scanner.nextLine();
                if (zipcode.length() != 5 || !zipcode.matches("\\d+")) { //check if the zipcode is 5 digits and numeric
                    //!zipcode.matches("\\d+") -> "\\d+" matches for digits 0-9 and + indicates that preceding element must appear one or more times
                    System.out.println("Invalid input. Please enter a 5-digit number.");
                } else {
                    //encode the 5 digit zipcode to the barcode pattern
                    StringBuilder barcode = new StringBuilder();
                    int sum = 0;

                    for (char c : zipcode.toCharArray()) { //convert the input to an array of characters
                        int num = Character.getNumericValue(c); //get the number
                        sum += num; //add the number to the sum
                        barcode.append(barCode.singleDigit(String.valueOf(num))); //converts the string value of num '3' -> "3" so that it can be referenced using a hashmap
                    }

                    int correctionDigit = (10 - (sum % 10)) % 10; //calculate correction digit

                    barcode.append(barCode.singleDigit(String.valueOf(correctionDigit))); //encode the correction digit and add to barcode

                    System.out.println("Encoded Barcode >>> " + "|" + barcode.toString() + "|"); //print the barcode with original 25 characters + 5 characters for the correction digit
                }
                break;
                
            case 4:
                System.out.print("Enter a barcode pattern >>> ");
                String fullBarcodeWithBars = scanner.nextLine();
                
                //validate that the input starts and ends with |
                if (!fullBarcodeWithBars.startsWith("|") || !fullBarcodeWithBars.endsWith("|")) { //good names dont need comments
                    System.out.println("Invalid format. The barcode should start and end with '|'.");
                    break;
                }
                
                //substring to remove the | at the beginning and end
                String fullBarcode = fullBarcodeWithBars.substring(1, fullBarcodeWithBars.length() - 1);
                
                if (fullBarcode.length() != 30) { //25 characters for zip and 5 for correction
                    System.out.println("The barcode must be 30 characters long, excluding the surrounding '|'.");
                } else {
                    String originalBarcode = fullBarcode.substring(0, 25);//decode the original zipcode of 25 characters
                    String correctionBarcode = fullBarcode.substring(25, 30); //correction digit of 5 characters
                    
                    String decodedZipcode = barCode.decode(originalBarcode);
                    String correctionDigit = barCode.decode(correctionBarcode);
                    
                    System.out.println("Decoded zipcode >>> " + decodedZipcode);
                    System.out.println("Correction digit >>> " + correctionDigit);
                }
                break;

            
            case 5: //same code as case 3 just different variable names
                System.out.print("Enter a 5-digit zipcode >>> ");
                String zipcode2 = scanner.nextLine();
                if (zipcode2.length() != 5 || !zipcode2.matches("\\d+")) { //check if the zipcode is 5 digits and numeric
                    //!zipcode.matches("\\d+") -> "\\d+" matches for digits 0-9 and + indicates that preceding element must appear one or more times
                    System.out.println("Invalid input. Please enter a 5-digit number.");
                } else {
                    //encode the 5 digit zipcode to the barcode pattern
                    StringBuilder barcode = new StringBuilder();
                    int sum = 0;

                    for (char c : zipcode2.toCharArray()) { //convert the input to an array of characters
                        int num = Character.getNumericValue(c); //get the number
                        sum += num; //add the number to the sum
                        barcode.append(barCode.singleDigit(String.valueOf(num))); //converts the string value of num '3' -> "3" so that it can be referenced using a hashmap
                    }

                    int correctionDigit = (10 - (sum % 10)) % 10; //calculate correction digit

                    barcode.append(barCode.singleDigit(String.valueOf(correctionDigit))); //encode the correction digit and add to barcode

                    System.out.println("Encoded Barcode >>> " + "|" + barcode.toString() + "|"); //print the barcode with original 25 characters + 5 characters for the correction digit
                }
                break;
            
            default:
                System.out.println("Invalid input.");
                break;
                
        }
        
        scanner.close();
    }
}
