import java.util.Scanner;

public class PhoneNumberConverter {
    
    //2Darray to store letter to number mappings
    private static final char[][] keypadMapping = {
        {'A', 'B', 'C'}, //2
        {'D', 'E', 'F'}, //3
        {'G', 'H', 'I'}, //4
        {'J', 'K', 'L'}, //5
        {'M', 'N', 'O'}, //6
        {'P', 'Q', 'R', 'S'}, //7
        {'T', 'U', 'V'}, //8
        {'W', 'X', 'Y', 'Z'} //9
    };

    /**
     * The main method prompts the user for a 10-character phone number
     * and displays the converted number.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a 10-character phone number in the format XXX-XXX-XXXX: ");
        String phoneNumber = scanner.nextLine().toUpperCase(); //convert to uppercase for consistency
        
        //validate input format
        if (!phoneNumber.matches("[A-Z0-9]{3}-[A-Z0-9]{3}-[A-Z0-9]{4}")) {
            System.out.println("Invalid format. Please enter in the format XXX-XXX-XXXX.");
        } else {
            //convert phone number and display result
            String convertedNumber = convertPhoneNumber(phoneNumber);
            System.out.println("Converted phone number: " + convertedNumber);
        }
        
        scanner.close();
    }

    /**
     * Converts a phone number with alphabetic characters to its numeric equivalent.
     *
     * @param phoneNumber The phone number to be converted
     * @return The phone number with letters replaced by corresponding numbers
     */
    private static String convertPhoneNumber(String phoneNumber) {
        StringBuilder converted = new StringBuilder();
        
        //iterate through each character of the phone number
        for (char c : phoneNumber.toCharArray()) {
            if (Character.isLetter(c)) {
                converted.append(getNumberForLetter(c)); //convert letters to numbers
            } else {
                converted.append(c); //retain dashes and numeric characters as is
            }
        }
        
        return converted.toString();
    }

    /**
     * Gets the numeric equivalent for a given alphabetic character based on telephone keypad mapping.
     *
     * @param letter The alphabetic character to convert
     * @return The corresponding numeric value
     */
    private static int getNumberForLetter(char letter) {
        for (int i = 0; i < keypadMapping.length; i++) {
            for (char mappedChar : keypadMapping[i]) {
                if (mappedChar == letter) {
                    return i + 2; //numbers on keypad start from 2
                }
            }
        }
        return -1; //return -1 if the letter is not found (shouldn't happen in this case)
    }
}
