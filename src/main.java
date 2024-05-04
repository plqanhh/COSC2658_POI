import ADTs.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map2DKDTree kdTree = new Map2DKDTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAvailable Operations:");
            System.out.println("1. Add a new place");
            System.out.println("2. Edit an existing place");
            System.out.println("3. Search for places");
            System.out.println("4. Print all places");
            System.out.println("5. Exit");
            System.out.print("Select an operation (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Flush the scanner

            switch (choice) {
                case 1: // Add a new place
                    addNewPlace(scanner, kdTree);
                    break;
                case 2: // Edit an existing place
                    editPlace(scanner, kdTree);
                    break;
                case 3: // Search for places
                    searchPlaces(scanner, kdTree);
                    break;
                case 4: // Print all places
                    kdTree.print();
                    break;
                case 5: // Exit and save
                    System.out.println("Exiting and saving...");
                    kdTree.saveToFile();
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid operation.");
            }
        }
    }

    private static void addNewPlace(Scanner scanner, Map2DKDTree kdTree) {
        System.out.print("Enter X coordinate: ");
        int x = scanner.nextInt();
        System.out.print("Enter Y coordinate: ");
        int y = scanner.nextInt();
        scanner.nextLine(); // Flush the scanner
        System.out.print("Enter services (comma-separated, e.g., ATM, Cafe): ");
        String servicesInput = scanner.nextLine();
        ArrayList<String> services = new ArrayList<>();
        for (String service : servicesInput.split(",")) {
            services.add(service.trim());
        }
        Place newPlace = new Place(x, y, services);
        kdTree.insert(newPlace);
        System.out.println("New place added successfully.");
    }

    private static void editPlace(Scanner scanner, Map2DKDTree kdTree) {
        System.out.print("Enter X coordinate of the place to edit: ");
        int x = scanner.nextInt();
        System.out.print("Enter Y coordinate of the place to edit: ");
        int y = scanner.nextInt();
        scanner.nextLine(); // Flush the scanner
        Place place = kdTree.findPlaceByCoordinates(x, y);
        if (place == null) {
            System.out.println("No place found at the specified coordinates.");
            return;
        }
        System.out.println("Current services: " + place.servicesToString());
        System.out.print("Enter new services (comma-separated): ");
        ArrayList<String> newServices = new ArrayList<>();
        for (String service : scanner.nextLine().split(",")) {
            newServices.add(service.trim());
        }
        kdTree.edit(place, newServices);
        System.out.println("Place edited successfully.");
    }

    private static void searchPlaces(Scanner scanner, Map2DKDTree kdTree) {
        System.out.print("Enter the top-left corner X coordinate of the search rectangle: ");
        int x = scanner.nextInt();
        System.out.print("Enter the top-left corner Y coordinate of the search rectangle: ");
        int y = scanner.nextInt();
        System.out.print("Enter the width of the search rectangle: ");
        int width = scanner.nextInt();
        System.out.print("Enter the height of the search rectangle: ");
        int height = scanner.nextInt();
        Rectangle bounds = new Rectangle(x, y, x + width, y + height);
        ArrayList<Place> foundPlaces = kdTree.search(bounds);
        System.out.println("Found places within the specified rectangle:");
        if (foundPlaces.isEmpty()) {
            System.out.println("No places found.");
        } else {
            for (Place place : foundPlaces) {
                System.out.println(place);
            }
        }
    }
}
