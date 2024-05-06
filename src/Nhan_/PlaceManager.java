import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class PlaceManager {
    private static final Random rand = new Random();
    private static final String[] types = {"Restaurant", "Hospital", "Library", "Cafe", "Market"};

    public static void generateAllPlaces(Map2D map, String filename) {
        try (PrintWriter out = new PrintWriter(new File(filename))) {
            // Generate places for each range
            generatePlaces(map, out, 'A', 1, 10000, 12500);      // Range 1: 1 to 10,000
            generatePlaces(map, out, 'B', 10001, 100000, 12500); // Range 2: 10,001 to 100,000
            generatePlaces(map, out, 'C', 100001, 1000000, 12500); // Range 3: 100,001 to 1,000,000
            generatePlaces(map, out, 'D', 1000001, 10000000, 12500); // Range 4: 1,000,001 to 10,000,000
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void generatePlaces(Map2D map, PrintWriter out, char prefix, int min, int max, int count) {
        for (int i = 0; i < count; i++) {
            int x = rand.nextInt(max - min + 1) + min;
            int y = rand.nextInt(max - min + 1) + min;
            String type = types[rand.nextInt(types.length)];
            String id = prefix + Integer.toString(i + 1); // Simple ID: one character followed by a number
            String name = "Place " + id;
//            map.add(id, name, x, y, type);
            out.printf("%s,%s,%d,%d,%s\n", id, name, x, y, type);
        }
    }

    public static void loadPlacesFromFile(Map2D map, String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String[] newParts = parts[4].split(",");
                    Position position = new Position(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                    Place place = new Place( position , newParts);
                    map.add(place);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
