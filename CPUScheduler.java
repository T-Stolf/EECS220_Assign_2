import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class CPUScheduler holds a list of the processes 
 * and a list of events.
 */

public abstract class CPUScheduler{
    public List<P> processes;   //list of processes see P class
    public List<Event> log;	   //see Event class
    public int tq;              //time quantum
    
    /**
     * Initializes two empty ArrayLists and sets the timeQuantum to 1
     */
    public CPUScheduler(){
    		processes = new ArrayList();
        log = new ArrayList();
        tq = 2;
    }
    
    /**
	 * Adds a process to processes
	 * @param p a process to add
	 */
    public boolean add(P p){
        return processes.add(p);
    }

    /**
   	 * Returns a List of the processes
   	 */
    public List<P> getProcesses(){
        return processes;
    }
    
    /**
  	 * Adds a process to processes
  	 * @param tq the new time quantum
  	 */
    public void setTQ(int tq){
        this.tq = tq;
    }
    
    /**
  	 * Returns the time quantum
  	 */
    public int getTQ(){
        return tq;
    }

    /**
  	 * Returns the Event
  	 * @param p a process
  	 */
    public Event getEvent(P p){
        for (Event e : log) {
            if (p.getName().equals(e.getName())) {
                return e;
            }
        }
		return null;
    }
    
    /**
  	 * Returns the process P
  	 * @param p a process name
  	 */
    public P getProcess(String process) {
        for (P p : processes)  {
            if (p.getName().equals(process))  {
                return p;
            }
        }
        return null;
    }

    /**
  	 * Returns the log of Events
  	 */
    public List<Event> getLog(){
        return log;
    }
    
    /**
  	 * Returns the average wait time
  	 */
    public double gawt(){
        double a = 0;
        for (P p : processes){
            a += p.getWT();
        }
        return a / processes.size();
    }
    
    /**
  	 * Returns the average turn around time
  	 */
    public double gatt(){
        double a = 0;
        for (P p : processes){
            a += p.getTT();
        }
        return a / processes.size();
    }
    
    public abstract void process();
}