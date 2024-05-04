public class Rectangle {
    int xMin, yMin, xMax, yMax;

    // Existing constructor for direct specification (keep if needed)
    public Rectangle(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    // New constructor to define a square from a center point and 'radius'
    public Rectangle(Point center, int halfSideLength) {
        this.xMin = center.getX() - halfSideLength;
        this.yMin = center.getY() - halfSideLength;
        this.xMax = center.getX() + halfSideLength;
        this.yMax = center.getY() + halfSideLength;
    }

    public boolean contains(Point p) {
        return p.getX() >= xMin && p.getX() <= xMax &&
                p.getY() >= yMin && p.getY() <= yMax;
    }
}
