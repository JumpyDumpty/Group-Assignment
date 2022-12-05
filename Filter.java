import Strategy.Strategy;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import treeviz.MunicipalTree;

import java.util.List;

/**
 * A concrete Strategy to implement displaying the selected trees
 */
public class Filter implements Strategy {
    //parameters and comments copied from TreeFilterEventHandler class!
    private ChoiceBox treeSelect; //select box ref
    private TextField txtSummary; //summary of tree counts
    private TreeViewer treeView; //the tree view

    /**
     * Constructor
     * @param view the tree view
     */
    public Filter(TreeViewer view){
        this.treeView = view;
        this.treeSelect = view.getTreeSelect();
        this.txtSummary = view.getTxtSummary();

    }
    /**
     * Copied from the handle() method within the TreeFilterEventHandler class!
     * 1. Clear (or UNDO) all the circles from the existing view
     * 2. Cycle thru the list of trees and redraw circles that correspond to the tree type selected
     * 3. Remember to register any circles you draw on the "undo" stack, so they can be undone later!
     * 4. Remember also that if the user selects "ALL TREES", all the trees should be drawn
     */
    @Override
    public void execute() {
        String selectedType = (String) treeSelect.getValue();
        List<MunicipalTree> trees = treeView.getTrees();
        while (!treeView.getUndoStack().isEmpty()){
            Object circle = treeView.getUndoStack().pop();
            treeView.getAnchorRoot().getChildren().remove(circle);
        }
        double[] boundries = treeView.getBoundaries();
        double llx = boundries[0];
        double lly = boundries[1];
        double urx = boundries[2];
        double ury = boundries[3];
        int height = treeView.getHeight();
        int width = treeView.getWidth();
        double[] coords;
        for (MunicipalTree t: trees){
            if (selectedType == null || t.getName().equalsIgnoreCase(selectedType) ||
                    selectedType.equalsIgnoreCase("ALL TREES")){
                coords = t.getLoc().getCoords();
                double yval = (double) height - height*((coords[1] - llx)/(urx - llx));
                double xval = (double) width - width*((coords[0] - lly)/(ury - lly));
                if (yval < height & yval > 0 & xval < width & xval > 0){
                    Circle circle = new Circle();
                    circle.setCenterX(xval);
                    circle.setCenterY(yval);
                    circle.setRadius(3);
                    circle.setFill(Color.RED);
                    treeView.getAnchorRoot().getChildren().add(circle); //attach each circle to the scene graph
                    treeView.getUndoStack().add(circle);
                }
            }
        }
        this.txtSummary.setText("Trees selected: " + treeView.getUndoStack().size());
    }

}
