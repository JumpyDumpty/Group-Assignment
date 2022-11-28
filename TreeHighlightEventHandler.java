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
public class TreeHighlightEventHandler implements EventHandler<MouseEvent> {

    private TreeViewer treeView; //the tree view
    private Context context;

    /**
     * Constructor
     * @param view reference to TreeView
     */
    public TreeHighlightEventHandler(TreeViewer view) {
        this.treeView = view;

        this.context = new Context();
        this.context.setStrategy(new Highlight(this.treeView));
    }

    /**
     * Executes the highlight strategy if event is a left mouse click
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