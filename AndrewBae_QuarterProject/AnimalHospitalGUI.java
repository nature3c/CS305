import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AnimalHospitalGUI extends JFrame {
    private List<Pet> pets;
    
    private JTextField nameField;
    private JTextField ownerNameField;
    private JTextField ownerEmailField;
    private JTextField colorField;
    private JComboBox<String> petTypeComboBox;
    private JComboBox<String> genderComboBox;
    private JTextField specialField; //for cat hair length, dog size, or bird feather clipped
    private JTextField boardStartField;
    private JTextField boardEndField;
    private JTextArea outputArea;

    public AnimalHospitalGUI() {
        pets = new ArrayList<>();
        setTitle("Animal Hospital");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(9, 2));

        //labels and text fields for pet attributes
        inputPanel.add(new JLabel("Pet Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Owner Name:"));
        ownerNameField = new JTextField();
        inputPanel.add(ownerNameField);

        inputPanel.add(new JLabel("Owner Email:"));
        ownerEmailField = new JTextField();
        inputPanel.add(ownerEmailField);

        inputPanel.add(new JLabel("Pet Color:"));
        colorField = new JTextField();
        inputPanel.add(colorField);

        inputPanel.add(new JLabel("Pet Type:"));
        petTypeComboBox = new JComboBox<>(new String[] { "CAT", "DOG", "BIRD" });
        inputPanel.add(petTypeComboBox);

        inputPanel.add(new JLabel("Gender:"));
        genderComboBox = new JComboBox<>(new String[] { "male", "female", "spayed", "neutered" });
        inputPanel.add(genderComboBox);

        inputPanel.add(new JLabel("Special Field (Hair Length/Size/Feathers Clipped):"));
        specialField = new JTextField();
        inputPanel.add(specialField);

        inputPanel.add(new JLabel("Board Start Date (MMDDYYYY):"));
        boardStartField = new JTextField();
        inputPanel.add(boardStartField);

        inputPanel.add(new JLabel("Board End Date (MMDDYYYY):"));
        boardEndField = new JTextField();
        inputPanel.add(boardEndField);

        //input panel
        add(inputPanel, BorderLayout.CENTER);

        //output area to show the added pets
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);

        //button to add a pet
        JButton addButton = new JButton("Add Pet");
        addButton.addActionListener(new AddPetListener());
        add(addButton, BorderLayout.NORTH);
    }

    //listener for adding a pet
    private class AddPetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = nameField.getText();
                String ownerName = ownerNameField.getText();
                String ownerEmail = ownerEmailField.getText();
                String color = colorField.getText();
                String petType = (String) petTypeComboBox.getSelectedItem();
                String gender = (String) genderComboBox.getSelectedItem();
                String special = specialField.getText();
                String boardStart = boardStartField.getText();
                String boardEnd = boardEndField.getText();

                //validate email
                if (!isValidEmail(ownerEmail)) {
                    throw new IllegalEmailException("Invalid email format.");
                }

                Pet pet = null;

                //parse dates
                int startMonth = Integer.parseInt(boardStart.substring(0, 2));
                int startDay = Integer.parseInt(boardStart.substring(2, 4));
                int startYear = Integer.parseInt(boardStart.substring(4));
                int endMonth = Integer.parseInt(boardEnd.substring(0, 2));
                int endDay = Integer.parseInt(boardEnd.substring(2, 4));
                int endYear = Integer.parseInt(boardEnd.substring(4));

                //code for different pet types
                if (petType.equals("CAT")) {
                    int hairLength = Integer.parseInt(special);
                    pet = new Cat(name, ownerName, ownerEmail, color, gender, hairLength);
                } else if (petType.equals("DOG")) {
                    int size = Integer.parseInt(special);
                    pet = new Dog(name, ownerName, ownerEmail, color, gender, size);
                } else if (petType.equals("BIRD")) {
                    boolean feathersClipped = Boolean.parseBoolean(special);
                    pet = new Bird(name, ownerName, ownerEmail, color, gender, feathersClipped);
                }

                //set boarding dates
                pet.setBoardStart(startYear, startMonth, startDay);
                pet.setBoardEnd(endYear, endMonth, endDay);

                //add pet to list
                pets.add(pet);

                //display pet in output area
                outputArea.append(pet.toString() + "\n");
                outputArea.append("**************************\n");

            } catch (IllegalEmailException ex) {
                JOptionPane.showMessageDialog(AnimalHospitalGUI.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(AnimalHospitalGUI.this, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //email validation method (same as before)
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailRegex);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnimalHospitalGUI gui = new AnimalHospitalGUI();
            gui.setVisible(true);
        });
    }
}
