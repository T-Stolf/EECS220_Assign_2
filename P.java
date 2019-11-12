/**
 * This class is used to represent each process

 */

public class P
{
	public String name;
	public int AT;
	public int BT;
	public int priority;
	public int TT;
	public int WT;
    
	/**
     * Initializes a new process P 
     * @param n name, at arrival time, CPUTime CPU Time, p priority
     */
    public P(String n, int at, int bt, int p){
    		this.name = n;
        this.AT = at;
        this.BT = bt;
        this.priority = p;
    }
    
    public void setBT(int bt){
        this.BT = bt;
    }
  
  
    public void setWT(int WT) {
        this.WT = WT;
    }
    
    public void setTT(int TT){
        this.TT = TT;
    }
    
    /**
  	 * Returns the arrival time of the process 
  	 */
    public int getAT(){
    	return this.AT;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public int getBT(){
    	return this.BT;
    }
  
    public int getPriority() {
        return this.priority;
    }
    
    public int getWT(){
        return this.WT;
    }
    
    public int getTT() {
        return this.TT;
    }
}