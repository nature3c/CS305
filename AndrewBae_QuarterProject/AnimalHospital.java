import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimalHospital {
    private List<Pet> pets = new ArrayList<>();

    public AnimalHospital(String inputFile) {
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.equals("END")) break;

                try {
                    String[] data = line.split(" ");
                    String type = data[0];
                    String name = data[1];
                    String ownerName = data[2];
                    String ownerEmail = data[3];
                    String color = data[4];
                    String gender = data[5];

                    // Validate email
                    if (!isValidEmail(ownerEmail)) {
                        throw new IllegalEmailException("Invalid email address: " + ownerEmail);
                    }

                    Pet pet = null;

                    if (type.equals("CAT")) {
                        int hairLength = Integer.parseInt(data[6]);
                        pet = new Cat(name, ownerName, ownerEmail, color, gender, hairLength);
                    } else if (type.equals("DOG")) {
                        int size = Integer.parseInt(data[6]);
                        pet = new Dog(name, ownerName, ownerEmail, color, gender, size);
                    } else if (type.equals("BIRD")) {
                        boolean feathersClipped = Boolean.parseBoolean(data[6]);
                        pet = new Bird(name, ownerName, ownerEmail, color, gender, feathersClipped);
                    } else {
                        System.out.println("Unknown pet type: " + type);
                        continue; //skip to the next interation
                    }

                    //get boarding dates, start and end
                    int startMonth = Integer.parseInt(data[7].substring(0, 2));
                    int startDay = Integer.parseInt(data[7].substring(2, 4));
                    int startYear = Integer.parseInt(data[7].substring(4));
                    int endMonth = Integer.parseInt(data[8].substring(0, 2));
                    int endDay = Integer.parseInt(data[8].substring(2, 4));
                    int endYear = Integer.parseInt(data[8].substring(4));

                    //make sure the dates are valid
                    pet.setBoardStart(startYear, startMonth, startDay);
                    pet.setBoardEnd(endYear, endMonth, endDay);

                    pets.add(pet);
                    System.out.println(pet);
                    System.out.println("**************************");
                } catch (IllegalDateException e) { //invalid date
                    System.out.println("Error: " + e.getMessage());
                    System.out.println("**************************");
                } catch (IllegalEmailException e) { //invalid email
                    System.out.println("Error: " + e.getMessage());
                    System.out.println("**************************");
                } catch (NumberFormatException e) { //date format incorrect
                    System.out.println("Error: Invalid number format in input: " + e.getMessage());
                    System.out.println("**************************");
                } catch (ArrayIndexOutOfBoundsException e) { //incorrect data format / data is missing
                    System.out.println("Error: Incomplete data for pet entry: " + line);
                    System.out.println("**************************");
                } catch (Exception e) { //general error handling
                    System.out.println("Error: " + e.getMessage());
                    System.out.println("**************************");
                }
            }
        } catch (FileNotFoundException e) { //no file???
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) { //check that the email is valid and not like 142.---.---.---
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
