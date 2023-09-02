import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private Sierpinski model;
    private JPanel drawingPanel;
    private JTextField iterationsField;
    private JButton drawButton;
    private int currentIterations = 0;

    public View(Sierpinski model) {
        this.model = model;
        setTitle("Triángulo de Sierpinski");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel controlPanel = new JPanel();
        iterationsField = new JTextField(5);
        drawButton = new JButton("Dibujar");
        controlPanel.add(new JLabel("Iteraciones:"));
        controlPanel.add(iterationsField);
        controlPanel.add(drawButton);

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int iterationsToDraw = currentIterations;
                Coordinate p1 = new Coordinate(400, 50);
                Coordinate p2 = new Coordinate(100, 650);
                Coordinate p3 = new Coordinate(700, 650);

                Triangle sierpinskiTriangle = model.calculateSierpinskiTriangle(0, p1, p2, p3);
                drawTriangle(g, sierpinskiTriangle);

            
                for (int i = 1; i <= iterationsToDraw; i++) {
                    sierpinskiTriangle = model.calculateSierpinskiTriangle(i, p1, p2, p3);
                    drawTriangle(g, sierpinskiTriangle);
                    p1 = sierpinskiTriangle.getVertex1();
                    p2 = sierpinskiTriangle.getVertex2();
                    p3 = sierpinskiTriangle.getVertex3();
                }
            }

            private void drawTriangle(Graphics g, Triangle triangle) {
                if (triangle != null) {
                    Coordinate p1 = triangle.getVertex1();
                    Coordinate p2 = triangle.getVertex2();
                    Coordinate p3 = triangle.getVertex3();

                    if (p1 != null && p2 != null && p3 != null) {
                        int[] xPoints = {p1.getX(), p2.getX(), p3.getX()};
                        int[] yPoints = {p1.getY(), p2.getY(), p3.getY()};
                        g.drawPolygon(xPoints, yPoints, 3);
                    }
                }
            }
        };

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int iterations = Integer.parseInt(iterationsField.getText());
                    currentIterations = iterations;
                    repaintDrawingPanel();
                } catch (NumberFormatException ex) {
                    showErrorMessage("Ingrese un número válido de iteraciones.");
                }
            }
        });

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
    }

    public String getIterationsFieldText() {
        return iterationsField.getText();
    }

    public void repaintDrawingPanel() {
        drawingPanel.repaint();
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        Sierpinski model = new Sierpinski();
        View view = new View(model);
        Presenter presenter = new Presenter(model, view);
        presenter.start();
    }
}