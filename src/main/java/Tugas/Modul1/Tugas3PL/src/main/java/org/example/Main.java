package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // input
        System.out.print("Masukkan nama mahasiswa: ");      //nama mahasiswa = nama (String)
        String nama = scanner.nextLine();

        System.out.print("Masukkan nilai ujian akhir: ");   //nilai ujian akhir = nilai (int)
        int nilai = scanner.nextInt();

        // process
        String status;
        if (nilai >= 60) {             // program membaca inputan dari user lalu mengecek inputan tersebut kalau nilai yg dimasukkan dikategorikan
            status = "Lulus";          // sebagai tidak lulus/lulus lewat penentuan if else nilai 60+/-
        } else {
            status = "Tidak Lulus";
        }

        // output
        System.out.println("Nama: " + nama);                   // program akan mengeluarkan hasil dari inputan dan penentuan tersebut
        System.out.println("Status Kelulusan: " + status);

        scanner.close();
    }
}

// deskripsi: ketika program tersebut berjalan maka ia meminta usernya buat memasukkan nama mahasiswa dan nilai ujian akhir, lalu setelah itu,
// program akan membaca inputan dari user dan menentukan nilai dari user tersebut lewat if else di apakah nilai nya lulus/tidak berdasarkan 60+/-.
// akhirnya, program akan mengeluarkan hasil inputan dan penentuan nilai.
