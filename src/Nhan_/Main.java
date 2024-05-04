public class Main {
    public static void main(String[] args) {

        Map2D map = new Map2D();
        map.add(new Place("4", "Place 4", new Position(4, 4), new String[] { "Service 7", "Service 8" }));
        map.add(new Place("1", "Place 1", new Position(1, 1), new String[] { "Service 1", "Service 2" }));
        map.add(new Place("2", "Place 2", new Position(2, 2), new String[] { "Service 3", "Service 4" }));
        map.add(new Place("3", "Place 3", new Position(3, 3), new String[] { "Service 5", "Service 6" }));
        map.add(new Place("5", "Place 5", new Position(5, 5), new String[] { "Service 9", "Service 10" }));
        map.add(new Place("6", "Place 6", new Position(6, 6), new String[] { "Service 11", "Service 12" }));
        map.add(new Place("7", "Place 7", new Position(7, 7), new String[] { "Service 13", "Service 14" }));
        // map.add(new Place("8", "Place 8", new Position(8, 5), new String[] { "Service 15", "Service 16" }));
        // map.add(new Place("9", "Place 9", new Position(7, 5), new String[] { "Service 17", "Service 18" }));

        
        map.printTree();
        /*---- Edit Place */
        // System.out.println("\n\n------Edit Place------");
        // map.editPlace(new Position(6, 6), new String[]{"Service 15", "Service 16"});
        // System.out.println(map.findNode(new Position(6, 6)).place.toString());
        // Remove place
        System.out.println("\n\n------Remove Place------");
        map.remove(new Position(6, 6));
        map.printTree();
    }
}