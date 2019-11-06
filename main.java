import java.util.List;

public class main {
	  public static void main(String[] args) {
		  fcfs();
		  sjf();
		  psn();
		  psp();
		  rr();
	  }
	    public static void fcfs()
	    {
	        CPUScheduler fcfs = new FirstComeFirstServe();
	        fcfs.add(new P("P1", 0, 5, 0));
	        fcfs.add(new P("P2", 2, 4, 0));
	        fcfs.add(new P("P3", 4, 3, 0));
	        fcfs.add(new P("P4", 6, 6, 0));
	        fcfs.add(new P("P5", 3, 6, 0));
	        fcfs.process();
	        display(fcfs);
	}
	   
	    public static void psp()
	    {
	        CPUScheduler psp = new PriorityPreemptive();
	        psp.add(new P("P1", 0, 1, 7));
	        psp.add(new P("P2", 0, 1, 10));
	        psp.add(new P("P3", 0, 7, 3));
	        psp.add(new P("P4", 0, 3, 8));
	        psp.add(new P("P5", 0, 8, 7));
	        psp.add(new P("P6", 0, 2, 1));
	        psp.add(new P("P7", 0, 5, 2));
	        psp.process();
	        display(psp);
	}
	   public static void sjf()
	    {
	        CPUScheduler sjf = new ShortestJobFirst();
	        sjf.add(new P("P1", 0, 5, 0));
	        sjf.add(new P("P2", 0, 3, 0));
	        sjf.add(new P("P3", 6, 2, 0));
	        sjf.add(new P("P4", 0, 4, 0));
	        sjf.add(new P("P5", 0, 1, 0));
	        sjf.process();
	        display(sjf);
	}
	   public static void psn()
	    {
	        CPUScheduler psn = new PriorityNonPreemptive();
	        psn.add(new P("P1", 0, 1, 5));
	        psn.add(new P("P2", 0, 1, 4));
	        psn.add(new P("P3", 0, 7, 3));
	        psn.add(new P("P4", 0, 3, 7));
	        psn.add(new P("P5", 0, 8, 3));
	        psn.add(new P("P6", 0, 2, 1));
	        psn.add(new P("P7", 0, 5, 2));
	        psn.process();
	        display(psn);
	    }
	   
	   public static void rr()
	    {
	        CPUScheduler rr = new RoundRobin();
	        rr.setTimeQuantum(2);
	        rr.add(new P("P1", 0, 4, 0));
	        rr.add(new P("P2", 1, 5, 0));
	        rr.add(new P("P3", 2, 6, 0));
	        rr.add(new P("P4", 4, 1, 0));
	        rr.add(new P("P5", 6, 3, 0));
	        rr.add(new P("P6", 7, 2, 0));
	        rr.process();
	        display(rr);
	    }
	    public static void display(CPUScheduler object)
	    {
	     
	        
	        System.out.println();
	        
	        for (int i = 0; i < object.getTimeline().size(); i++)
	        {
	            List<Event> timeline = object.getTimeline();
	            System.out.print(timeline.get(i).getStartTime() + "(" + timeline.get(i).getProcessName() + ")");
	            
	            if (i == object.getTimeline().size() - 1)
	            {
	                System.out.print(timeline.get(i).getFinishTime());
	            }
	        }
	        System.out.println("\n");
	        
	}
}
