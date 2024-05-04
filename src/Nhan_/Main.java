import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Map2D map = new Map2D();
        map.add(new Place("4", "Place 4", new Position(4, 4), new String[] { "School", "Restaurant" }));
        map.add(new Place("1", "Place 1", new Position(1, 1), new String[] { "Restaurant", "School" }));
        map.add(new Place("2", "Place 2", new Position(2, 2), new String[] { "Park", "Mall" }));
        map.add(new Place("3", "Place 3", new Position(3, 3), new String[] { "Restaurant", "Park" }));
        map.add(new Place("5", "Place 5", new Position(5, 5), new String[] { "Mall", "Park" }));
        map.add(new Place("6", "Place 6", new Position(6, 6), new String[] { "Restaurant", "School" }));
        map.add(new Place("7", "Place 7", new Position(7, 7), new String[] { "Restaurant", "Mall" }));
        map.add(new Place("8", "Place 8", new Position(8, 5), new String[] { "Service 15", "Service 16" }));
        map.add(new Place("9", "Place 9", new Position(7, 5), new String[] { "Service 17", "Service 18" }));
        // Print tree
        System.out.println("------Print Tree------");
        map.printTree();

        // Edit place
        System.out.println("\n\n------Edit Place------");
        map.editPlace(new Position(6, 6), new String[]{"Service 15", "Service 16"});
        System.out.println(map.findNode(new Position(6, 6)).place.toString());

        // Remove place
        System.out.println("\n\n------Remove Place------");
        map.remove(new Position(6, 6));
        map.printTree();

        // Search Function
        System.out.println("\n\n------Find Place------");
        ArrayQueue<Place> places = map.search(new Position(5, 5), 10, 10, "Restaurant");
        System.out.println("Places found: " + places.size());

        while (!places.isEmpty()) {
            System.out.println(places.peekFront());
            places.deQueue();
        }


        // Initialize the 10x10 matrix
        String[][] matrix = new String[10][10];
        for (int i = 0; i < 10; i++) {
            Arrays.fill(matrix[i], "-");
        }

        // Mark the places on the matrix
        List<Place> list = map.getPlacesList();
        for (int i = 0; i < list.size(); i++) {
            Place place = list.get(i);
            Position pos = place.getPosition();
            if (pos.getX() >= 0 && pos.getX() < 10 && pos.getY() >= 0 && pos.getY() < 10) {
                matrix[pos.getX()][pos.getY()] = "X";
            }
        }

        // // Print the matrix
        // System.out.println("Matrix representation of the map:");
        // printMatrix(matrix);

        // Mark the boundary of the places on the matrix
        // Define a search boundary and mark it
        Position center = new Position(4, 4);
        int width = 10;
        int height = 10;
        markBoundary(matrix, center, width, height);

        // Print the matrix with the boundary
        System.out.println("Matrix with search boundary:");
        printMatrix(matrix);
    }
    private static void printMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private static void markBoundary(String[][] matrix, Position center, int width, int height) {
        int startX = Math.max(center.getX() - width / 2, 0);
        int startY = Math.max(center.getY() - height / 2, 0);
        int endX = Math.min(center.getX() + width / 2, matrix.length - 1);
        int endY = Math.min(center.getY() + height / 2, matrix[0].length - 1);
    
        // Mark the boundary with "%"
        for (int x = startX; x <= endX; x++) {
            if (x < matrix.length) {
                if (startY < matrix[0].length) matrix[x][startY] = "%"; // Top boundary
                if (endY < matrix[0].length) matrix[x][endY] = "%"; // Bottom boundary
            }
        }
        for (int y = startY; y <= endY; y++) {
            if (y < matrix[0].length) {
                if (startX < matrix.length) matrix[startX][y] = "%"; // Left boundary
                if (endX < matrix.length) matrix[endX][y] = "%"; // Right boundary
            }
        }
    }
    
}

