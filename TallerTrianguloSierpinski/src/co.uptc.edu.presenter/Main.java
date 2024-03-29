import javax.swing.*;


public class Main{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Triángulo de Sierpinski");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new View());
            frame.pack();
            frame.setVisible(true);
        });
    }
}