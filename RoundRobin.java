import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundRobin extends CPUScheduler
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
        int timeQuantum = this.getTimeQuantum();
        
        while (!rows.isEmpty())
        {
            P p = processes.get(0);
            int bt = (p.getCPUTime() < timeQuantum ? p.getCPUTime() : timeQuantum);
            this.getTimeline().add(new Event(p.getName(), time, time + bt));
            time += bt;
            rows.remove(0);
            
            if (p.getCPUTime() > timeQuantum)
            {
                p.setCPUTime(p.getCPUTime() - timeQuantum);
                
                for (int i = 0; i < rows.size(); i++)
                {
                    if (rows.get(i).getArrivalTime() > time)
                    {
                        rows.add(i, p);
                        break;
                    }
                    else if (i == rows.size() - 1)
                    {
                        rows.add(p);
                        break;
                    }
                }
            }
        }
        
        Map map = new HashMap();
        
        for (P p : this.processes)
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