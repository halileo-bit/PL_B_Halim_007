package Tugas.Modul2;

import java.util.ArrayList;
import java.util.List;

public class VisitorRepository {
    private final TicketManagerBefore ticketManagerBefore;

    public VisitorRepository(TicketManagerBefore ticketManagerBefore) {
        this.ticketManagerBefore = ticketManagerBefore;
    }

    public boolean addVisitor(String name, int age, String cosplayCharacter, String ticketType) {
        if (!ticketManagerBefore.getTicketPrices().containsKey(ticketType)) {
            System.out.println("Jenis tiket tidak valid!");
            return false;
        }

        if (age < 10) {
            System.out.println("Pengunjung harus berusia minimal 10 tahun!");
            return false;
        }

        Visitor visitor = new Visitor(name, age, cosplayCharacter, ticketType);
        ticketManagerBefore.getVisitors().add(visitor);
        System.out.println("Pengunjung " + name + " berhasil ditambahkan!");
        return true;
    }

    public void displayVisitors() {
        if (ticketManagerBefore.getVisitors().isEmpty()) {
            System.out.println("Belum ada pengunjung terdaftar.");
            return;
        }

        System.out.println("\nDaftar Pengunjung:");
        for (int i = 0; i < ticketManagerBefore.getVisitors().size(); i++) {
            Visitor visitor = ticketManagerBefore.getVisitors().get(i);
            System.out.println((i + 1) + ". Nama: " + visitor.getName() +
                    ", Usia: " + visitor.getAge() +
                    ", Karakter: " + visitor.getCosplayCharacter() +
                    ", Tiket: " + visitor.getTicketType().toUpperCase());
        }
    }

    public List<Visitor> findVisitorByName(String name) {
        List<Visitor> found = new ArrayList<Visitor>();
        for (Visitor visitor : ticketManagerBefore.getVisitors()) {
            if (visitor.getName().toLowerCase().contains(name.toLowerCase())) {
                found.add(visitor);
            }
        }
        return found;
    }
}