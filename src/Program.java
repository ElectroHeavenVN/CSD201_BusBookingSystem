
import BSTree.BusTree;
import Entities.Bus;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author EHVN
 */
public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BusTree busTree = new BusTree();
        //...

        while (true) { 
            System.out.println("=================== MENU ===================");
            System.out.println("1. Bus List");
            System.out.println("2. Customer List");
            System.out.println("3. Booking List");
            System.out.println("0. Exit");
            System.out.print("\r\nEnter your choice (0-3): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        busMenu(scanner, busTree);
                        break;
                    case 2:
                        // customerMenu(scanner, ...);
                        break;
                    case 3:
                        // bookingMenu(scanner, ...);
                        break;
                    case 0:
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please choose 0-3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
            System.out.println();
        }
    }

    private static void busMenu(Scanner scanner, BusTree busTree) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\r\n=================== BUS MENU ===================");
            System.out.println("1. Load data from file");
            System.out.println("2. Insert bus");
            System.out.println("3. In-order traversal");
            System.out.println("4. Breadth-first traversal");
            System.out.println("5. In-order traversal to file");
            System.out.println("6. Search by code");
            System.out.println("7. Delete by code");
            System.out.println("8. Balance tree");
            System.out.println("9. Count number of buses");
            System.out.println("0. Back to main menu");
            System.out.print("\r\nEnter your choice (0-9): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter file path to load bus data: ");
                        String filePath = sc.nextLine();
                        if (busTree.loadFromFile(filePath)) {
                            System.out.println("Bus data loaded successfully.");
                        } else {
                            System.out.println("Failed to load bus data.");
                        }
                        break;
                    case 2:
                        Bus bus = Bus.read();
                        if (busTree.SearchByCode(bus.getCode()) != null) {
                            System.out.println("Bus with code " + bus.getCode() + " already exists.");
                        } else {
                            busTree.insert(bus);
                            System.out.println("Bus added successfully.");
                        }
                        break;
                    case 3:
                        System.out.println("In-order traversal of the bus tree:");
                        busTree.InOrderTraversal(n -> System.out.println(n.getValue()));
                        break;
                    case 4:
                        System.out.println("Breadth-first traversal of the bus tree:");
                        busTree.BreadthFirstTraversal(n -> System.out.println(n.getValue().toString()));
                        break;
                    case 5:
                        System.out.print("Enter file path to save bus data: ");
                        String savePath = sc.nextLine();
                        try {
                            File file = new File(savePath);
                            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                            busTree.InOrderTraversal(n -> {
                                try {
                                    writer.write(n.getValue().toString());
                                    writer.newLine();
                                } catch (Exception e) {
                                    System.out.println("Error writing to file: " + e.getMessage());
                                }
                            });
                            writer.close();
                        } catch (Exception e) {
                            System.out.println("Error saving bus data: " + e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.print("Enter bus code to search: ");
                        String code = sc.nextLine();
                        Bus foundBus = busTree.SearchByCode(code);
                        if (foundBus != null) {
                            System.out.println("Found bus: " + foundBus);
                        } else {
                            System.out.println("No bus found with code " + code);
                        }
                        break;
                    case 7:
                        System.out.print("Enter bus code to delete: ");
                        String deleteCode = sc.nextLine();
                        Bus bus2 = busTree.SearchByCode(deleteCode);
                        if (bus2 != null) {
                            busTree.Delete(bus2);
                            System.out.println("Bus with code " + deleteCode + " deleted successfully.");
                        } else {
                            System.out.println("No bus found with code " + deleteCode);
                        }
                        break;
                    case 8:
                        busTree.Balance();
                        System.out.println("Bus tree balanced successfully.");
                        break;
                    case 9:
                        int size = busTree.Size();
                        System.out.println("Total number of buses: " + size);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice. Please choose 0-9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
