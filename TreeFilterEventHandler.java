import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import treeviz.MunicipalTree;

import java.util.Stack;

/**
 * TreeFilterEventHandler handles UI events captured via the TreeViewer
 */
public class TreeFilterEventHandler implements EventHandler<MouseEvent> {

    private ChoiceBox treeSelect; //select box ref
    private TextField txtSummary; //summary of tree counts
    private TreeViewer treeView; //the tree view

    /**
     * Constructor
     * @param view reference to TreeView
     */
    public TreeFilterEventHandler(TreeViewer view) {
        this.treeView = view;
        this.treeSelect = view.getTreeSelect();
        this.txtSummary = view.getTxtSummary();
    }

    /**
     * Handle a mouse event (i.e. a button click)!  This routine will need to:
     * 1. Clear (or UNDO) all the circles from the existing view
     * 2. Cycle thru the list of trees and redraw circles that correspond to the tree type selected
     * 3. Remember to register any circles you draw on the "undo" stack, so they can be undone later!
     * 4. Remember also that if the user selects "ALL TREES", all the trees should be drawn
     *
     * @param event  The mouse event
     */
    @Override
    public void handle(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Stack<Object> s = treeView.getUndoStack();
            AnchorPane a = treeView.getAnchorRoot();
            while(!s.empty()){
                Circle c = (Circle) s.pop();
                a.getChildren().remove(c);
            }
            int h = treeView.getHeight();
            int w = treeView.getWidth();
            double[] g = treeView.getBoundaries();
            if(treeSelect.getValue() == null){
                txtSummary.setText("Trees selected: " + s.size());
                return;
            }
            String selected = treeSelect.getValue().toString();
            System.out.println("tree: " + selected);
            double[] coords;
            for (MunicipalTree t: treeView.getTrees()) {
                coords = t.getLoc().getCoords();
                double yval = (double) h - h*((coords[1] - g[0])/(g[2] - g[0]));
                double xval = (double) w - w*((coords[0] - g[1])/(g[3] - g[1]));
                if (yval < h & yval > 0 & xval < w & xval > 0 & (selected.equals("ALL TREES") | t.getName().equals(selected))) {
                    System.out.println(t.getName());
                    Circle circle = new Circle();
                    circle.setCenterX(xval);
                    circle.setCenterY(yval);
                    circle.setRadius(3);
                    circle.setFill(Color.RED);
                    a.getChildren().add(circle); //attach each circle to the scene graph
                    s.add(circle); //add the circle to the undo stack
                }
            }
            txtSummary.setText("Trees selected: " + s.size());
        }
    }


}
