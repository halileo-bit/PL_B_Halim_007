package Tugas.Modul3;

import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    private String nama;
    private double harga;

    // Constructor untuk membuat objek Menu
    public Menu(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    // Method untuk mendapatkan nama menu
    public String getNama() {
        return nama;
    }

    // Method untuk mendapatkan harga menu
    public double getHarga() {
        return harga;
    }
}

class Pesanan {
    private Menu menu;
    private int jumlah;

    // Constructor untuk membuat objek Pesanan
    public Pesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    }

    // Method untuk mendapatkan item menu dari pesanan ini
    public Menu getMenu() {
        return menu;
    }

    // Method untuk mendapatkan jumlah yang dipesan
    public int getJumlah() {
        return jumlah;
    }

    // Method untuk menghitung subtotal (harga * jumlah)
    public double getSubtotal() {
        return menu.getHarga() * jumlah;
    }
}

public class Tugas1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Menu> daftarMenu = new ArrayList<>();
        ArrayList<Pesanan> daftarPesanan = new ArrayList<>();

        // Menambahkan beberapa item ke daftar menu
        daftarMenu.add(new Menu("Tahu Jeletot", 15000));
        daftarMenu.add(new Menu("Ayam Geprek", 12000));
        daftarMenu.add(new Menu("Semur Jengkol", 20000));
        daftarMenu.add(new Menu("Es Jeruk", 5000));

        System.out.println("Selamat Datang di Warung Makan Penacony!");
        System.out.println("=====================================");

        int pilihan;
        do {
            // 1. Menampilkan Menu Makanan
            System.out.println("\nDAFTAR MENU:");
            for (int i = 0; i < daftarMenu.size(); i++) {
                System.out.printf("%d. %-15s - Rp%,.0f\n", (i + 1), daftarMenu.get(i).getNama(), daftarMenu.get(i).getHarga());
            }
            System.out.println("0. Selesai Memesan dan Cetak Nota");

            // 2. Meminta Input dari Pengguna
            System.out.print("\n=> Silakan pilih menu (nomor): ");
            pilihan = scanner.nextInt();

            if (pilihan > 0 && pilihan <= daftarMenu.size()) {
                System.out.print("=> Masukkan jumlah pesanan: ");
                int jumlah = scanner.nextInt();

                // Membuat objek pesanan baru dan menambahkannya ke daftar pesanan
                Menu menuDipesan = daftarMenu.get(pilihan - 1);
                daftarPesanan.add(new Pesanan(menuDipesan, jumlah));
                System.out.println("--- Pesanan ditambahkan: " + menuDipesan.getNama() + " sejumlah " + jumlah);
            } else if (pilihan != 0) {
                System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }

        } while (pilihan != 0);

        // 3. Menampilkan Nota Pesanan
        System.out.println("\n\n================ NOTA PEMESANAN ================");
        if (daftarPesanan.isEmpty()) {
            System.out.println("Anda belum memesan apapun.");
        } else {
            double totalBayar = 0;
            System.out.println("No. | Nama Menu      | Harga Satuan | Jumlah | Subtotal");
            System.out.println("--------------------------------------------------");
            for (int i = 0; i < daftarPesanan.size(); i++) {
                Pesanan psn = daftarPesanan.get(i);
                System.out.printf("%-3d | %-14s | Rp%,10.0f | %-6d | Rp%,10.0f\n",
                        (i + 1),
                        psn.getMenu().getNama(),
                        psn.getMenu().getHarga(),
                        psn.getJumlah(),
                        psn.getSubtotal());
                totalBayar += psn.getSubtotal();
            }
            System.out.println("--------------------------------------------------");
            System.out.printf("TOTAL BAYAR: Rp%,.0f\n", totalBayar);
        }
        System.out.println("================================================");
        System.out.println("Terima kasih telah berkunjung!");

        scanner.close();
    }
}
