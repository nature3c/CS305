public class Cat extends Pet {
    private int hairLength;

    public Cat(String name, String ownerName, String ownerEmail, String color, String gender, int hairLength) {
        super(name, ownerName, ownerEmail, color);
        this.hairLength = hairLength;
        setGender(gender);
    }

    //set gender
    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
        }
    }

    //method for printing cat details
    @Override
    public String toString() {
        return "CAT:\n" + getName() + " owned by " + getOwnerName() + "\nowner's email: " + getOwnerEmail() + 
               "\nPet Color: " + getColor() + "\nHair: " + hairLength;
    }
}
