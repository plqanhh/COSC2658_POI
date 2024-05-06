import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ADTs.*; 
public class Map2DKDTree {
    private Node root;
    private int size = 0;

    // Node class represents each node in the KD-Tree
    private class Node {
        Place place;
        Node left, right;
        int depth;

        // Node constructor initializes the place and depth
        Node(Place place, int depth) {
            this.place = place;
            this.depth = depth;
        }
    }

    // KD-Tree constructor initializes the tree and loads data from a file
    public Map2DKDTree() {
        this.root = null;
        loadFromFile();
    }

    // Insert a new place into the KD-Tree
    public void insert(Place place) {
        root = insert(root, place, 0);
        size++;
        if (size % 10 == 0) {
            rebuildTree(); // Rebalance the tree periodically
        }
    }

    // Helper method to insert nodes recursively
    private Node insert(Node node, Place place, int depth) {
        if (node == null)
            return new Node(place, depth);
        int cmp = compare(place, node.place, depth);
        if (cmp < 0)
            node.left = insert(node.left, place, depth + 1);
        else
            node.right = insert(node.right, place, depth + 1);
        return node;
    }

    // Compares two places based on their depth
    private int compare(Place a, Place b, int depth) {
        int cd = depth % 2;
        return (cd == 0) ? Integer.compare(a.getLocation().getX(), b.getLocation().getX())
                : Integer.compare(a.getLocation().getY(), b.getLocation().getY());
    }

    // Edit a place's services
    public void edit(Place place, ArrayList<String> newServices) {
        if (place != null) {
            place.setServices(newServices);
        }
    }

    // Search for places within a specified rectangle and service type
    public ArrayList<Place> search(Rectangle bounds, String serviceType) {
        ArrayList<Place> results = new ArrayList<>();
        search(root, bounds, serviceType, 0, results);
        return results;
    }

    // Recursive search method that includes type filtering
    private void search(Node node, Rectangle bounds, String serviceType, int depth, ArrayList<Place> results) {
        if (node == null || results.size() >= 50)
            return;
        Point loc = node.place.getLocation();
        if (bounds.contains(loc) && node.place.getServices().contains(serviceType)) {
            results.add(node.place);
        }
        int cd = depth % 2;
        if ((cd == 0 && loc.getX() > bounds.xMin) || (cd == 1 && loc.getY() > bounds.yMin)) {
            search(node.left, bounds, serviceType, depth + 1, results);
        }
        if ((cd == 0 && loc.getX() < bounds.xMax) || (cd == 1 && loc.getY() < bounds.yMax)) {
            search(node.right, bounds, serviceType, depth + 1, results);
        }
    }

    // Recursive method to print all nodes in the KD-Tree
    private void printRecursive(Node node, String prefix) {
        if (node != null) {
            System.out.println(prefix + "Place: " + node.place);
            printRecursive(node.left, prefix + "L-- ");
            printRecursive(node.right, prefix + "R-- ");
        }
    }


    // Builds the KD-Tree from a list of places
    private Node buildTree(ArrayList<Place> places, int start, int end, int depth) {
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        Node node = new Node(places.get(mid), depth);
        node.left = buildTree(places, start, mid - 1, depth + 1);
        node.right = buildTree(places, mid + 1, end, depth + 1);
        return node;
    }

    // Load places from a file into the KD-Tree
    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("places.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Place place = parseLineToPlace(line);
                if (place != null) {
                    insert(place); // Insert places to rebuild the tree
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load from file: " + e.getMessage());
        }
    }

    // Parses a line from the file into a Place object
    private Place parseLineToPlace(String line) {
        String[] parts = line.split(",");
        if (parts.length < 3)
            return null;
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        ArrayList<String> services = parseServices(parts[2]);
        return new Place(x, y, services);
    }

    // Parses service data into a list of strings
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

    // Method to save all places to a file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("places.txt"))) {
            saveNodeToFile(root, writer);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    // Recursive helper method to save node data to file
    private void saveNodeToFile(Node node, BufferedWriter writer) throws IOException {
        if (node != null) {
            writer.write(node.place.toFileString());
            writer.newLine();
            saveNodeToFile(node.left, writer);
            saveNodeToFile(node.right, writer);
        }
    }
    // Find a place by coordinates
    public Place findPlaceByCoordinates(int x, int y) {
        return findPlaceByCoordinates(root, x, y, 0);
    }

    // Helper method to find a place by coordinates recursively
    private Place findPlaceByCoordinates(Node node, int x, int y, int depth) {
        if (node == null) {
            return null;
        }
        if (node.place.getLocation().getX() == x && node.place.getLocation().getY() == y) {
            return node.place;
        }
        int cd = depth % 2;
        if ((cd == 0 && x < node.place.getLocation().getX()) || (cd == 1 && y < node.place.getLocation().getY())) {
            return findPlaceByCoordinates(node.left, x, y, depth + 1);
        } else {
            return findPlaceByCoordinates(node.right, x, y, depth + 1);
        }
    }

    /*--------------------------Remove Function ------------------------------- */
    public boolean remove(Place target) {
        Node parent = null;
        Node current = root;
        int cd = 0;
        while (current != null && !current.place.equals(target)) {
            parent = current;
            if (cd % 2 == 0) { // Compare x-coordinate
                current = (target.getLocation().getX() < current.place.getLocation().getX()) ? current.left
                        : current.right;
            } else { // Compare y-coordinate
                current = (target.getLocation().getY() < current.place.getLocation().getY()) ? current.left
                        : current.right;
            }
            cd++;
        }
        if (current == null)
            return false; // Node not found

        removeNode(current, parent, cd % 2);
        return true;
    }

    private void removeNode(Node node, Node parent, int cd) {
        if (node.left == null || node.right == null) {
            // Node with only one child or no child
            Node child = (node.left != null) ? node.left : node.right;
            if (parent == null) {
                root = child; // The node to be removed is the root
            } else {
                if (node == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
        } else {
            // Node with two children, find successor
            Node successor = findMin(node.right, cd);
            Place temp = node.place;
            node.place = successor.place;
            successor.place = temp; // Swap places
            removeNode(successor, node, (cd + 1) % 2); // Remove the successor
        }
    }

    // Helper to find the minimum node based on dimension cd
    private Node findMin(Node node, int cd) {
        Node current = node;
        Node minNode = node;
        int min = cd % 2 == 0 ? node.place.getLocation().getX() : node.place.getLocation().getY();
        while (current != null) {
            int currentVal = cd % 2 == 0 ? current.place.getLocation().getX() : current.place.getLocation().getY();
            if (currentVal < min) {
                min = currentVal;
                minNode = current;
            }
            current = current.left;
        }
        return minNode;
    }
    /*------------------------Remove Functions End----------------------------- */
    /*------------------------Test case : Balance Tree----------------------------- */
    // Method to print the tree
    public void print() {
        printRecursive(root, 0, "Root");
    }

    // Recursive method to print the tree
    private void printRecursive(Node node, int level, String prefix) {
        if (node == null) {
            return;
        }
        // Create indentation based on the level of the node
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("   "); // 3 spaces per level of depth
        }
        // Print the current node
        sb.append(prefix).append(": ").append(node.place);
        System.out.println(sb.toString());

        // Recursively print the left and right children
        printRecursive(node.left, level + 1, "L");
        printRecursive(node.right, level + 1, "R");
    }

    // Method to get the height of the tree
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Method to check if the tree is balanced
    public boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(node.left) && isBalanced(node.right)) {
            return true;
        } else {
            return false;
        }
    }

    // Call this method to check if the whole tree is balanced
    public boolean isTreeBalanced() {
        return isBalanced(root);
    }
    
    // Rebuilds the entire tree to maintain balance
    private void rebuildTree() {
        ArrayList<Place> places = new ArrayList<>();
        inorderTraversal(root, places);
        root = buildTree(places, 0, places.size() - 1, 0);
    }

    // Inorder traversal to collect all places
    private void inorderTraversal(Node node, ArrayList<Place> places) {
        if (node == null)
            return;
        inorderTraversal(node.left, places);
        places.add(node.place);
        inorderTraversal(node.right, places);
    }

    /*------------------------Test case : Balance Tree END----------------------------- */

}
