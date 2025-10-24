package Tugas.Modul2;

import java.util.*;

enum TicketType {
    REGULAR("regular", 50000),
    VIP("vip", 100000),
    VVIP("vvip", 200000);

    private final String type;
    private final int price;
}

class Visitor {
    private String name;
    private int age;
    private String cosplayCharacter;
    private String ticketType;

    public Visitor(String name, int age, String cosplayCharacter, String ticketType) {
        this.name = name;
        this.age = age;
        this.cosplayCharacter = cosplayCharacter;
        this.ticketType = getTicketType(ticketType);
    }

    private static String getTicketType(String ticketType) {
        return ticketType;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCosplayCharacter() { return cosplayCharacter; }
    public String getTicketType() { return ticketType; }
}

public class TicketManagerBefore {
    private final VisitorRepository visitorRepository = new VisitorRepository(this);
    private List<Visitor> visitors;
    private Map<String, Integer> ticketPrices;

    public TicketManagerBefore() {
        visitors = new ArrayList<>();
        ticketPrices = new HashMap<>();
        ticketPrices.put("regular", 50000);
        ticketPrices.put("vip", 100000);
        ticketPrices.put("vvip", 200000);
    }

    public boolean addVisitor(String name, int age, String cosplayCharacter, String ticketType) {

        return visitorRepository.addVisitor(name, age, cosplayCharacter, ticketType);
    }

    public int calculateTotalRevenue() {
        int total = 0;
        for (Visitor visitor : visitors) {
            total += ticketPrices.get(visitor.getTicketType());
        }
        return total;
    }

    public void displayVisitors() {

        visitorRepository.displayVisitors();
    }

    public List<Visitor> findVisitorByName(String name) {
        return visitorRepository.findVisitorByName(name);
    }

    public static void main(String[] args) {
        TicketManagerBefore manager = new TicketManagerBefore();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MANAJEMEN TIKET EVENT COSPLAY ===");
            System.out.println("1. Tambah Pengunjung");
            System.out.println("2. Tampilkan Semua Pengunjung");
            System.out.println("3. Cari Pengunjung");
            System.out.println("4. Total Pendapatan");
            System.out.println("5. Keluar");

            System.out.print("Pilih menu (1-5): ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Nama pengunjung: ");
                String name = scanner.nextLine();

                System.out.print("Usia: ");
                int age = Integer.parseInt(scanner.nextLine());

                System.out.print("Karakter cosplay: ");
                String character = scanner.nextLine();

                System.out.print("Jenis tiket (regular/vip/vvip): ");
                String ticketType = scanner.nextLine().toLowerCase();

                manager.addVisitor(name, age, character, ticketType);

            } else if (choice.equals("2")) {
                manager.displayVisitors();

            } else if (choice.equals("3")) {
                System.out.print("Masukkan nama yang dicari: ");
                String name = scanner.nextLine();
                List<Visitor> results = manager.findVisitorByName(name);
                if (!results.isEmpty()) {
                    System.out.println("\nHasil pencarian:");
                    for (Visitor visitor : results) {
                        System.out.println("- " + visitor.getName() +
                                " (" + visitor.getCosplayCharacter() +
                                ") - " + visitor.getTicketType());
                    }
                } else {
                    System.out.println("Pengunjung tidak ditemukan.");
                }

            } else if (choice.equals("4")) {
                int revenue = manager.calculateTotalRevenue();
                System.out.println("Total pendapatan: Rp " + revenue);

            } else if (choice.equals("5")) {
                System.out.println("Terima kasih!");
                break;

            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }
}
