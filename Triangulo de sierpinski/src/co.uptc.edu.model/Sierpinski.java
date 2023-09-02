public class Sierpinski {
    private int iterations;

    public Sierpinski() {
        this.iterations = 0;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getIterations() {
        return iterations;
    }

    public Triangle calculateSierpinskiTriangle(int iterations, Coordinate p1, Coordinate p2, Coordinate p3) {
        if (iterations == 0) {
            return new Triangle(p1, p2, p3);
        } else {
            Coordinate middle1 = calculateMidPoint(p1, p2);
            Coordinate middle2 = calculateMidPoint(p2, p3);
            Coordinate middle3 = calculateMidPoint(p1, p3);

            Triangle triangle1 = calculateSierpinskiTriangle(iterations - 1, p1, middle1, middle3);
            Triangle triangle2 = calculateSierpinskiTriangle(iterations - 1, middle1, p2, middle2);
            Triangle triangle3 = calculateSierpinskiTriangle(iterations - 1, middle3, middle2, p3);

            return new Triangle(triangle1, triangle2, triangle3);
        }
    }

    private Coordinate calculateMidPoint(Coordinate p1, Coordinate p2) {
        int x = (p1.getX() + p2.getX()) / 2;
        int y = (p1.getY() + p2.getY()) / 2;
        return new Coordinate(x, y);
    }
}