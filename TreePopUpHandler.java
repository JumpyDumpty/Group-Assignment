import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import treeviz.MunicipalTree;
import javafx.scene.control.Alert;
import treeviz.TreeReader;
import treeviz.TreeWriter;

import java.util.List;
import java.util.*;

// this class will:
// 1. show TextInputDialog when create new tree is clicked
// 2. verify valid input of UNIT TYPE, diameter, long, lat

// 3. Add tree to treeViewer list field
// 4. Add tree to csv file
// 5. display alert messages

/**
 * TreePopUpHandler handles UI events captured via the TreeViewer
 */
public class TreePopUpHandler implements EventHandler<MouseEvent> {


    private TreeViewer treeView; //the tree view

    /**
     * Constructor
     *
     * @param view reference to TreeView
     */
    public TreePopUpHandler(TreeViewer view) {
        this.treeView = view;

    }
    private static boolean isNumericHelper(String a){
        if(a.matches("-?[0-9]+(\\.[0-9]+)?")){
            return true;
        }
        return false;
    }



    private static boolean aHandleHelper(String input){
        if (input != null && input.length() != 0 && input.contains(",")
        ) {
            String[] treeInfo = input.split(",");
            if (treeInfo.length != 5) { // 0 : untitype, 1: diam, 2: bot name, 3: lon , 4: lat
                return false;

            }
            else {
                if ((treeInfo[0].toUpperCase().equals("DECID") || treeInfo[0].toUpperCase().equals("CONFIR") ||
                        treeInfo[0].toUpperCase().equals("MULTI")) && (isNumericHelper(treeInfo[1])) &&
                    (isNumericHelper(treeInfo[3])) && (isNumericHelper(treeInfo[4]))
                ) {

                    return true;
                }
                else {
                    return false;
                }


            }

        }
        return false;
    }
    /**
     * Handle a mouse event (i.e. a button click)!  This routine will need to:
     * 1. Clear (or UNDO) all the circles from the existing view
     * 2. Cycle thru the list of trees and redraw circles that correspond to the tree type selected
     * 3. Remember to register any circles you draw on the "undo" stack, so they can be undone later!
     * 4. Remember also that if the user selects "ALL TREES", all the trees should be drawn
     *
     * @param event The mouse event
     */
    @Override
    public void handle(MouseEvent event) {

        // format : MULTI,50,Norway Maple,-79.64259,43.53124
        treeView.getTxtinput().showAndWait();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Invalid information given exit and try again");
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setContentText("Tree saved! Click Filter Tree List to view");
        String userInput = treeView.getTxtinput().getEditor().getText();
        if (aHandleHelper(userInput)) {
            String[] treeInfo = userInput.split(",");



                    String[] record = new String[6];
                    record[0] = treeInfo[0]; // DECID MULTI OR CONFIR
                    record[1] = treeInfo[1]; // diam
                    record[2] = "MISS"; // owner default "MISS"
                    record[3] = treeInfo[2]; //botanical name
                    record[4] = treeInfo[3]; // lon
                    record[5] = treeInfo[4]; // lat

                    treeView.getTrees().add(new MunicipalTree(record));
                    String filename = "treelist.csv";
                    // need : filename, String Unittype, String Diam, String Botname, String lon, String lat
                    TreeWriter.WriteNewTree(filename, treeInfo[0], treeInfo[1], treeInfo[2], treeInfo[3], treeInfo[4]);
                    alert2.show();



        }
        else {
            alert.show();

        }



    }
}



