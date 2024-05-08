import java.util.Random;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataGenerator {

    public static void generatePlaces(String filename, int numberOfPlaces, int mapSize) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Random rand = new Random();

            for (int i = 0; i < numberOfPlaces; i++) {
                int x = rand.nextInt(mapSize);
                int y = rand.nextInt(mapSize);
                String services = generateServices(rand);
                writer.write(x + "," + y + "," + services);
                writer.newLine();
            }
        }
    }

    private static String generateServices(Random rand) {
        ServiceType[] allServices = ServiceType.values();
        int numServices = 1 ; // Generates 1 or 2 services
        ArrayList<ServiceType> selectedServices = new ArrayList<>();

        while (selectedServices.size() < numServices) {
            ServiceType service = allServices[rand.nextInt(allServices.length)];
            if (!selectedServices.contains(service)) {
                selectedServices.insertAt(selectedServices.size(), service);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selectedServices.size(); i++) {
            if (sb.length() > 0)
                sb.append(";");
            sb.append(selectedServices.get(i).name());
        }
        return sb.toString();
    }

    // public static ArrayList<Place> readPlacesFromDataFile(String filename) throws IOException {
    //     ArrayList<Place> places = new ArrayList<>();
    //     try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
    //         String line;
    //         while ((line = reader.readLine()) != null) {
    //             String[] parts = line.split(",");
    //             int x = Integer.parseInt(parts[0].trim());
    //             int y = Integer.parseInt(parts[1].trim());
    //             ArrayList<String> services = parseServices(parts[2]); // Changed to parse services as String list
    //             places.insertAt(places.size(), new Place(x, y, services));
    //         }
    //     }
    //     return places;
    // }

    private static ArrayList<String> parseServices(String servicesStr) {
        ArrayList<String> services = new ArrayList<>();
        String[] serviceTokens = servicesStr.split(";");
        for (String service : serviceTokens) {
            if (!service.trim().isEmpty()) {
                services.add(service.trim());
            }
        }
        return services;
    }

    public static void main(String[] args) {
        try {
            String filename = "places.txt";
            int numberOfPlaces = 500000; // Number of places to generate
            int mapSize = 1000000; // Reduced map size for localized data
            generatePlaces(filename, numberOfPlaces, mapSize);
            System.out.println("Data generation complete. Generated " + numberOfPlaces + " places.");

        //     // Optionally load and print the data from the file for verification
        //     ArrayList<Place> loadedPlaces = readPlacesFromDataFile(filename);
        //     System.out.println("Loaded " + loadedPlaces.size() + " places from data file.");
        // } catch (IOException e) {
        //     System.out.println("Error processing file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error generating data: " + e.getMessage());
        }
    }
}