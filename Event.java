/**
 * This class Event stores the name, start time, and finish time
 * of a P process
 */

public class Event
{
    public String name;  //name of process
    public int ST;		//start time of process
    public int FT;		//finish time of process
    
    /**
     * Initializes an Event with specified name, start time and finish time
     */
    public Event(String name, int ST, int FT) {
        this.name = name;
        this.ST = ST;
        this.FT = FT;
    }
   
    /**
   	 * Returns a the name of the processes
   	 */
    public String getName()  {
        return name;
    }
    
    /**
   	 * Returns the start time of the processes
   	 */
    public int getST(){
        return ST;
    }
    
    /**
   	 * Returns the finish time of the processes
   	 */
    public int getFT(){
        return FT;
    }
    
    /**
   	 * Sets the finish time of the processes
   	 * @param FT a finish time to set
   	 */
    public void setFT(int FT){
        this.FT = FT;
    }
}