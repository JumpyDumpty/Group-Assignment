import Strategy.Strategy;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import treeviz.MunicipalTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements highlighting trees with a specific name as a strategy
 */
public class Highlight implements Strategy {
    private ChoiceBox treeSelect; //select box ref
    private TextField txtSummary; //summary of tree counts
    private TreeViewer treeView; //the tree view
    private double llx, lly, urx, ury; //boundaries of the map
    private int height, width; //Height and width of the tree view

    /**
     * Constructor
     *
     * @param view the tree view
     */
    public Highlight(TreeViewer view) {
        this.treeView = view;
        this.treeSelect = view.getTreeSelect();
        this.txtSummary = view.getTxtSummary();

        double[] boundries = treeView.getBoundaries();
        this.llx = boundries[0];
        this.lly = boundries[1];
        this.urx = boundries[2];
        this.ury = boundries[3];
        this.height = treeView.getHeight();
        this.width = treeView.getWidth();
    }

    /**
     * Returns the coordinates of the tree on the map
     * @param tree MunicipalTree object
     * @return an array of two doubles representing the coordinates of the tree on the map
     */
    private double[] getTreeXYVals(MunicipalTree tree) {
        double[] coords;
        coords = tree.getLoc().getCoords();

        double[] vals = new double[2];
        vals[0] = (double) width - width * ((coords[0] - lly) / (ury - lly));
        vals[1] = (double) height - height * ((coords[1] - llx) / (urx - llx));

        return vals;
    }

    /**
     * Redraws all the non-selected trees gray and also returns
     * the selected trees
     *
     * @return ArrayList of selected trees
     */
    private ArrayList<MunicipalTree> drawNonSelected() {
        String selectedType = (String) treeSelect.getValue();

        List<MunicipalTree> trees = treeView.getTrees();
        ArrayList<MunicipalTree> selectedTrees = new ArrayList<>();

        while (!treeView.getUndoStack().isEmpty()) {
            Object circle = treeView.getUndoStack().pop();
            treeView.getAnchorRoot().getChildren().remove(circle);
        }

        for (MunicipalTree t : trees) {
            if (t.getName().equalsIgnoreCase(selectedType)) {
                selectedTrees.add(t);
            } else {
                double[] xYVals = getTreeXYVals(t);
                double xval = xYVals[0];
                double yval = xYVals[1];
                if (yval < height & yval > 0 & xval < width & xval > 0) {
                    Circle circle = new Circle();
                    circle.setCenterX(xval);
                    circle.setCenterY(yval);
                    circle.setRadius(3);
                    circle.setFill(Color.DARKGRAY);
                    treeView.getAnchorRoot().getChildren().add(circle); //attach each circle to the scene graph
                    treeView.getUndoStack().add(circle);
                }
            }
        }
        return selectedTrees;
    }

    /**
     * Method that draws the selected trees as red with a black perimeter
     * @param selectedTrees selected trees to be highlighted
     */
    private void drawSelected(ArrayList<MunicipalTree> selectedTrees) {
        int selectedNum = 0;
        for (MunicipalTree t : selectedTrees) {
            double[] xYVals = getTreeXYVals(t);
            double xval = xYVals[0];
            double yval = xYVals[1];
            if (yval < height & yval > 0 & xval < width & xval > 0) {
                Circle perimiter = new Circle();
                perimiter.setCenterX(xval);
                perimiter.setCenterY(yval);
                perimiter.setRadius(6);
                perimiter.setFill(Color.BLACK);
                Circle circle = new Circle();
                circle.setCenterX(xval);
                circle.setCenterY(yval);
                circle.setRadius(4);
                circle.setFill(Color.ORANGERED);
                treeView.getAnchorRoot().getChildren().add(perimiter);
                treeView.getAnchorRoot().getChildren().add(circle); //attach each circle to the scene graph
                treeView.getUndoStack().add(circle);
                treeView.getUndoStack().add(perimiter);
                selectedNum++;
            }
        }
        this.txtSummary.setText("Trees highlighted: " + selectedNum);
    }
    /**
     * 1. Clear the map of the trees using the undo stack
     * 2. Redraw the non-selected trees as red circles and selected
     * trees as green circles over top the previously drawn trees.
     */
    @Override
    public void execute () {
        ArrayList<MunicipalTree> selected = drawNonSelected();
        drawSelected(selected);
    }
}
