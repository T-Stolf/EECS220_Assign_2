
public class P
{
	private String name;
    private int arrivalTime;
    private int CPUTime;
    private int IOTime;
    private int priority;
    private int turnaroundTime;
    private int waitingTime;
    
    public P(String name, int arrivalTime, int CPUTime, int priority)
    {
    	this.name = name;
        this.arrivalTime = arrivalTime;
        this.CPUTime = CPUTime;
        this.priority = priority;
    }
    public void setCPUTime(int CPUTime)
    {
        this.CPUTime = CPUTime;
    }
  
    
    public void setIOTime(int IOTime)
    {
        this.IOTime = IOTime;
    }
  
    public void setWaitingTime(int waitingTime)
    {
        this.waitingTime = waitingTime;
    }
    
    public void setTurnaroundTime(int turnaroundTime)
    {
        this.turnaroundTime = turnaroundTime;
    }
    
    public int getArrivalTime()
    {
    	return this.arrivalTime;
    }
    public String getName()
    {
    	return this.name;
    }
    
    public int getCPUTime()
    {
    	return this.CPUTime;
    }
    
    public int getIOTime()
    {
    	return this.IOTime;
    }
    
    public int getPriority()
    {
        return this.priority;
    }
    
    public int getWaitingTime()
    {
        return this.waitingTime;
    }
    
    public int getTurnaroundTime()
    {
        return this.turnaroundTime;
    }
}