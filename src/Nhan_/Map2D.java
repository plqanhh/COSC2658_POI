import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Map2D{
    private Node root;
    private ArrayList<Node> placesList;
    final int maxResults = 49;
    
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
        if(root == null){
            root = new Node(place, null);
            placesList.add(root);
            return root;
        }
        Node current = root;
        boolean useX = true;
        while(current != null){
            int comparePlace;
            int comparePosition;
            if(useX){
                comparePlace = current.place.getPosition().getX();
                comparePosition = place.getPosition().getX();
            } else {
                comparePlace = current.place.getPosition().getY();
                comparePosition = place.getPosition().getY();
            }
            if(comparePlace == comparePosition){
                return null;
            } else if(comparePlace > comparePosition){
                if(current.left == null){
                    current.left = new Node(place, current);
                    placesList.add(current.left);
                    return current.left;
                }
                current = current.left;
            } else {
                if(current.right == null){
                    current.right = new Node(place, current);
                    placesList.add(current.right);
                    return current.right;
                }
                current = current.right;
            }
            useX = !useX;
        }
        return null;
    }


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
    
        
    public void printTree() {
        printNode(root, 0, "Root");
    }

    private void printNode(Node node, int depth, String direction) {
        if (node == null) return;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("   "); // Indentation for visual hierarchy
        }

        // Add node information
        sb.append(direction)
          .append(": [")
          .append(node.place.getPosition().getX())
          .append(", ")
          .append(node.place.getPosition().getY())
          .append("] ");
    

        System.out.println(sb.toString());

        // Recursively print left and right children
        printNode(node.left, depth + 1, "L");
        printNode(node.right, depth + 1, "R");
    }
    public void editPlace(Position position, String[] services){
        Node node = findNode(position);
        if(node != null){
            node.place.setServices(services);
        }
    }
    public Node findNode(Position position){
        Node current = root;
        boolean useX = true;
        while(current != null){
            int comparePlace;
            int comparePosition;
            if(useX){
                comparePlace = current.place.getPosition().getX();
                comparePosition = position.getX();
            } else {
                comparePlace = current.place.getPosition().getY();
                comparePosition = position.getY();
            }
            if(comparePlace == comparePosition){
                return current;
            } else if(comparePlace > comparePosition){
                current = current.left;
            } else {
                current = current.right;
            }
            useX = !useX;
        }
        return null;
    }
    /*--------------------------Remove Function ------------------------------- */
    
    public boolean remove(Position pos) {
        // Find the node to remove
        Node nodeToRemove = findNode(pos);
        if (nodeToRemove == null) {
            return false;
        }
        // Remove the node
        removeNode(nodeToRemove);
        return true;
    }

    // Helper method to remove a node
    private void removeNode(Node node) {
        // Case 1: Node has no children
        if (node.getLeft() == null && node.getRight() == null) {
            removeLeafNode(node);
        }
        // Case 2: Node has only one child
        else if (node.getLeft() == null || node.getRight() == null) {
            removeNodeWithOneChild(node);
        }
        // Case 3: Node has two children
        else {
            removeNodeWithTwoChildren(node);
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
        Node parent = node.getParent();
        if (parent.getLeft() == node) {
            parent.setLeft(null);
        } else {
            parent.setRight(null);
        }
    }
    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            // u is the root of the tree
            root = v;
        } else if (u == u.parent.left) {
            // u is the left child
            u.parent.left = v;
        } else {
            // u is the right child
            u.parent.right = v;
        }
        if (v != null) {
            // Update the parent of v
            v.parent = u.parent;
        }
    }
    // Helper method to remove a node with one child
    private void removeNodeWithOneChild(Node node) {
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
        ArrayQueue<Place> listOfAvailablePlace = new ArrayQueue<>();
        int minX = center.getX() - width/2;
        int maxX = center.getX() + width/2;
        int minY = center.getY() - height/2;
        int maxY = center.getY() + height/2;
        System.out.println("The search area is having the X-axis:(" + minX + ", " + maxX + ") and Y-axis: (" + minY + ", " + maxY + ")");
        searchNearestPlace(root, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace, 0);
        return listOfAvailablePlace;
    }

    public void searchNearestPlace(Node curNode, int minX, int maxX, int minY, int maxY, String type, int maxResults, ArrayQueue<Place> listOfAvailablePlace, int depth) {
        if(curNode == null || listOfAvailablePlace.size() >= maxResults){
            return;
        }
    
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
    
        // Determine whether to go left or right in the tree
        if (useX) {
            if (x >= minX && curNode.left != null) {
                searchNearestPlace(curNode.left, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace, depth + 1);
            }
            if (x <= maxX && curNode.right != null) {
                searchNearestPlace(curNode.right, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace, depth + 1);
            }
        } else {
            if (y >= minY && curNode.left != null) {
                searchNearestPlace(curNode.left, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace, depth + 1);
            }
            if (y <= maxY && curNode.right != null) {
                searchNearestPlace(curNode.right, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace, depth + 1);
            }
        }
    }
    
        // // Method to save all places to a file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("place.txt"))) {
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