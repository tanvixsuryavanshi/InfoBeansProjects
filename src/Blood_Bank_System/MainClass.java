package Blood_Bank_System;

import java.io.*;
import java.util.*;

public class MainClass {

    static ArrayList<Donor> donors = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static final String FILE_NAME = "donors.txt";

    public static void main(String[] args) {

        loadFromFile();   // load old data

        int choice;

        do {
            System.out.println("\n---- Blood Bank (Single Bank System) ----");
            System.out.println("1. Add Donor");
            System.out.println("2. View All Donors (including deleted)");
            System.out.println("3. Search Available Donor by Blood Group");
            System.out.println("4. Delete Donor");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addDonor(); break;
                case 2: viewAll(); break;
                case 3: searchAvailable(); break;
                case 4: deleteDonor(); break;
                case 5: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice");
            }

        } while (choice != 5);
    }

    // ----------------- ADD -----------------

    static void addDonor() {

        System.out.print("Enter id: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter blood group: ");
        String bg = sc.nextLine();

        System.out.print("Enter city: ");
        String city = sc.nextLine();

        Donor d = new Donor(id, name, bg, city, "ADDED");
        donors.add(d);

        saveToFile();

        System.out.println("Donor added and saved to file");
    }

    // ----------------- VIEW -----------------

    static void viewAll() {

        if (donors.isEmpty()) {
            System.out.println("No donors");
            return;
        }

        for (Donor d : donors) {
            System.out.println(d);
        }
    }

    // ----------------- SEARCH (ONLY ADDED) -----------------

    static void searchAvailable() {

        sc.nextLine();
        System.out.print("Enter blood group: ");
        String bg = sc.nextLine();

        boolean found = false;

        for (Donor d : donors) {

            if (d.getBloodGroup().equalsIgnoreCase(bg)
                    && d.getStatus().equals("ADDED")) {

                System.out.println(d);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available donor for this blood group");
        }
    }

    // ----------------- DELETE (STATUS CHANGE) -----------------

    static void deleteDonor() {

        System.out.print("Enter donor id to delete: ");
        int id = sc.nextInt();

        boolean found = false;

        for (Donor d : donors) {

            if (d.getId() == id && d.getStatus().equals("ADDED")) {

                d.setStatus("DELETED");
                found = true;
                break;
            }
        }

        if (found) {
            saveToFile();
            System.out.println("Donor status changed to DELETED");
        } else {
            System.out.println("Donor not found or already deleted");
        }
    }

    // ----------------- FILE SAVE -----------------

    static void saveToFile() {

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Donor d : donors) {
                pw.println(d.toFileString());
            }

        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    // ----------------- FILE LOAD -----------------

    static void loadFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String bg = data[2];
                String city = data[3];
                String status = data[4];

                donors.add(new Donor(id, name, bg, city, status));
            }

        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }
}


