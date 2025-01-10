import java.time.LocalDate;
import java.time.DateTimeException;

public abstract class Pet {
    private String name;
    private String ownerName;
    private String ownerEmail;
    private String color;
    private LocalDate boardStart;
    private LocalDate boardEnd;

    //constructor
    public Pet(String name, String ownerName, String ownerEmail, String color) {
        this.name = name;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.color = color;
    }

    //setters for BoardStart and BoardEnd
    public void setBoardStart(int year, int month, int day) throws IllegalDateException {
        this.boardStart = validateAndConvertDate(year, month, day);
    }

    public void setBoardEnd(int year, int month, int day) throws IllegalDateException {
        this.boardEnd = validateAndConvertDate(year, month, day);
    }

    //method to validate and convert date
    private LocalDate validateAndConvertDate(int year, int month, int day) throws IllegalDateException {
        if (year < 2000 || year > 2020) {
            throw new IllegalDateException("Year must be between 2000 and 2020.");
        }
        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new IllegalDateException("Invalid date: " + day + "/" + month + "/" + year);
        }
    }

    //getters
    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getColor() {
        return color;
    }

    public LocalDate getBoardStart() {
        return boardStart;
    }

    public LocalDate getBoardEnd() {
        return boardEnd;
    }

    //abstract method for subclasses
    public abstract String toString();
}
