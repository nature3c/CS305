public class Bird extends Pet {
    private boolean feathersClipped;

    public Bird(String name, String ownerName, String ownerEmail, String color, String gender, boolean feathersClipped) {
        super(name, ownerName, ownerEmail, color);
        this.feathersClipped = feathersClipped;
        setGender(gender);
    }

    //set gender
    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
        }
    }

    //method for printing bird details
    @Override
    public String toString() {
        return "BIRD:\n" + getName() + " owned by " + getOwnerName() + "\nowner's email: " + getOwnerEmail() + 
               "\nPet Color: " + getColor() + "\nFeathers clipped: " + feathersClipped;
    }
}
