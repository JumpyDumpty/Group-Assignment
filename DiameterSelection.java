public class DiameterSelection implements Command{
    //Nov 18 log: up the empty classes and interface
    //Nov 24 log: Implement DiameterSelection
    private TreeViewer t;
    private double d1;
    private double d2;
    public DiameterSelection(TreeViewer t, double min, double max){
        this.t = t;
        this.d1 = min;
        this.d2 = max;
    }
    public TreeViewer getTreeViewer(){
        return t;
    }
    public double getMinimum(){
        System.out.println("Minimum is "+ d1);
        return d1;
    }
    public double getMaximum(){
        System.out.println("Maximum is "+ d2);
        return d2;
    }
    public void execute(){
        Display.exe(this);
    }
}

