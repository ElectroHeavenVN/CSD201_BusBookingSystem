package LinkedList;

import BSTree.BusTree;
import Entities.Booking;
import Entities.Bus;
import java.util.Scanner;

/**
 * @author EHVN
 */
public class BookingList {
    private final LinkedList list = new LinkedList();

    public boolean inputAndAdd(Scanner sc, BusTree busTree, CustomerList customerList) {
        System.out.print("Enter bus code: ");
        String bcode = sc.nextLine().trim();
        System.out.print("Enter customer code: ");
        String ccode = sc.nextLine().trim();
        System.out.print("Enter number of seats to book: ");
        int seat;
        try {
            seat = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid seat number. Must be an integer.");
            return false;
        }
        Bus bus = busTree.SearchByCode(bcode);
        if (bus == null) {
            System.out.println("Bus with code " + bcode + " does not exist.");
            return false;
        }
        if (customerList.searchByCode(ccode) == null) {
            System.out.println("Customer with code " + ccode + " does not exist.");
            return false;
        }
        if (seat <= 0 || bus.getSeat() < bus.getBooked() + seat) {
            System.out.println("Invalid number of seats or not enough seats available.");
            return false;
        }
        Booking booking = new Booking(bcode, ccode, seat);
        list.add(booking);
        bus.setBooked(bus.getBooked() + seat);
        System.out.println("Booking added successfully.");
        return true;
    }

    public void display() {
        if (list.isEmpty()) {
            System.out.println("Booking list is empty.");
            return;
        }
        Node current = list.getHead();
        System.out.println("Booking list:");
        System.out.printf("%-10s | %-15s | %-5s\n", "Bus Code", "Customer Code", "Seats");
        System.out.println("-----------------------------------");
        while (current != null) {
            Booking b = (Booking) current.getValue();
            System.out.printf("%-10s | %-15s | %-5d\n", b.getBusCode(), b.getCustomerCode(), b.getSeat());
            current = current.getNext();
        }
    }

    public void sortByBcodeCcode() {
        if (list.isEmpty() || list.size() == 1) {
            return;
        }
        Node current = list.getHead();
        java.util.ArrayList<Booking> bookings = new java.util.ArrayList<>();
        while (current != null) {
            bookings.add((Booking) current.getValue());
            current = current.getNext();
        }
        bookings.sort((b1, b2) -> {
            int bcodeCompare = b1.getBusCode().compareTo(b2.getBusCode());
            if (bcodeCompare != 0) {
                return bcodeCompare;
            }
            return b1.getCustomerCode().compareTo(b2.getCustomerCode());
        });
        list.clear();
        for (Booking booking : bookings) {
            list.add(booking);
        }
    }
}