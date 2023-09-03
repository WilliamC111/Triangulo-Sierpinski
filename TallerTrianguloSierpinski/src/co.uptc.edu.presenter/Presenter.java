import java.awt.Color;
import java.awt.Graphics;

public class Presenter {
    private Sierpinski model;
    private View view;
    private int width, height;

    public Presenter(Sierpinski model, View view) {
        this.model = model;
        this.view = view;
    }

    public void drawSierpinski(int depth) {
        model.setDepth(depth);
        view.repaint();
    }

    public void paintSierpinski(Graphics g) {
        width = view.getWidth();
        height = view.getHeight();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);

        int[] xPoints = {width / 2, 0, width};
        int[] yPoints = {0, height, height};
        drawSierpinskiTriangle(g, model.getDepth(), xPoints, yPoints);
    }

    private void drawSierpinskiTriangle(Graphics g, int depth, int[] xPoints, int[] yPoints) {
        if (depth == 0) {
            g.drawPolygon(xPoints, yPoints, 3);
        } else {
            int[] midX = new int[3];
            int[] midY = new int[3];

            for (int i = 0; i < 3; i++) {
                midX[i] = (xPoints[i] + xPoints[(i + 1) % 3]) / 2;
                midY[i] = (yPoints[i] + yPoints[(i + 1) % 3]) / 2;
            }

            drawSierpinskiTriangle(g, depth - 1, xPoints, midY);
            drawSierpinskiTriangle(g, depth - 1, midX, yPoints);
            drawSierpinskiTriangle(g, depth - 1, midX, midY);
        }
    }
} 
    
