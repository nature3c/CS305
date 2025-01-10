public class Dog extends Pet {
    private int size;

    public Dog(String name, String ownerName, String ownerEmail, String color, String gender, int size) {
        super(name, ownerName, ownerEmail, color);
        this.size = size;
        setGender(gender);
    }

    //set gender
    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
        }
    }

    //method for printing dog details
    @Override
    public String toString() {
        return "DOG:\n" + getName() + " owned by " + getOwnerName() + "\nowner's email: " + getOwnerEmail() + 
               "\nPet Color: " + getColor() + "\nSize: " + size;
    }
}
