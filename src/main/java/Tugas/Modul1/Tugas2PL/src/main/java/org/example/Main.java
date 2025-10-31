package Tugas.Modul1.Tugas2PL.src.main.java.org.example;

import java.util.Scanner;

// Custom Exception
class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}

public class Main {
    // Method untuk validasi angka positif
    public static void validateNumber(int number) throws InvalidNumberException {
        if (number <= 0) {
            throw new InvalidNumberException("Error: Angka harus positif! Anda memasukkan: " + number);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Masukkan sebuah angka: ");
            int input = scanner.nextInt();

            // Validasi angka
            validateNumber(input);

            System.out.println("Angka valid: " + input);

        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Input tidak valid! Harus berupa angka.");
        } finally {
            scanner.close();
        }
    }
}
