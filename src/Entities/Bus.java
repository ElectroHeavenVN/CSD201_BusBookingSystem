/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Scanner;

/**
 *
 * @author EHVN
 */
public class Bus {
    public String code;
    public String busName;
    public int seat;
    public int booked;
    public double departTime;
    public double arrivalTime;

    public Bus(String code, String busName, int seat, int booked, double departTime, double arrivalTime) {
        this.code = code;
        this.busName = busName;
        this.seat = seat;
        this.booked = booked;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public double getDepartTime() {
        return departTime;
    }

    public void setDepartTime(double departTime) {
        this.departTime = departTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return code + " | " + busName + " | " + seat + " | " + booked + " | " + departTime + " | " + arrivalTime;
    }

    // Additional methods

    public static Bus read()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bus code: ");
        String code = sc.nextLine();
        System.out.print("Enter bus name: ");
        String busName = sc.nextLine();
        int seat;
        do { 
            System.out.print("Enter number of seats: ");
            try {
                seat = sc.nextInt();
                sc.nextLine();
                if (seat > 0) 
                    break;
                System.out.println("Number of seats must be greater than 0. Please try again.");
            }
            catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
            }
        } while (true);
        int booked;
        do {
            try {
                System.out.print("Enter number of booked seats: ");
                booked = sc.nextInt();
                sc.nextLine();
                if (booked >= 0 && booked <= seat) 
                    break;
                System.out.println("Booked seats must be between 0 and " + seat + ". Please try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
            }
        } while (true);
        double departTime;
        do {
            System.out.print("Enter departure time: ");
            try {
                departTime = sc.nextDouble();
                sc.nextLine();
                if (departTime >= 0) 
                    break;
                System.out.println("Departure time must be non-negative. Please try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
            }
        } while (true);
        double arrivalTime;
        do {
            System.out.print("Enter arrival time: ");
            try {
                arrivalTime = sc.nextDouble();
                sc.nextLine();
                if (arrivalTime > departTime) 
                    break;
                System.out.println("Arrival time must be greater than departure time. Please try again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
            }
        } while (true);
        return new Bus(code, busName, seat, booked, departTime, arrivalTime);
    }
}
