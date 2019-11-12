import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreemptivePriority extends CPUScheduler
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

            P p = availableProcesses.get(0);
            this.getLog().add(new Event(p.getName(), time, ++time));
            p.setBT(p.getBT() - 1);
            
            if (p.getBT() == 0)
            {
                for (int i = 0; i < processes.size(); i++)
                {
                    if (processes.get(i).getName().equals(p.getName()))
                    {
                        processes.remove(i);
                        break;
                    }
                }
            }
        }
        
        for (int i = this.getLog().size() - 1; i > 0; i--)
        {
            List<Event> timeline = this.getLog();
            
            if (timeline.get(i - 1).getName().equals(timeline.get(i).getName()))
            {
                timeline.get(i - 1).setFT(timeline.get(i).getFT());
                timeline.remove(i);
            }
        }
        
        Map map = new HashMap();
        
        for (P p : this.getProcesses())
        {
            map.clear();
            
            for (Event e : this.getLog())
            {
                if (e.getName().equals(p.getName()))
                {
                    if (map.containsKey(e.getName()))
                    {
                        int w = e.getST() - (int) map.get(e.getName());
                        p.setWT(p.getWT() + w);
                    }
                    else
                    {
                        p.setWT(e.getST() - p.getAT());
                    }
                    
                    map.put(e.getName(), e.getFT());
                }
            }
            
            p.setTT(p.getWT() + p.getBT());
        }
    }
}