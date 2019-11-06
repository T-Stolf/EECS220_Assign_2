import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriorityPreemptive extends CPUScheduler
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
        
        List<P> rows = this.getProcesses();
        int time = rows.get(0).getArrivalTime();
        
        while (!rows.isEmpty())
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
                if (((P) o1).getPriority()== ((P) o2).getPriority())
                {
                    return 0;
                }
                else if (((P) o1).getPriority() < ((P) o2).getPriority())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            });
            
            P p = availableRows.get(0);
            this.getTimeline().add(new Event(p.getName(), time, ++time));
            p.setCPUTime(p.getCPUTime() - 1);
            
            if (p.getCPUTime() == 0)
            {
                for (int i = 0; i < rows.size(); i++)
                {
                    if (processes.get(i).getName().equals(p.getName()))
                    {
                        processes.remove(i);
                        break;
                    }
                }
            }
        }
        
        for (int i = this.getTimeline().size() - 1; i > 0; i--)
        {
            List<Event> timeline = this.getTimeline();
            
            if (timeline.get(i - 1).getProcessName().equals(timeline.get(i).getProcessName()))
            {
                timeline.get(i - 1).setFinishTime(timeline.get(i).getFinishTime());
                timeline.remove(i);
            }
        }
        
        Map map = new HashMap();
        
        for (P p : this.getProcesses())
        {
            map.clear();
            
            for (Event event : this.getTimeline())
            {
                if (event.getProcessName().equals(p.getName()))
                {
                    if (map.containsKey(event.getProcessName()))
                    {
                        int w = event.getStartTime() - (int) map.get(event.getProcessName());
                        p.setWaitingTime(p.getWaitingTime() + w);
                    }
                    else
                    {
                        p.setWaitingTime(event.getStartTime() - p.getArrivalTime());
                    }
                    
                    map.put(event.getProcessName(), event.getFinishTime());
                }
            }
            
            p.setTurnaroundTime(p.getWaitingTime() + p.getCPUTime());
        }
    }
}