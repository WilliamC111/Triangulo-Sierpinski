public class Presenter {
    private Sierpinski model;
    private View view;

    public Presenter(Sierpinski model, View view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        view.setVisible(true);
    }

    public void calculateSierpinski() {
        int iterations;
        try {
            iterations = Integer.parseInt(view.getIterationsFieldText());
        } catch (NumberFormatException e) {
            view.showErrorMessage("Ingrese un número válido de iteraciones.");
            return;
        }

        model.setIterations(iterations);
        view.repaintDrawingPanel();
    }

  
}