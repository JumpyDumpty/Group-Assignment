import javafx.scene.control.Label;
import treeviz.MunicipalTree;

import java.util.List;

public class Display {
    //Nov 18 log: up the empty classes and interface
    //Nov 27 log: Implement Display
    public static void exe(DiameterSelection c){
        int treeNum = 0;
        List<MunicipalTree> trees = c.getTreeViewer().getTrees();
        for (MunicipalTree t: trees){
            if(t.getDiameter() <= c.getMaximum() & t.getDiameter() >= c.getMinimum()){
                treeNum ++;
            }
        }
        c.getTreeViewer().TreeNum.setText("There are currently "+ treeNum + " trees within the range.");
    }
}

