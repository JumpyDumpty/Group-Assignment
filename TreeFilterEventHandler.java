import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import treeviz.MunicipalTree;
import Strategy.Context;


import java.util.*;

/**
 * TreeFilterEventHandler handles UI events captured via the TreeViewer
 */
public class TreeFilterEventHandler implements EventHandler<MouseEvent> {

    private TreeViewer treeView; //the tree view
    private Context context; //holds the strategy for filtering trees

    /**
     * Constructor
     * @param view reference to TreeView
     */
    public TreeFilterEventHandler(TreeViewer view) {
        this.treeView = view;

        //Strategy initialization;
        this.context = new Context();
        this.context.setStrategy(new Filter(this.treeView));
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
            context.executeStrategy();
        }
    }
}