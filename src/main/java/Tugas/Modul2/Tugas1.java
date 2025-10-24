package Tugas.Modul2;

class Doctor {
    private static final double BONUS_RATE = 0.98;
    private String name;
    private int id;
    private double salary;
    private String specialization;

    // Construction
    public Doctor(String name, int id, double salary, String specialization) {
        this.setName(name);
        this.setId(id);
        this.setSalary(salary);
        this.setSpecialization(specialization);
    }

    public void applyBonus(){
        double bonus = getSalary() * BONUS_RATE;
        setSalary(getSalary() + bonus);
        System.out.println("Bonus applied ! New Salary : " + getSalary());
    }

    public void printDetails() {
        System.out.println("Doctor ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Specialization: " + getSpecialization());
        System.out.println("Salary: %" + getSalary());
    }

    // Update specialization
    public void updateSpecialization(String newSpecialization) {
        setSpecialization(newSpecialization);
        System.out.println("Specialization updated to: " + getSpecialization());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}

class Patient {
    public String name;
    public int recordNumber;
    public Doctor doctor;
    public String disease;

    // Construction
    public Patient(String name, int recordNumber, Doctor doctor, String disease) {
        this.name = name;
        this.recordNumber = recordNumber;
        this.doctor = doctor;
        this.disease = disease;
    }

    public void printPatientDetails() {
        System.out.println("Patient Name: " + name);
        System.out.println("Record Number: " + recordNumber);
        System.out.println("Disease: " + disease);
        System.out.println("Doctor in Charge: " + doctor.getName());
    }

    public void updateDisease(String newDisease) {
        disease = newDisease;
        System.out.println("Disease updated to: " + disease);
    }
}

class Hospital {
    public String hospitalName;
    public String address;
    public Patient patient;

    public Hospital(String hospitalName, String address, Patient patient) {
        this.hospitalName = hospitalName;
        this.address = address;
        this.patient = patient;
    }

    public void printHospitalDetails() {
        System.out.println("Hospital Name: " + hospitalName);
        System.out.println("Address: " + address);
        patient.printPatientDetails();
    }
}

public class MainApp {
    public static void main(String[] args) {
        Doctor doctor = new Doctor("Dr. Sarah Lee", 2001, 12000, "Cardiology");
        Patient patient = new Patient("Michael Brown", 555, doctor, "Heart Disease");

        Hospital hospital = new Hospital("City General Hospital", "123 Main Street", patient);
        hospital.printHospitalDetails();

        System.out.println();
        doctor.applyBonus();
        doctor.printDetails();
    }
}