public class Main {
    public static void main(String[] args) {
        map2D map = new map2D();
        map.addNew(new Place("1", "Place One", new Point(4, 7), new String[]{"Restaurant"}));
        map.addNew(new Place("2", "Place Two", new Point(5, 5), new String[]{"Cafe"}));

        // Add search functionality demonstration here
    }
}