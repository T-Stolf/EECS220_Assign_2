import java.util.Collections;
import java.util.List;

public class FirstComeFirstServe  extends CPUScheduler{

	        
	      
	        
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
		        
		        List<Event> timeline = this.getTimeline();
	        
	        for (P p : this.getProcesses())
	        {
	            if (timeline.isEmpty())
	            {
	                timeline.add(new Event(p.getName(), p.getArrivalTime(), p.getArrivalTime() + p.getCPUTime()));
	            }
	            else
	            {
	                Event event = timeline.get(timeline.size() - 1);
	                timeline.add(new Event(p.getName(), event.getFinishTime(), event.getFinishTime() + p.getCPUTime()));
	            }
	        }
	        
	        for (P p : this.getProcesses())
	        {
	            p.setWaitingTime(this.getEvent(p).getStartTime() - p.getArrivalTime());
	            p.setTurnaroundTime(p.getWaitingTime() + p.getCPUTime());
	        }
	    }
	}

