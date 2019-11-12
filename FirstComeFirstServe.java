import java.util.Collections;
import java.util.List;


public class FirstComeFirstServe extends CPUScheduler
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
		        
		        List<Event> log = this.getLog();
	        
	        for (P p : this.getProcesses())
	        {
	            if (log.isEmpty())
	            {
	                log.add(new Event(p.getName(), p.getAT(), p.getAT() + p.getBT()));
	            }
	            else
	            {
	                Event e = log.get(log.size() - 1);
	                log.add(new Event(p.getName(), e.getFT(), e.getFT() + p.getBT()));
	            }
	        }

	        for (P p : this.getProcesses())
	        {
	            p.setWT(this.getEvent(p).getST() - p.getAT());
	            p.setTT(p.getWT() + p.getBT());
	        }
	    }
	}

