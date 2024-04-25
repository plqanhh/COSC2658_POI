public class Place {
    private String id;
    private String name;
    private final Point point;
    private String[] services;

    public Place(String id, String name, Point position, String[] services) {
        this.id = id;
        this.name = name;
        this.point = position;
        this.services = services;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Point getPoint() { return point; }
    public String[] getServices() { return services; }
    public void setServices(String[] services) { this.services = services; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Location: (" + point.getX() + "," + point.getY() + ")";
    }
}
