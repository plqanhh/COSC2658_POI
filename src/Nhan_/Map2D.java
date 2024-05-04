public class Map2D{
    private Node root;
    private ArrayList<Node> placesList;
    final int maxResults = 50;
    
    public Map2D(){
        root = null;
        placesList = new ArrayList<>();
    }
    public Node add(Place place){
        if(root == null){
            root = new Node(place, null);
            placesList.insertAt(placesList.size(), root);
            root.isRed = false; // Root is always black
            return root;
        }
        Node current = root;
        Node parentNode = null;
        boolean useX = true;
        
        while(current != null){
            parentNode = current;
            int comparePlace;
            int compareParent;
            if(useX){
                comparePlace = current.place.getPosition().getX();
                compareParent = place.getPosition().getX();
            } else {
                comparePlace = current.place.getPosition().getY();
                compareParent = place.getPosition().getY();
            }
            if(comparePlace > compareParent){
                if(current.left == null){
                    Node newPlace = new Node(place, parentNode);
                    current.left = newPlace ;
                    placesList.insertAt(placesList.size()-1, newPlace);
                    insertFixup(newPlace);
                    return newPlace;
                }
                current = current.left;
            } else {
                if(current.right == null){
                    Node newPlace = new Node(place, parentNode);
                    current.right = newPlace;
                    placesList.insertAt(placesList.size()-1, newPlace);
                    insertFixup(newPlace);
                    return newPlace;
                }
                current = current.right;
            }  
            useX = !useX; 
        }
        return null;
    }
    public ArrayList<Place> getPlacesList(){
        ArrayList<Place> places = new ArrayList<>();
        for(int i = 0; i < placesList.size(); i++){
            places.insertAt(i, placesList.get(i).place);
        }
        return places;
    }
    private void insertFixup(Node node) {
        while (node != root && node.parent.isRed) {
            Node grandparent = node.parent.parent;
            Node uncle = (node.parent == grandparent.left) ? grandparent.right : grandparent.left;
    
            if (uncle != null && uncle.isRed) { // Uncle red
                node.parent.isRed = false;
                uncle.isRed = false;
                grandparent.isRed = true;
                node = grandparent;
            } else { // Uncle black or null
                if (node.parent == grandparent.left) {
                    if (node == node.parent.right) { // Left-Right Case
                        node = node.parent;
                        rotateLeft(node);
                    }
                    // Left-Left Case
                    node.parent.isRed = false;
                    grandparent.isRed = true;
                    rotateRight(grandparent);
                } else {
                    if (node == node.parent.left) { // Right-Left Case
                        node = node.parent;
                        rotateRight(node);
                    }
                    // Right-Right Case
                    node.parent.isRed = false;
                    grandparent.isRed = true;
                    rotateLeft(grandparent);
                }
            }
        }
        root.isRed = false; // Root should always be black
    }


    private void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) rightChild.left.parent = node;
        rightChild.parent = node.parent;
        if (node.parent == null) root = rightChild;
        else if (node == node.parent.left) node.parent.left = rightChild;
        else node.parent.right = rightChild;
        rightChild.left = node;
        node.parent = rightChild;
    }
    
    private void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) leftChild.right.parent = node;
        leftChild.parent = node.parent;
        if (node.parent == null) root = leftChild;
        else if (node == node.parent.right) node.parent.right = leftChild;
        else node.parent.left = leftChild;
        leftChild.right = node;
        node.parent = leftChild;
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
          .append("] ")
          .append(node.isRed ? "Red" : "Black"); // Add color information if it's a Red-Black Tree

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
    private void deleteFixup(Node node) {
        while (node != root && !node.isRed) {
            if (node == node.parent.getLeft()) {
                Node sibling = node.parent.getRight();
                if (sibling.isRed) {
                    sibling.isRed = false;
                    node.parent.isRed = true;
                    rotateLeft(node.parent);
                    sibling = node.parent.getRight();
                }
                if (!sibling.getLeft().isRed && !sibling.getRight().isRed) {
                    sibling.isRed = true;
                    node = node.parent;
                } else {
                    if (!sibling.getRight().isRed) {
                        sibling.getLeft().isRed = false;
                        sibling.isRed = true;
                        rotateRight(sibling);
                        sibling = node.parent.getRight();
                    }
                    sibling.isRed = node.parent.isRed;
                    node.parent.isRed = false;
                    sibling.getRight().isRed = false;
                    rotateLeft(node.parent);
                    node = root;
                }
            } else { // Node is node.parent.getRight()
                Node sibling = node.parent.getLeft();
                if (sibling.isRed) {
                    sibling.isRed = false;
                    node.parent.isRed = true;
                    rotateRight(node.parent);
                    sibling = node.parent.getLeft();
                }
                if (!sibling.getRight().isRed && !sibling.getLeft().isRed) {
                    sibling.isRed = true;
                    node = node.parent;
                } else {
                    if (!sibling.getLeft().isRed) {
                        sibling.getRight().isRed = false;
                        sibling.isRed = true;
                        rotateLeft(sibling);
                        sibling = node.parent.getLeft();
                    }
                    sibling.isRed = node.parent.isRed;
                    node.parent.isRed = false;
                    sibling.getLeft().isRed = false;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        node.isRed = false;
    }
    
    public void remove(Position pos) {
        // Find the node to remove
        Node nodeToRemove = findNode(pos);
        if (nodeToRemove == null) {
            System.out.println("Place not found at point: " + pos);
            return;
        }
        // Remove the node
        removeNode(nodeToRemove);
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

    // Helper method to remove a leaf node
    private void removeLeafNode(Node node) {
        if (node.isRed || node == root) {
            if (node == root) root = null;
            else detachNode(node);
        } else {
            deleteFixup(node);
            detachNode(node);
            placesList.remove(node);
        }
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
        transplant(node, child);
        placesList.remove(node);
        if (!node.isRed) {
            if (child.isRed) child.isRed = false;
            else deleteFixup(child);
        }
    }

    // Helper method to remove a node with two children
    private void removeNodeWithTwoChildren(Node node) {
        Node successor = findSuccessor(node.getRight());
        boolean originalColorIsRed = successor.isRed;
        Node successorChild = (successor.getRight() != null) ? successor.getRight() : null;
    
        if (successor.getParent() != node) {
            transplant(successor, successorChild);
            successor.setRight(node.getRight());
            successor.getRight().setParent(successor);
        }
        transplant(node, successor);
        placesList.remove(node);
        successor.setLeft(node.getLeft());
        successor.getLeft().setParent(successor);
        successor.isRed = node.isRed;
    
        if (!originalColorIsRed && successorChild != null) {
            deleteFixup(successorChild);
        }
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
        searchNearestPlace(root, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace);
        return listOfAvailablePlace;
    }
    public void searchNearestPlace(Node curNode, int minX, int maxX, int minY, int maxY , String type, int maxResults, ArrayQueue<Place> listOfAvailablePlace){
        if(curNode == null || listOfAvailablePlace.size() > maxResults){
            return;
        }
        int x = curNode.place.getPosition().getX();
        int y = curNode.place.getPosition().getY();
        if(x >= minX && x <= maxX && y >= minY && y <= maxY){
            for(String service : curNode.place.getServices()){
                if(service.equals(type)){
                    listOfAvailablePlace.enQueue(curNode.place);
                    break;
                }
            }
        }
       
        if (curNode.left != null && (curNode.left.place.getPosition().getX() >= minX || curNode.left.place.getPosition().getY() >= minY)) {
            searchNearestPlace(curNode.left, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace);
        }
        if (curNode.right != null && (curNode.right.place.getPosition().getX() <= maxX || curNode.right.place.getPosition().getY() <= maxY)) {
            searchNearestPlace(curNode.right, minX, maxX, minY, maxY, type, maxResults, listOfAvailablePlace);
        }
    }
}

/* ------------------------Search Functions End----------------------------- */