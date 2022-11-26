import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class DiameterEventHandler implements EventHandler<MouseEvent>{
    private TreeViewer treeView;
    public DiameterEventHandler(TreeViewer t){
        this.treeView = t;
    }
    public void handle(MouseEvent e){
        try{
            double mi = Double.parseDouble(treeView.getminimum().getText());
            double ma = Double.parseDouble(treeView.getmaximum().getText());
            if(mi > ma){
                DiameterSelection d = new DiameterSelection(treeView, ma, mi);
                d.execute();
            }
            else{
                DiameterSelection d = new DiameterSelection(treeView, mi, ma);
                d.execute();
            }
        }catch(NumberFormatException u){
            treeView.inputError.setText("Please use valid input|");
        }


    }
}
