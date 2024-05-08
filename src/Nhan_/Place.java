import java.util.Arrays;

public class Place {
    private final Position position;
    private String[] services;

    public Place(Position position, String[] services) {
        this.position = position;
        this.services = services;
    }
    
    public Position getPosition() {
        return position;
    }

    public String[] getServices() {
        return services;
    }

    public String serviceString() {
        return String.join(", ", services);
    }

    @Override
    public String toString() {
        return "Place at (" + position.getX() + ", " + position.getY() + ") offering " + servicesToString();
    }

    public void setServices(String[] services) {
        this.services = services;
    }
    public String toFileString() {
        return getPosition().getX() + "," + getPosition().getY() + "," + servicesToString();
    }

    public String servicesToString() {
        return String.join(", ", services);
    }

}
class Node{
    Place place;
    Node left, right, parent;
    int height;

    public Node(Place place, Node parent) {
        this.place = place;
        this.left = null;
        this.right = null;
        this.parent = parent;
        this.height = 0;
    }
    public int getHeight() {
        return height;
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

