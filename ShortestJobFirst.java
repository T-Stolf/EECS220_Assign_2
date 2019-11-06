import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShortestJobFirst extends CPUScheduler
{
    @Override
    public void process()
    {
        Collections.sort(this.getProcesses(), (Object o1, Object o2) -> {
            if (((P) o1).getArrivalTime() == ((P) o2).getArrivalTime())
            {
                return 0;
            }
            else if (((P) o1).getArrivalTime() < ((P) o2).getArrivalTime())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        });
        
        List<P> processes = this.getProcesses();
        int time = processes.get(0).getArrivalTime();
        
        while (!processes.isEmpty())
        {
            List<P> availableRows = new ArrayList();
            
            for (P p : processes)
            {
                if (p.getArrivalTime() <= time)
                {
                    availableRows.add(p);
                }
            }
            
            Collections.sort(availableRows, (Object o1, Object o2) -> {
                if (((P) o1).getCPUTime() == ((P) o2).getCPUTime())
                {
                    return 0;
                }
                else if (((P) o1).getCPUTime() < ((P) o2).getCPUTime())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            });
            
            P p = availableRows.get(0);
            this.getTimeline().add(new Event(p.getName(), time, time + p.getCPUTime()));
            time += p.getCPUTime();
            
            for (int i = 0; i < processes.size(); i++)
            {
                if (processes.get(i).getName().equals(p.getName()))
                {
                	processes.remove(i);
                    break;
                }
            }
        }
        
        for (P p : this.getProcesses())
        {
            p.setWaitingTime(this.getEvent(p).getStartTime() - p.getArrivalTime());
            p.setTurnaroundTime(p.getWaitingTime() + p.getCPUTime());
        }
    }
}