public class Triangle {
    private Coordinate vertex1;
    private Coordinate vertex2;
    private Coordinate vertex3;

    public Triangle(Coordinate vertex1, Coordinate vertex2, Coordinate vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }

    public Triangle(Triangle triangle1, Triangle triangle2, Triangle triangle3) {
        this.vertex1 = triangle1.getVertex1();
        this.vertex2 = triangle2.getVertex2();
        this.vertex3 = triangle3.getVertex3();
    }

    public Coordinate getVertex1() {
        return vertex1;
    }

    public Coordinate getVertex2() {
        return vertex2;
    }

    public Coordinate getVertex3() {
        return vertex3;
    }
}