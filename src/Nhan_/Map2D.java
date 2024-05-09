import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Map2D{
    private Node root;
    private ArrayList<Node> placesList;
    final int maxResults = 50;
    
    public Map2D(){
        root = null;
        placesList = new ArrayList<>();
    }
    
    public ArrayList<Place> getPlacesList(){
        ArrayList<Place> places = new ArrayList<>();
        for(int i = 0; i < placesList.size(); i++){
            places.insertAt(i, placesList.get(i).place);
        }
        return places;
    }

    public Node add(Place place){
        // If the root is null, create a new node and set it as the root
        if(root == null){
            root = new Node(place, null);
            placesList.add(root);
            return root;
        }
        // Otherwise, find the correct position to insert the new node
        Node current = root;
        boolean useX = true;
        while(current != null){
            int comparePlace;
            int comparePosition;
            // Compare the x-coordinate if useX is true, otherwise compare the y-coordinate
            if(useX){
                comparePlace = current.place.getPosition().getX();
                comparePosition = place.getPosition().getX();
            } else {
                comparePlace = current.place.getPosition().getY();
                comparePosition = place.getPosition().getY();
            }
            // If the place is already in the tree, return null
            if(comparePlace == comparePosition){
                return null;
            } else if(comparePlace > comparePosition){// If the new place is less than the current place, go left
                if(current.left == null){
                    current.left = new Node(place, current);
                    placesList.add(current.left);
                    return current.left;
                }
                current = current.left;// If the new place is less than the current place, go left
            } else {
                if(current.right == null){// If the new place is greater than the current place, go right
                    current.right = new Node(place, current);
                    placesList.add(current.right);
                    return current.right;
                }
                current = current.right;// If the new place is greater than the current place, go right
            }
            useX = !useX;// Switch the comparison axis
        }
        return null;// Return null if the place is already in the tree
    }
    public void editPlace(Position position, String[] services){
        Node node = findNode(position);// Find the node with the given position
        if(node != null){// If the node is found, update the services
            node.place.setServices(services);
        }
    }
    public Node findNode(Position position){
        // If the root is null, return null
        Node current = root;
        boolean useX = true;
        while(current != null){
            int comparePlace;
            int comparePosition;
            if(useX){// Compare the x-coordinate if useX is true, otherwise compare the y-coordinate
                comparePlace = current.place.getPosition().getX();
                comparePosition = position.getX();
            } else {
                comparePlace = current.place.getPosition().getY();
                comparePosition = position.getY();
            }
            if(comparePlace == comparePosition){// If the place is found, return the node
                return current;
            } else if(comparePlace > comparePosition){// If the current place is greater than the target place, go left
                current = current.left;
            } else {// If the current place is less than the target place, go right
                current = current.right;
            }
            useX = !useX;// Switch the comparison axis
        }
        return null;// Return null if the place is not found
    }
    /*--------------------------Remove Function ------------------------------- */
    
    public boolean remove(Position pos) {
        // Find the node to remove
        Node nodeToRemove = findNode(pos);// Find the node with the given position
        if (nodeToRemove == null) {// If the node is not found, return false
            return false;
        }
        // Remove the node
        removeNode(nodeToRemove);// Remove the node
        return true;
    }

    // Helper method to remove a node
    private void removeNode(Node node) {
        // Case 1: Node has no children
        if (node.getLeft() == null && node.getRight() == null) {// If the node is a leaf node
            removeLeafNode(node);// Remove a leaf node
        }
        // Case 2: Node has only one child
        else if (node.getLeft() == null || node.getRight() == null) {// If the node has one child
            removeNodeWithOneChild(node);// Remove a node with one child
        }
        // Case 3: Node has two children
        else {
            removeNodeWithTwoChildren(node);// Remove a node with two children
        }
    }

    // // Helper method to remove a leaf node
    private void removeLeafNode(Node node) {
        if (node == root) {
            root = null;
        } else {
            detachNode(node);
        }
        placesList.remove(node);
    }
    
    private void detachNode(Node node) {
        // Detach the node from its parent
        Node parent = node.getParent();
        if (parent.getLeft() == node) {
            parent.setLeft(null);// If the node is a left child, set the left child to null
        } else {
            parent.setRight(null);// If the node is a right child, set the right child to null
        }
    }
    // Helper method to remove a node with one child
    private void removeNodeWithOneChild(Node node) {
        // Replace the node with its child
        Node child = (node.getLeft() != null) ? node.getLeft() : node.getRight();
        if (node == root) {
            root = child;
        } else {
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(child);
            } else {
                node.getParent().setRight(child);
            }
        }
        if (child != null) {
            child.setParent(node.getParent());
        }
        // Remove the node for testing
        placesList.remove(node);
    }

    // // Helper method to remove a node with two children
    private void removeNodeWithTwoChildren(Node node) {
        Node successor = findSuccessor(node.getRight());
        node.place = successor.place;
        removeNode(successor);
        placesList.remove(node);
    }

    // Helper method to find the successor node
    private Node findSuccessor(Node node) {
        Node curNode = node;
        while (curNode.getLeft() != null) {
            curNode = curNode.getLeft();
        }
        return curNode;
    }

/*------------------------Remove Functions End----------------------------- */ 

/* ------------------------Search Functions Start----------------------------- */
    public ArrayQueue<Place> search(Position center, int width, int height, String type){
        // Create a queue to store the results
        ArrayQueue<Place> listOfAvailablePlace = new ArrayQueue<>();
        int minX = center.getX() - width/2;
        int maxX = center.getX() + width/2;
        int minY = center.getY() - height/2;
        int maxY = center.getY() + height/2;
        // Check the boundaries
        if(minX < 0){
            minX = 0;
        }
        if(maxX > 10000000){
            maxX = 10000000;
        }
        if(minY < 0){
            minY = 0;
        }
        if(maxY > 10000000){
            maxY = 10000000;
        }
        System.out.println("The search area is having the X-axis:(" + minX + ", " + maxX + ") and Y-axis: (" + minY + ", " + maxY + ")");
        // Search for the available places
        searchAvailablePlace(root, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace, 0);
        return listOfAvailablePlace;
    }

    public void searchAvailablePlace(Node curNode, int minX, int maxX, int minY, int maxY, String type, int maxResults, ArrayQueue<Place> listOfAvailablePlace, int depth) {
        // Check if the current node is null or the maximum number of results has been reached
        if(curNode == null || listOfAvailablePlace.size() >= maxResults){
            return;
        }
        // Get the x and y coordinates of the current node
        int x = curNode.place.getPosition().getX();
        int y = curNode.place.getPosition().getY();
        boolean useX = (depth % 2) == 0;
    
        // Check current node
        if(x >= minX && x <= maxX && y >= minY && y <= maxY){
            for(String service : curNode.place.getServices()){
                if(service.equals(type)){
                    listOfAvailablePlace.enQueue(curNode.place);
                    break;
                }
            }
        }
    
        // Recursively search the left and right subtrees
        if (useX) {
            if (x >= minX && curNode.left != null) {// If the x-coordinate is within the range, search the left subtree
                searchAvailablePlace(curNode.left, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace, depth + 1);
            }
            if (x <= maxX && curNode.right != null) {// If the x-coordinate is within the range, search the right subtree
                searchAvailablePlace(curNode.right, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace, depth + 1);
            }
        } else {// If the y-coordinate is within the range, search the left subtree
            if (y >= minY && curNode.left != null) {// If the y-coordinate is within the range, search the left subtree
                searchAvailablePlace(curNode.left, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace, depth + 1);
            }
            if (y <= maxY && curNode.right != null) {// If the y-coordinate is within the range, search the right subtree
                searchAvailablePlace(curNode.right, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace, depth + 1);
            }
        }
    }
    
        // // Method to save all places to a file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\DSA_github\\COSC2658_Project1\\src\\Nhan_\\places.txt"))) {
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
}

/* ------------------------Search Functions End----------------------------- */