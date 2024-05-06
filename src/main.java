import ADTs.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map2DKDTree kdTree = new Map2DKDTree();

        // Adding nodes
        kdTree.insert(new Place(5, 10, new ArrayList<>()));
        kdTree.insert(new Place(15, 10, new ArrayList<>()));
        kdTree.insert(new Place(5, 20, new ArrayList<>()));
        kdTree.insert(new Place(15, 20, new ArrayList<>()));
        kdTree.insert(new Place(10, 15, new ArrayList<>()));

        System.out.println("Tree after insertions:");
        kdTree.print();

        // Removing a node
        kdTree.remove(new Place(10, 15, new ArrayList<>())); // Assuming remove by matching place coordinates
        System.out.println("Tree after removal:");
        kdTree.print();

        kdTree.print();
        System.out.println("Is the KD-Tree balanced? " + kdTree.isTreeBalanced());
    }
}

// import ADTs.*;

// import java.util.Scanner;

// public class Main {
//     public static void main(String[] args) {
//         Map2DKDTree kdTree = new Map2DKDTree();
//         Scanner scanner = new Scanner(System.in);

//         while (true) {
//             System.out.println("\nAvailable Operations:");
//             System.out.println("1. Add a new place");
//             System.out.println("2. Edit an existing place");
//             System.out.println("3. Search for places");
//             System.out.println("4. Print all places");
//             System.out.println("5. Remove a place");
//             System.out.println("6. Exit");
//             System.out.print("Select an operation (1-6): ");
//             int choice = scanner.nextInt();
//             scanner.nextLine(); // Flush the scanner

//             switch (choice) {
//                 case 1:
//                     addNewPlace(scanner, kdTree);
//                     break;
//                 case 2:
//                     editPlace(scanner, kdTree);
//                     break;
//                 case 3:
//                     searchPlaces(scanner, kdTree);
//                     break;
//                 case 4:
//                     kdTree.print();
//                     break;
//                 case 5:
//                     removePlace(scanner, kdTree);
//                     break;
//                 case 6:
//                     System.out.println("Exiting and saving...");
//                     kdTree.saveToFile();
//                     scanner.close();
//                     return;
//                 default:
//                     System.out.println("Invalid choice. Please select a valid operation.");
//             }
//         }
//     }

//     private static void addNewPlace(Scanner scanner, Map2DKDTree kdTree) {
//         System.out.print("Enter X coordinate: ");
//         int x = scanner.nextInt();
//         System.out.print("Enter Y coordinate: ");
//         int y = scanner.nextInt();
//         scanner.nextLine(); // Flush the scanner
//         System.out.print("Enter services (comma-separated, e.g., ATM, Cafe): ");
//         String servicesInput = scanner.nextLine().toUpperCase();
//         ArrayList<String> services = new ArrayList<>();
//         for (String service : servicesInput.split(",")) {
//             services.insertAt(services.size(), service.trim());
//         }
//         Place newPlace = new Place(x, y, services);
//         kdTree.insert(newPlace);
//         System.out.println("New place added successfully.");
//     }

//     private static void editPlace(Scanner scanner, Map2DKDTree kdTree) {
//         System.out.print("Enter X coordinate of the place to edit: ");
//         int x = scanner.nextInt();
//         System.out.print("Enter Y coordinate of the place to edit: ");
//         int y = scanner.nextInt();
//         scanner.nextLine(); // Flush the scanner
//         Place place = kdTree.findPlaceByCoordinates(x, y);
//         if (place == null) {
//             System.out.println("No place found at the specified coordinates.");
//             return;
//         }
//         System.out.println("Current services: " + place.getServices().toString());
//         System.out.print("Enter new services (comma-separated): ");
//         ArrayList<String> newServices = new ArrayList<>();
//         String[] inputServices = scanner.nextLine().toUpperCase().split(",");
//         for (String service : inputServices) {
//             if (ServiceType.isValid(service.trim())) { // Assuming ServiceType has a method to validate services
//                 newServices.insertAt(newServices.size(), service.trim());
//             } else {
//                 System.out.println("Error: Service '" + service + "' does not exist.");
//             }
//         }
//         kdTree.edit(place, newServices);
//         System.out.println("Place edited successfully.");
//     }

//     private static void searchPlaces(Scanner scanner, Map2DKDTree kdTree) {
//         System.out.print("Enter the top-left corner X coordinate of the search rectangle: ");
//         int x = scanner.nextInt();
//         System.out.print("Enter the top-left corner Y coordinate of the search rectangle: ");
//         int y = scanner.nextInt();
//         System.out.print("Enter the width of the search rectangle: ");
//         int width = scanner.nextInt();
//         System.out.print("Enter the height of the search rectangle: ");
//         int height = scanner.nextInt();
//         Rectangle bounds = new Rectangle(x, y, x + width, y + height);
//         ArrayList<Place> foundPlaces = kdTree.search(bounds, null); // Assuming search now takes a service type, pass
//                                                                     // null for all services
//         System.out.println("Found places within the specified rectangle:");
//         for (Place place : foundPlaces) {
//             System.out.println(place);
//         }
//     }
    
//     private static void removePlace(Scanner scanner, Map2DKDTree kdTree) {
//         System.out.print("Enter X coordinate of the place to remove: ");
//         int x = scanner.nextInt();
//         System.out.print("Enter Y coordinate of the place to remove: ");
//         int y = scanner.nextInt();
//         scanner.nextLine(); // Flush the scanner

//         // Call to remove the place
//         if (kdTree.remove(new Place(x, y, null))) {
//             System.out.println("Place removed successfully.");
//         } else {
//             System.out.println("No place found at the specified coordinates or failed to remove.");
//         }
//     }
// }
