import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestJobFirst extends CPUScheduler
{
    @Override
    public void process()
    {
        Collections.sort(this.getProcesses(), (Object o1, Object o2) -> {
            if (((P) o1).getAT() == ((P) o2).getAT())
            {
                return 0;
            }
            else if (((P) o1).getAT() < ((P) o2).getAT())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });
        
        
        List<P> processes = Utility.deepCopy(this.getProcesses());
        int time = processes.get(0).getAT();
        
        
        
        while (!processes.isEmpty())
        {
            List<P> availableProcesses = new ArrayList();
            
            for (P p : processes)
            {
                if (p.getAT() <= time)
                {
                	availableProcesses.add(p);
                }
            }
            
            Collections.sort(availableProcesses, (Object o1, Object o2) -> {
                if (((P) o1).getBT() == ((P) o2).getBT())
                {
                    return 0;
                }
                else if (((P) o1).getBT() < ((P) o2).getBT())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            });
            
            P p = availableProcesses.get(0);
            this.getLog().add(new Event(p.getName(), time, time + p.getBT()));
            time += p.getBT();
            
            for (int i = 0; i < processes.size(); i++)
            {
            	
                if (processes.get(i).getName().equals(p.getName()))
                {
                	processes.remove(i);
                    break;
                }
            }
            
        }
        List<Event> log = this.getLog();
        for (P p : this.getProcesses())
        {
            if (log.isEmpty())
            {
                log.add(new Event(p.getName(), p.getAT(), p.getAT() + p.getBT()));
            }
            else
            {
                Event event = log.get(log.size() - 1);
                log.add(new Event(p.getName(), event.getFT(), event.getFT() + p.getBT()));
            }
        }
        
        for (P p : this.getProcesses())
        {
       
            p.setWT(this.getEvent(p).getST() - p.getAT());
            p.setTT(p.getWT() + p.getBT());
        }
    }
}