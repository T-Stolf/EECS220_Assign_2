import java.util.ArrayList;
import java.util.List;

public abstract class CPUScheduler
{
    protected final List<P> processes;
    private final List<Event> timeline;
    private int timeQuantum;
    
    
    
    public CPUScheduler()
    {
    		processes = new ArrayList();
        timeline = new ArrayList();
        timeQuantum = 1;
    }
    
    public boolean add(P p)
    {
        return processes.add(p);
    }

    public List<P> getProcesses()
    {
        return processes;
    }
    public void setTimeQuantum(int timeQuantum)
    {
        this.timeQuantum = timeQuantum;
    }
    
    public int getTimeQuantum()
    {
        return timeQuantum;
    }

    
    public Event getEvent(P p)
    {
        for (Event event : timeline)
        {
            if (p.getName().equals(event.getProcessName()))
            {
                return event;
            }
        }
		return null;
    }
    public P getProcess(String process)
    {
        for (P p : processes)
        {
            if (p.getName().equals(process))
            {
                return p;
            }
        }
        
        return null;
}

    
    public List<Event> getTimeline()
    {
        return timeline;
    }
    public double getAverageWaitingTime()
    {
        double avg = 0.0;
        
        for (P p : processes)
        {
            avg += p.getWaitingTime();
        }
        
        return avg / processes.size();
    }
    
    public double getAverageTurnAroundTime()
    {
        double avg = 0.0;
        
        for (P p : processes)
        {
            avg += p.getTurnaroundTime();
        }
        
        return avg / processes.size();
    }
    
    public abstract void process();
}