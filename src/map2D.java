import ADTs.ArrayList;
import ADTs.List;

public class map2D {
    private PlaceNode root;


    /**
     * Inserts a new place into the map using alternating x and y comparisons.
     */
    public void addNew(Place place) {
        root = insert(root, place, 0);
    }

    private PlaceNode insert(PlaceNode current, Place place, int depth) {
        if (current == null) {
            return new PlaceNode(place);
        }

        int cd = depth % 2;  // 0 for x, 1 for y
        int comparePlace = (cd == 0) ? place.getPoint().getX() : place.getPoint().getY();
        int compareParent = (cd == 0) ? current.place.getPoint().getX() : current.place.getPoint().getY();

        if (comparePlace < compareParent) {
            current.left = insert(current.left, place, depth + 1);
        } else {
            current.right = insert(current.right, place, depth + 1);
        }
        return current;
    }

    /**
     * Searches for places within a rectangular area defined by a top-left point and dimensions.
     */
    public List<Place> search(Rectangle area, String serviceType) {
        List<Place> results = new ArrayList<>(); // Your custom ArrayList
        searchInRectangle(root, area, serviceType, results);
        return results;
    }

    private void searchInRectangle(PlaceNode node, Rectangle area, String serviceType, List<Place> results) {
        if (node == null) {
            return;
        }

        Point p = node.place.getPoint();
        if (area.contains(p) && containsServiceType(node.place.getServices(), serviceType)) {
            results.add(node.place);
        }

        // Determine whether to search left or right branches of the tree
        int compareX = area.x + area.width;
        int compareY = area.y + area.height;

        if (node.place.getPoint().getX() >= area.x && node.left != null) {
            searchInRectangle(node.left, area, serviceType, results);
        }
        if (node.place.getPoint().getX() <= compareX && node.right != null) {
            searchInRectangle(node.right, area, serviceType, results);
        }
    }

    /**
     * Helper method to check if the array of services contains the specified service type.
     * @param services Array of service types offered by a place
     * @param serviceType Service type to look for
     * @return true if serviceType is found in services; otherwise, false
     */
    private boolean containsServiceType(String[] services, String serviceType) {
        for (String service : services) {
            if (service.equals(serviceType)) {
                return true;
            }
        }
        return false;
    }
}