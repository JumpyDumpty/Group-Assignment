package Strategy;

/**
 * Used to facilitate interactions between the Strategy interface and the client code.
 * Stores a strategy object and can make a call to it's execute() method.
 */
public class Context {
    private Strategy strategy;

    /**
     * Sets the strategy of this Context object
     * @param strat object implementing the Strategy interface
     */
    public void setStrategy(Strategy strat){
        this.strategy = strat;
    }

    /**
     * Executes the currently set strategy
     */
    public void executeStrategy(){
        this.strategy.execute();
    }
}
