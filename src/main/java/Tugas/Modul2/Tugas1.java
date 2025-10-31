package Tugas.Modul2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Hospital Management System with improved architecture, encapsulation, and functionality
 */

// Enum for doctor specializations
enum Specialization {
    CARDIOLOGY(),
    NEUROLOGY(),
    PEDIATRICS(),
    SURGERY(),
    ORTHOPEDICS(),
    DERMATOLOGY(), CARDIAC_SURGERY();

    private String displayName = "";

    Specialization() {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Specialization fromString(String text) {
        for (Specialization spec : Specialization.values()) {
            if (spec.displayName.equalsIgnoreCase(text)) {
                return spec;
            }
        }
        throw new IllegalArgumentException("Unknown specialization: " + text);
    }
}

// Interface for printable entities
interface Displayable {
    void displayDetails();
}

// Base class for all persons in the hospital
abstract class Person implements Displayable {
    protected String name;
    protected int id;

    protected Person(String name, int id) {
        setName(name);
        this.id = id;
    }

    // Getters and setters with validation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

// Refactored Doctor class
class Doctor extends Person {
    private static final double BONUS_RATE = 0.10; // Fixed: 10% instead of 98%
    private double salary;
    private Specialization specialization;
    private List<Patient> assignedPatients;

    public Doctor(String name, int id, double salary, Specialization specialization) {
        super(name, id);
        setSalary(salary);
        this.specialization = specialization;
        this.assignedPatients = new ArrayList<>();
    }

    public Doctor(String name, int id, double salary, String specialization) {
        this(name, id, salary, Specialization.fromString(specialization));
    }

    public void applyBonus() {
        double bonus = salary * BONUS_RATE;
        salary += bonus;
        System.out.printf("💰 Bonus applied! New Salary: $%,.2f%n", salary);
    }

    @Override
    public void displayDetails() {
        System.out.println("=== DOCTOR DETAILS ===");
        System.out.println("👨‍⚕️  ID: " + id);
        System.out.println("📛 Name: " + name);
        System.out.println("🎯 Specialization: " + specialization.getDisplayName());
        System.out.printf("💵 Salary: $%,.2f%n", salary);
        System.out.println("👥 Assigned Patients: " + assignedPatients.size());
        System.out.println("======================");
    }

    public void updateSpecialization(Specialization newSpecialization) {
        this.specialization = newSpecialization;
        System.out.println("🔄 Specialization updated to: " + newSpecialization.getDisplayName());
    }

    public void assignPatient(Patient patient) {
        if (patient != null && !assignedPatients.contains(patient)) {
            assignedPatients.add(patient);
            System.out.println("✅ Patient " + patient.getName() + " assigned to Dr. " + name);
        }
    }

    // Getter and Setter methods
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public List<Patient> getAssignedPatients() {
        return new ArrayList<>(assignedPatients);
    }
}

// Refactored Patient class
class Patient extends Person {
    private int recordNumber;
    private Doctor assignedDoctor;
    private String disease;
    private String admissionDate;

    public Patient(String name, int recordNumber, Doctor assignedDoctor, String disease) {
        super(name, recordNumber);
        this.recordNumber = recordNumber;
        setAssignedDoctor(assignedDoctor);
        setDisease(disease);
        this.admissionDate = LocalDate.now().toString();

        // Auto-assign to doctor
        if (assignedDoctor != null) {
            assignedDoctor.assignPatient(this);
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("=== PATIENT DETAILS ===");
        System.out.println("👤 Name: " + name);
        System.out.println("📋 Record Number: " + recordNumber);
        System.out.println("🤒 Disease: " + disease);
        System.out.println("📅 Admission Date: " + admissionDate);
        System.out.println("👨‍⚕️  Doctor in Charge: " +
                (assignedDoctor != null ? assignedDoctor.getName() : "None"));
        System.out.println("=======================");
    }

    public void updateDisease(String newDisease) {
        setDisease(newDisease);
        System.out.println("🔄 Disease updated to: " + newDisease);
    }

    public void transferDoctor(Doctor newDoctor) {
        this.assignedDoctor = newDoctor;
        if (newDoctor != null) {
            newDoctor.assignPatient(this);
        }
        System.out.println("🔄 Patient transferred to Dr. " +
                (newDoctor != null ? newDoctor.getName() : "None"));
    }

    // Getter and Setter methods
    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    public Doctor getAssignedDoctor() {
        return assignedDoctor;
    }

    public void setAssignedDoctor(Doctor assignedDoctor) {
        this.assignedDoctor = assignedDoctor;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = Objects.requireNonNull(disease, "Disease cannot be null");
        if (disease.trim().isEmpty()) {
            throw new IllegalArgumentException("Disease cannot be empty");
        }
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }
}

// Refactored Hospital class
class Hospital implements Displayable {
    private String hospitalName;
    private String address;
    private String phoneNumber;
    private List<Patient> patients;
    private List<Doctor> doctors;

    public Hospital(String hospitalName, String address) {
        setHospitalName(hospitalName);
        setAddress(address);
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.phoneNumber = "Not specified";
    }

    public Hospital(String hospitalName, String address, String phoneNumber) {
        this(hospitalName, address);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void displayDetails() {
        System.out.println("🏥 === HOSPITAL INFORMATION ===");
        System.out.println("🏢 Name: " + hospitalName);
        System.out.println("📍 Address: " + address);
        System.out.println("📞 Phone: " + phoneNumber);
        System.out.println("👨‍⚕️  Doctors: " + doctors.size());
        System.out.println("👤 Patients: " + patients.size());
        System.out.println("===============================");
    }

    public void displayCompleteInfo() {
        displayDetails();

        if (!doctors.isEmpty()) {
            System.out.println("\n--- MEDICAL STAFF ---");
            doctors.forEach(Doctor::displayDetails);
        }

        if (!patients.isEmpty()) {
            System.out.println("\n--- CURRENT PATIENTS ---");
            patients.forEach(Patient::displayDetails);
        }
    }

    public void admitPatient(Patient patient) {
        if (patient != null && !patients.contains(patient)) {
            patients.add(patient);
            System.out.println("✅ Patient " + patient.getName() + " admitted to " + hospitalName);
        }
    }

    public void hireDoctor(Doctor doctor) {
        if (doctor != null && !doctors.contains(doctor)) {
            doctors.add(doctor);
            System.out.println("✅ Dr. " + doctor.getName() + " joined " + hospitalName);
        }
    }

    // Getter and Setter methods
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = Objects.requireNonNull(hospitalName, "Hospital name cannot be null");
        if (hospitalName.trim().isEmpty()) {
            throw new IllegalArgumentException("Hospital name cannot be empty");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = Objects.requireNonNull(address, "Address cannot be null");
        if (address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }
}

// Main application class
public class Tugas1 {
    public static void main(String[] args) {
        try {
            System.out.println("=== 🏥 HOSPITAL MANAGEMENT SYSTEM ===\n");

            // Create hospital
            Hospital cityGeneral = new Hospital("City General Hospital",
                    "123 Main Street",
                    "(555) 123-4567");

            // Create doctors
            Doctor cardiologist = new Doctor("Dr. Sarah Lee", 2001, 12000.0, Specialization.CARDIOLOGY);
            Doctor neurologist = new Doctor("Dr. James Wilson", 2002, 11000.0, "Neurology");

            // Create patients
            Patient patient1 = new Patient("Michael Brown", 555, cardiologist, "Heart Disease");
            Patient patient2 = new Patient("Emily Davis", 556, neurologist, "Migraine");

            // Hospital operations
            cityGeneral.hireDoctor(cardiologist);
            cityGeneral.hireDoctor(neurologist);
            cityGeneral.admitPatient(patient1);
            cityGeneral.admitPatient(patient2);

            // Display hospital information
            System.out.println("\n=== HOSPITAL OVERVIEW ===");
            cityGeneral.displayCompleteInfo();

            // Demonstrate operations
            System.out.println("\n=== HOSPITAL OPERATIONS ===");
            cardiologist.applyBonus();
            patient1.updateDisease("Hypertension");
            cardiologist.updateSpecialization(Specialization.CARDIAC_SURGERY);

            // Display updated information
            System.out.println("\n=== UPDATED INFORMATION ===");
            cardiologist.displayDetails();
            patient1.displayDetails();

            // Show final hospital status
            System.out.println("\n=== FINAL HOSPITAL STATUS ===");
            cityGeneral.displayDetails();

        } catch (IllegalArgumentException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}