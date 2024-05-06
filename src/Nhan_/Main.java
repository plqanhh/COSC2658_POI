import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {

        // Map2D map = new Map2D();
        // map.add(new Place("4", "Place 4", new Position(4, 4), new String[] { "School", "Restaurant" }));
        // map.add(new Place("1", "Place 1", new Position(1, 1), new String[] { "Restaurant", "School" }));
        // map.add(new Place("2", "Place 2", new Position(2, 2), new String[] { "Park", "Mall" }));
        // map.add(new Place("3", "Place 3", new Position(3, 3), new String[] { "Restaurant", "Park" }));
        // map.add(new Place("5", "Place 5", new Position(5, 5), new String[] { "Mall", "Park" }));
        // map.add(new Place("6", "Place 6", new Position(6, 6), new String[] { "Restaurant", "School" }));
        // map.add(new Place("7", "Place 7", new Position(7, 7), new String[] { "Restaurant", "Mall" }));
        // map.add(new Place("8", "Place 8", new Position(8, 5), new String[] { "Service 15", "Service 16" }));
        // map.add(new Place("9", "Place 9", new Position(7, 5), new String[] { "Service 17", "Service 18" }));
        // map.add(new Place("10", "Place 10", new Position(6, 5), new String[] { "Service 19", "Service 20" }));
        // map.add(new Place("11", "Place 11", new Position(5, 5), new String[] { "Service 21", "Service 22" }));
        // map.add(new Place("12", "Place 12", new Position(4, 5), new String[] { "Service 23", "Service 24" }));
        // map.add(new Place("13", "Place 13", new Position(3, 5), new String[] { "Service 25", "Service 26" }));
        // map.add(new Place("14", "Place 14", new Position(2, 5), new String[] { "Service 27", "Service 28" }));
        
        // // Print tree
        // System.out.println("------Print Tree------");
        // map.printTree();

        // // Find node 
        // System.out.println("\n\n------Find Node------");
        // System.out.println(map.findNode(new Position(5, 5)).place.toString());

        // // Check the tree is balanced or not
        // System.out.println("\n\n------Check Balance------");
        // if(map.isTreeBalanced()) System.out.println("The tree is balanced");
        // else System.out.println("The tree is not balanced");

        // // Check the tree is BST or not
        // // Edit place
        // System.out.println("\n\n------Edit Place------");
        // map.editPlace(new Position(6, 6), new String[]{"Service 15", "Service 16"});
        // System.out.println(map.findNode(new Position(6, 6)).place.toString());

        // // Remove place
        // System.out.println("\n\n------Remove Place------");
        // map.remove(new Position(6, 6));
        // map.printTree();
        
        // // Check the tree is balanced or not
        // System.out.println("\n\n------Check Balance------");
        // if(map.isTreeBalanced()) System.out.println("The tree is balanced");
        // else System.out.println("The tree is not balanced");

        // // Search Function
        // System.out.println("\n\n------Find Place------");
        // ArrayQueue<Place> places = map.search(new Position(5, 5), 10, 10, "Restaurant");
        // System.out.println("Places found: " + places.size());
        
        // while (!places.isEmpty()) {
        //     System.out.println(places.peekFront());
        //     places.deQueue();
        // }

        // String file_name = "C:\\Users\\ASUS\\Desktop\\DSA_github\\COSC2658_Project1\\src\\Nhan_\\point.txt";
        // PlaceManager.loadPlacesFromFile(map, file_name);

        // Print tree
        // System.out.println("------Print Tree------");
        // map.printTree();

        // // Initialize the 10x10 matrix
        // String[][] matrix = new String[10][10];
        // for (int i = 0; i < 10; i++) {
        //     Arrays.fill(matrix[i], "-");
        // }

        // // Mark the places on the matrix
        // List<Place> list = map.getPlacesList();
        // for (int i = 0; i < list.size(); i++) {
        //     Place place = list.get(i);
        //     Position pos = place.getPosition();
        //     if (pos.getX() >= 0 && pos.getX() < 10 && pos.getY() >= 0 && pos.getY() < 10) {
        //         matrix[pos.getX()][pos.getY()] = "X";
        //     }
        // }

        // // // Print the matrix
        // // System.out.println("Matrix representation of the map:");
        // // printMatrix(matrix);

        // // Mark the boundary of the places on the matrix
        // // Define a search boundary and mark it
        // Position center = new Position(4, 4);
        // int width = 10;
        // int height = 10;
        // markBoundary(matrix, center, width, height);

        // // Print the matrix with the boundary
        // System.out.println("Matrix with search boundary:");
        // printMatrix(matrix);


        Map2D map = new Map2D();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAvailable Operations:");
            System.out.println("1. Add a new place");
            System.out.println("2. Edit an existing place");
            System.out.println("3. Search for places");
            System.out.println("4. Print all places");
            System.out.println("5. Remove a place");
            System.out.println("6. Exit");
            System.out.print("Select an operation (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Flush the scanner

            switch (choice) {
                case 1:
                    addNewPlace(scanner, map);
                    break;
                case 2:
                    editPlace(scanner, map);
                    break;
                case 3:
                    searchPlaces(scanner, map);
                    break;
                // case 4:
                //     map.print();
                //     break;
                case 5:
                    removePlace(scanner, map);
                    break;
                case 6:
                    System.out.println("Exiting and saving...");
                    map.saveToFile();
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid operation.");
            }
        }
    }
    private static void addNewPlace(Scanner scanner, Map2D map) {
        System.out.print("Enter X coordinate: ");
        int x = scanner.nextInt();
        System.out.print("Enter Y coordinate: ");
        int y = scanner.nextInt();
        scanner.nextLine(); // Flush the scanner
        System.out.print("Enter services (comma-separated, e.g., ATM, Cafe): ");
        String servicesInput = scanner.nextLine().toUpperCase();
        String[] services = new String[] {};
        if (!servicesInput.isEmpty()) {
            services = servicesInput.split(",");
        }
        Position position = new Position(x, y);
        Place newPlace = new Place(position, services);
        map.add(newPlace);
        System.out.println("New place added successfully.");
    }

    private static void editPlace(Scanner scanner, Map2D map) {
        System.out.print("Enter X coordinate of the place to edit: ");
        int x = scanner.nextInt();
        System.out.print("Enter Y coordinate of the place to edit: ");
        int y = scanner.nextInt();
        scanner.nextLine(); // Flush the scanner
        Position curPosition = new Position(x, y);
        Place place = map.findNode(curPosition).place;
        if (place == null) {
            System.out.println("No place found at the specified coordinates.");
            return;
        }
        System.out.println("Current services: " + place.getServices().toString());
        System.out.print("Enter new services (comma-separated): ");
        String[] newServices = new String[] {};

        String[] inputServices = scanner.nextLine().toUpperCase().split(",");

        for(String service : inputServices){
            newServices = Arrays.copyOf(newServices, newServices.length + 1);
            newServices[newServices.length - 1] = service;
        }
        map.editPlace(curPosition, newServices);
        System.out.println("Place edited successfully.");
    }

    private static void searchPlaces(Scanner scanner, Map2D map) {
        System.out.print("Enter the current X coordinate of your searching place: ");
        int x = scanner.nextInt();
        System.out.print("Enter the current Y coordinate of the searching place: ");
        int y = scanner.nextInt();
        System.out.print("Enter the width of the search rectangle boundary: ");
        int width = scanner.nextInt();
        System.out.print("Enter the height of the search rectangle boundary: ");
        int height = scanner.nextInt();
        System.out.println("Enter the service type to search for (or press Enter to search for all services): ");
        String service = scanner.nextLine(); // Flush the scanner

        if(service.isEmpty() || !ServiceType.isValid(service)){
            System.out.println("Invalid service type. Please enter a valid service type.");
            return;
        }

        // Define a search boundary and search for places
        ArrayQueue<Place> places = map.search(new Position(x, y), width, height, scanner.nextLine());
        System.out.println("Places found: " + places.size());
        System.out.println("Found places within the specified rectangle:");
        while(!places.isEmpty()){
            System.out.println(places.peekFront());
            places.deQueue();
        }
    }
    
    private static void removePlace(Scanner scanner, Map2D map) {
        System.out.print("Enter X coordinate of the place to remove: ");
        int x = scanner.nextInt();
        System.out.print("Enter Y coordinate of the place to remove: ");
        int y = scanner.nextInt();
        scanner.nextLine(); // Flush the scanner
        Position curPosition = new Position(x, y);

        // Call to remove the place
        if (map.remove(curPosition)){
            System.out.println("Place removed successfully.");
        } else {
            System.out.println("No place found at the specified coordinates or failed to remove.");
        }
    }
}
    // private static void printMatrix(String[][] matrix) {
    //     for (String[] row : matrix) {
    //         for (String cell : row) {
    //             System.out.print(cell + " ");
    //         }
    //         System.out.println();
    //     }
    // }

    // private static void markBoundary(String[][] matrix, Position center, int width, int height) {
    //     int startX = Math.max(center.getX() - width / 2, 0);
    //     int startY = Math.max(center.getY() - height / 2, 0);
    //     int endX = Math.min(center.getX() + width / 2, matrix.length - 1);
    //     int endY = Math.min(center.getY() + height / 2, matrix[0].length - 1);
    
    //     // Mark the boundary with "%"
    //     for (int x = startX; x <= endX; x++) {
    //         if (x < matrix.length) {
    //             if (startY < matrix[0].length) matrix[x][startY] = "%"; // Top boundary
    //             if (endY < matrix[0].length) matrix[x][endY] = "%"; // Bottom boundary
    //         }
    //     }
    //     for (int y = startY; y <= endY; y++) {
    //         if (y < matrix[0].length) {
    //             if (startX < matrix.length) matrix[startX][y] = "%"; // Left boundary
    //             if (endX < matrix.length) matrix[endX][y] = "%"; // Right boundary
    //         }
    //     }
    // }
    
// }

