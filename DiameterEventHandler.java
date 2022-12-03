import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
public class DiameterEventHandler implements EventHandler<MouseEvent>{
    //Nov 18 log: up the empty classes and interface
    //Nov 21 log: Implemented Command and DiameterEventHandler
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

