import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Doctor {
    String name;
    int availableHours;
    int originalHours;

    public Doctor(String name, int availableHours) {
        this.name = name;
        this.availableHours = availableHours;
        this.originalHours = availableHours;
    }
}

class Patient {
    String name;
    int requiredTime;

    public Patient(String name, int requiredTime) {
        this.name = name;
        this.requiredTime = requiredTime;
    }
}

public class Scheduler extends JPanel {
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<List<Patient>> doctorSchedules;

    public Scheduler(List<Doctor> doctors, List<Patient> patients) {
        this.doctors = doctors;
        this.patients = patients;
        this.doctorSchedules = new ArrayList<>();
        for (int i = 0; i < doctors.size(); i++) {
            doctorSchedules.add(new ArrayList<>());
        }
    }

    public static List<Doctor> loadDoctors(String filename) throws IOException {
        List<Doctor> doctors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                doctors.add(new Doctor(parts[0].trim(), Integer.parseInt(parts[1].trim())));
            }
        }
        return doctors;
    }

    public static List<Patient> loadPatients(String filename) throws IOException {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                patients.add(new Patient(parts[0].trim(), Integer.parseInt(parts[1].trim())));
            }
        }
        return patients;
    }

    public boolean schedulePatients() {
        return backtrack(0);
    }

    private boolean backtrack(int patientIndex) {
        if (patientIndex == patients.size()) {
            return true;
        }
        Patient currentPatient = patients.get(patientIndex);
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            if (doctor.availableHours >= currentPatient.requiredTime) {
                doctor.availableHours -= currentPatient.requiredTime;
                doctorSchedules.get(i).add(currentPatient);
                if (backtrack(patientIndex + 1)) {
                    return true;
                }
                doctor.availableHours += currentPatient.requiredTime;
                doctorSchedules.get(i).remove(doctorSchedules.get(i).size() - 1);
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
        int doctorX = 100;
        int patientX = 350;
        int startY = 50;
        int boxHeight = 50;
        int boxWidth = 150;
        int doctorGap = 40;
        int patientGap = 90;
    
        //colors
        Color doctorColor = new Color(135, 206, 250);  //light blue for doctors
        Color patientColor = new Color(255, 182, 193);  //light pink for patients
        Color lineColor = new Color(70, 130, 180);      //steel blue for connecting lines
    
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            int doctorY = startY + i * (boxHeight + doctorGap);
    
            //draw rounded rectangle for doctor
            g2.setColor(doctorColor);
            g2.fillRoundRect(doctorX, doctorY, boxWidth, boxHeight, 20, 20);
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(doctorX, doctorY, boxWidth, boxHeight, 20, 20);
    
            //doctor text
            g2.drawString(doctor.name + " (" + doctor.originalHours + " hrs)", doctorX + 10, doctorY + 20);
            g2.drawString("Scheduled: " + (doctor.originalHours - doctor.availableHours) + " hrs", doctorX + 10, doctorY + 40);
    
            //set starting Y position for patients assigned to this doctor
            int patientY = doctorY + boxHeight + patientGap;
    
            for (Patient patient : doctorSchedules.get(i)) {
                //draw rounded rectangle for patient
                g2.setColor(patientColor);
                g2.fillRoundRect(patientX, patientY, boxWidth, boxHeight, 20, 20);
                g2.setColor(Color.BLACK);
                g2.drawRoundRect(patientX, patientY, boxWidth, boxHeight, 20, 20);
    
                //patient text
                g2.drawString(patient.name + " (" + patient.requiredTime + " hrs)", patientX + 10, patientY + 20);
    
                //draw line connecting doctor to patient
                g2.setColor(lineColor);
                g2.setStroke(new BasicStroke(2));
                g2.draw(new Line2D.Double(doctorX + boxWidth, doctorY + boxHeight / 2, patientX, patientY + boxHeight / 2));
    
                //increase Y position for the next patient to ensure no overlap
                patientY += boxHeight + patientGap;
            }
        }
    }


    public static void main(String[] args) {
        try {
            List<Doctor> doctors = loadDoctors("doctors.txt");
            List<Patient> patients = loadPatients("patients.txt");
            Scheduler scheduler = new Scheduler(doctors, patients);

            if (scheduler.schedulePatients()) {
                System.out.println("All patients scheduled successfully.");
                
                JFrame frame = new JFrame("Doctor-Patient Scheduling");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 400);
                frame.add(scheduler);
                frame.setVisible(true);
            } else {
                System.out.println("Unable to schedule all patients with the available doctor hours.");
            }
        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
    }
}
