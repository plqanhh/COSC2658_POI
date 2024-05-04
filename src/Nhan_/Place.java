import java.util.Arrays;

public class Place {
    private String id;
    private String name;
    private final Position position;
    private String[] services;

    public Place(String id, String name, Position position, String[] services) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.services = services;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public String[] getServices() {
        return services;
    }

    @Override
    public String toString() {
        return "Place [id=" + id + ", name=" + name + ", position=" + position + ", services="
                + Arrays.toString(services) + "]";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServices(String[] services) {
        this.services = services;
    }
}
class Node{
    Place place;
    Node left, right, parent;
    int height;
    boolean isRed; // true for Red, false for Black (red by default)

    public Node(Place place, Node parent) {
        this.place = place;
        this.left = null;
        this.right = null;
        this.parent = parent;
        this.height = 0;
        this.isRed = true; // New nodes are red by default
    }
    public int getHeight() {
        return height;
    }
        // update and return the updated height
    public int updateHeight() {
        int leftHeight = 0;
        if (left != null) {
            leftHeight = left.getHeight();
        }
        int rightHeight = 0;
        if (right != null) {
            rightHeight = right.getHeight();
        }
        height = Math.max(leftHeight, rightHeight) + 1;
        return height;
    }

    public int getBalanceFactor() {
        int leftHeight = 0;
        if (left != null) {
            leftHeight = left.getHeight();
        }
        int rightHeight = 0;
        if (right != null) {
            rightHeight = right.getHeight();
        }
        return rightHeight - leftHeight;
    }
    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }
    public Node getParent() {
        return parent;
    }
    public Place getPlace() {
        return place;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
}

