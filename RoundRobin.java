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
        int tq = this.getTQ();
        
        while (!processes.isEmpty())
        {
            P p = processes.get(0);
            int bt = (p.getBT() < tq ? p.getBT() : tq);
            this.getLog().add(new Event(p.getName(), time, time + bt));
            time += bt;
            processes.remove(0);
            
            if (p.getBT() > tq)
            {
                p.setBT(p.getBT() - tq);
                
                for (int i = 0; i < processes.size(); i++)
                {
                    if (processes.get(i).getAT() > time)
                    {
                    		processes.add(i, p);
                        break;
                    }
                    else if (i == processes.size() - 1)
                    {
                    	processes.add(p);
                        break;
                    }
                }
            }
        }
        
        Map map = new HashMap();
        
        for (P p : this.processes)
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