public class Rectangle {
    int x, y; // Top-left corner
    int width, height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if a given point is within the rectangle.
     * @param point the point to check
     * @return true if the point is inside the rectangle; false otherwise
     */
    public boolean contains(Point point) {
        return point.x >= x && point.x <= x + width &&
               point.y >= y && point.y <= y + height;
    }
}