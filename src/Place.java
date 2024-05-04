import ADTs.*;
public class Place {
    private Point location;
    private ArrayList<String> services; // Assuming services are stored as a boolean array for availability

    public Place(int x, int y, ArrayList<String> services) {
        this.location = new Point(x, y);
        this.services = services;
    }

    public Point getLocation() {
        return location;
    }

    public ArrayList<String> getServices() {
        return services;
    }

    public boolean offersService(ServiceType service) {
        return services.contains(service.name());
    }

    public void setServices(ArrayList<String> services) {
        this.services = services;
    }

    public String toFileString() {
        return getLocation().getX() + "," + getLocation().getY() + "," + servicesToString();
    }

    public String servicesToString() {
        return String.join(", ", services);
    }

    @Override
    public String toString() {
        return "Place at (" + location.getX() + ", " + location.getY() + ") offering " + servicesToString();
    }
}
