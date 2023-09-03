import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class View extends JPanel {
    private int iterations = 1;

    public View() {
        setBackground(Color.BLACK);

        JButton drawButton = new JButton("Dibujar");
        JTextField iterationsField = new JTextField(5);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    iterations = Integer.parseInt(iterationsField.getText());
                    repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(View.this, "Ingrese un número válido.");
                }
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Iteraciones: "));
        controlPanel.add(iterationsField);
        controlPanel.add(drawButton);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = Math.min(getWidth(), getHeight()) - 20;
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        Point[] points = new Point[3];
        points[0] = new Point(x + size / 2, y);
        points[1] = new Point(x, y + size);
        points[2] = new Point(x + size, y + size);
        g.setColor(Color.WHITE);
        drawSierpinski(g, points, iterations);
    }

    private void drawSierpinski(Graphics g, Point[] points, int iterations) {
        if (iterations == 0) {
            int[] xPoints = new int[3];
            int[] yPoints = new int[3];

            for (int i = 0; i < 3; i++) {
                xPoints[i] = points[i].x;
                yPoints[i] = points[i].y;
            }

            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            Point[] midpoints = new Point[3];

            for (int i = 0; i < 3; i++) {
                int x1 = points[i].x;
                int y1 = points[i].y;
                int x2 = points[(i + 1) % 3].x;
                int y2 = points[(i + 1) % 3].y;

                midpoints[i] = new Point((x1 + x2) / 2, (y1 + y2) / 2);
            }

            drawSierpinski(g, new Point[]{points[0], midpoints[0], midpoints[2]}, iterations - 1);
            drawSierpinski(g, new Point[]{midpoints[0], points[1], midpoints[1]}, iterations - 1);
            drawSierpinski(g, new Point[]{midpoints[2], midpoints[1], points[2]}, iterations - 1);
        }
    }
}