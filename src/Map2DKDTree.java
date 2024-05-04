import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ADTs.*;; // Make sure your ArrayList is capable of handling generic types properly

public class Map2DKDTree {
    private Node root;
    private int size = 0;

    private class Node {
        Place place;
        Node left, right;
        int depth;

        Node(Place place, int depth) {
            this.place = place;
            this.depth = depth;
        }
    }

    public Map2DKDTree() {
        this.root = null;
        loadFromFile();
    }

    public void insert(Place place) {
        root = insert(root, place, 0);
        size++;
        if (size % 10 == 0) { // Optionally rebalance the tree periodically
            rebuildTree();
        }
    }

    private Node insert(Node node, Place place, int depth) {
        if (node == null) {
            return new Node(place, depth);
        }

        int cd = depth % 2;
        int cmp = (cd == 0) ? Integer.compare(place.getLocation().getX(), node.place.getLocation().getX())
                : Integer.compare(place.getLocation().getY(), node.place.getLocation().getY());

        if (cmp < 0) {
            node.left = insert(node.left, place, depth + 1);
        } else {
            node.right = insert(node.right, place, depth + 1);
        }
        return node;
    }

    public void edit(Place place, ArrayList<String> newServices) {
        if (place != null) {
            place.setServices(newServices);
        }
    }

    public ArrayList<Place> search(Rectangle bounds) {
        ArrayList<Place> results = new ArrayList<>();
        search(root, bounds, 0, results);
        return results;
    }

    private void search(Node node, Rectangle bounds, int depth, ArrayList<Place> results) {
        if (node == null || results.size() >= 50) return;

        Point loc = node.place.getLocation();
        if (bounds.contains(loc)) {
            results.add(node.place);
        }

        int cd = depth % 2;
        if ((cd == 0 && loc.getX() > bounds.xMin) || (cd == 1 && loc.getY() > bounds.yMin)) {
            search(node.left, bounds, depth + 1, results);
        }
        if ((cd == 0 && loc.getX() < bounds.xMax) || (cd == 1 && loc.getY() < bounds.yMax)) {
            search (node.right, bounds, depth + 1, results);
        }  
    } 

    public void print() {
    printRecursive(root, "");
    }

    private void printRecursive(Node node, String prefix) {
        if (node != null) {
            System.out.println(prefix + "Place: " + node.place);
            printRecursive(node.left, prefix + "L-- ");
            printRecursive(node.right, prefix + "R-- ");
        }
    }

    private void rebuildTree() {
        ArrayList<Place> places = new ArrayList<>();
        inorderTraversal(root, places);
        root = buildTree(places, 0, places.size() - 1, 0);
    }

    private void inorderTraversal(Node node, ArrayList<Place> places) {
        if (node == null) return;
        inorderTraversal(node.left, places);
        places.add(node.place);
        inorderTraversal(node.right, places);
    }

    private Node buildTree(ArrayList<Place> places, int start, int end, int depth) {
        if (start > end) return null;
        int cd = depth % 2;
        quickSort(places, start, end, cd);
        int mid = start + (end - start) / 2;
        Node node = new Node(places.get(mid), depth);
        node.left = buildTree(places, start, mid - 1, depth + 1);
        node.right = buildTree(places, mid + 1, end, depth + 1);
        return node;
    }

    private void quickSort(ArrayList<Place> places, int low, int high, int dimension) {
        if (low < high) {
            int pi = partition(places, low, high, dimension);
            quickSort(places, low, pi - 1, dimension);
            quickSort(places, pi + 1, high, dimension);
        }
    }

    private int partition(ArrayList<Place> places, int low, int high, int dimension) {
        Place pivot = places.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if ((dimension == 0 && places.get(j).getLocation().getX() <= pivot.getLocation().getX()) ||
                (dimension == 1 && places.get(j).getLocation().getY() <= pivot.getLocation().getY())) {
                i++;
                Place temp = places.get(i);
                places.set(i, places.get(j));
                places.set(j, temp);
            }
        }
        Place temp = places.get(i + 1);
        places.set(i + 1, places.get(high));
        places.set(high, temp);
        return i + 1;
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("places.txt"))) {
            saveNodeToFile(root, bw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveNodeToFile(Node node, BufferedWriter bw) throws IOException {
        if (node != null) {
            bw.write(node.place.toFileString());
            bw.newLine();
            saveNodeToFile(node.left, bw);
            saveNodeToFile(node.right, bw);
        }
    }

    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("places.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Place place = parseLineToPlace(line);
                if (place != null) {
                    insert(place);  // Insert places to rebuild the tree
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Place parseLineToPlace(String line) {
        String[] parts = line.split(",");
        if (parts.length < 3) return null;

        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        ArrayList<String> services = parseServices(parts[2]);

        return new Place(x, y, services);
    }

    private ArrayList<String> parseServices(String servicesStr) {
        ArrayList<String> services = new ArrayList<>();
        if (servicesStr != null && !servicesStr.isEmpty()) {
            String[] serviceTokens = servicesStr.split(";");
            for (String service : serviceTokens) {
                if (!service.trim().isEmpty()) {
                    services.add(service.trim());
                }
            }
        }
        return services;
    }

    public Place findPlaceByCoordinates(int x, int y) {
        return findPlaceByCoordinates(root, x, y, 0);
    }

    private Place findPlaceByCoordinates(Node node, int x, int y, int depth) {
        if (node == null) {
            return null;
        }
        // Check if the current node matches the coordinates
        if (node.place.getLocation().getX() == x && node.place.getLocation().getY() == y) {
            return node.place;
        }

        // Calculate the current dimension (0 for x, 1 for y)
        int cd = depth % 2;

        // Determine which side to search
        if ((cd == 0 && x < node.place.getLocation().getX()) || (cd == 1 && y < node.place.getLocation().getY())) {
            return findPlaceByCoordinates(node.left, x, y, depth + 1);
        } else {
            return findPlaceByCoordinates(node.right, x, y, depth + 1);
        }
    }

}
