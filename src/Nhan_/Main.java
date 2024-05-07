import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
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

        Map2D map = new Map2D();
        readPlacesFromDataFile(map);
        System.out.println( map.getPlacesList().size());
    
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nAvailable Operations:");
            System.out.println("1. Add a new place");
            System.out.println("2. Edit an existing place");
            System.out.println("3. Search for places within a rectangle boundary");
            System.out.println("4. Remove a place");
            System.out.println("5. Exit");
            System.out.print("Select an operation (1-5): ");
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
                case 4:
                    removePlace(scanner, map);
                    break;
                case 5:
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
        System.out.print("Enter the current Y coordinate of your searching place: ");
        int y = scanner.nextInt();
        System.out.print("Enter the width of the search rectangle boundary  (Min: 10,000 and Max: 10,000,000,000): ");
        int width = scanner.nextInt();
        // while (width < 10000 || width > 1000000000) {
        //     System.out.println("Invalid width. Please enter a valid width: ");
        //     width = scanner.nextInt();
        // }
        System.out.print("Enter the height of the search rectangle boundary (Min: 10,000 and Max: 10,000,000,000): ");
        int height = scanner.nextInt();
        // while (height < 10000 || height > 1000000000) {
        //     System.out.println("Invalid height. Please enter a valid height: ");
        //     height = scanner.nextInt();
        // }
        scanner.nextLine(); // Flush the scanner
        System.out.print("Enter the service type to search for: ");
        String serviceType = scanner.nextLine();
        while(serviceType.isEmpty()){
            System.out.println("Invalid service type. Please enter a valid service type.");
            serviceType = scanner.nextLine();
        }
        if(!serviceType.equals(serviceType.toUpperCase())){
            serviceType = serviceType.toUpperCase();
        }
        while(!ServiceType.isValid(serviceType)){
            System.out.println("Invalid service type. Please enter a valid service type.");
            serviceType = scanner.nextLine();
        }
        // Define a search boundary and search for places
        ArrayQueue<Place> places = map.search(new Position(x, y), width, height, serviceType);
        ArrayList<Place> placesList = map.getPlacesList();
        int minX = x - width/2;
        int maxX = x + width/2;
        int minY = y - height/2;
        int maxY = y + height/2;
        for(int i = 0; i < placesList.size(); i++){
            if(placesList.get(i).getPosition().getX() >= minX && placesList.get(i).getPosition().getX() <= maxX && placesList.get(i).getPosition().getY() >= minY && placesList.get(i).getPosition().getY() <= maxY){
                System.out.println(placesList.get(i).toString());
            }
        }
        System.out.println("Places found: " + places.size());
        System.out.println("Found places within the specified rectangle:");
        while(!places.isEmpty()){
            System.out.println(places.peekFront().toString());
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
    private static void readPlacesFromDataFile(Map2D map){
        String filename = "D:\\DSA_github\\COSC2658_Project1\\src\\Nhan_\\places.txt";
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int x = Integer.parseInt(parts[0].trim());
                int y = Integer.parseInt(parts[1].trim());
                String[] services = parts[2].split(";");
                Position position = new Position(x, y);
                Place newPlace = new Place(position, services);
                map.add(newPlace);
                count++;
                if(count == 1000){
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading data from the file.");
            e.printStackTrace();
        }
    }
}



