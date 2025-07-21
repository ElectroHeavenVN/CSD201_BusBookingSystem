/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedList;
import Entities.Customer;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class CustomerList {
    private final LinkedList list = new LinkedList();

    // 2.1 Load data from file
    public void loadFromFile(String filename) {
        list.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    String phone = parts[2].trim();
                    list.add(new Customer(code, name, phone));
                }
            }
            System.out.println("Loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
    }

    // 2.2 Input & add to end
    public void inputAndAdd(Scanner sc) {
        System.out.print("Enter customer code: ");
        String code = sc.nextLine().trim();

        System.out.print("Enter customer name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine().trim();

        list.add(new Customer(code, name, phone));
        System.out.println("Customer added.");
    }

    // 2.3 Display all customers
    public void display() {
        if (list.isEmpty()) {
            System.out.println("Customer list is empty.");
            return;
        }

        Node current = list.getHead();
        while (current != null) {
            Customer c = (Customer) current.getValue();
            System.out.printf("%-10s | %-20s | %-10s\n", c.getCode(), c.getCustomerName(), c.getPhone());
            current = current.getNext();
        }
    }

    // 2.4 Save to file
    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            Node current = list.getHead();
            while (current != null) {
                Customer c = (Customer) current.getValue();
                pw.println(c.getCode() + " | " + c.getCustomerName() + " | " + c.getPhone());
                current = current.getNext();
            }
            System.out.println("Saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }

    // 2.5 Search by ccode
    public Customer searchByCode(String ccode) {
        Node current = list.getHead();
        while (current != null) {
            Customer c = (Customer) current.getValue();
            if (c.getCode().equalsIgnoreCase(ccode)) {
                return c;
            }
            current = current.getNext();
        }
        return null;
    }

    // 2.6 Delete by ccode
    public void deleteByCode(String ccode) {
        Node current = list.getHead();
        while (current != null) {
            Customer c = (Customer) current.getValue();
            if (c.getCode().equalsIgnoreCase(ccode)) {
                list.remove(c);
                System.out.println("Customer deleted.");
                return;
            }
            current = current.getNext();
        }
        System.out.println("Customer not found.");
    }   
}
