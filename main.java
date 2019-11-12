import java.util.List;

public class main {
	  public static void main(String[] args) {
	      System.out.println("-----First Come First Serve-------");
		  fcfs();
		  
		  System.out.println("-----Shortest Job First------");
		  sjf();
	
		  
		  System.out.println("-----Shortest Remaining Time------");
		  srt();
		  
		  System.out.println("-------Nonpreemptive Priority------");
		  np();
		  
		  System.out.println("-----Preemptive Priority------");
		  pp();
		  
		  System.out.println("----Round Robin-------");
		  rr();
		  
	  }
	    public static void fcfs()
	    {
	        CPUScheduler fcfs = new FirstComeFirstServe();
	        fcfs.add(new P("P1", 2, 4, 0));
	        fcfs.add(new P("P2", 1, 6, 0));
	        fcfs.add(new P("P3", 1, 2, 0));
	        fcfs.add(new P("P4", 1, 9, 0));
	        fcfs.add(new P("P5", 1, 12, 0));
	        fcfs.process();
	        display(fcfs);
	}
	    public static void sjf()
	    {
	        CPUScheduler sjf = new ShortestJobFirst();
	        sjf.add(new P("P1", 1, 21, 0));
	        sjf.add(new P("P2", 2, 3, 0));
	        sjf.add(new P("P3", 2, 6, 0));
	        sjf.add(new P("P4", 4, 2, 0));
	  
	        sjf.process();
	        display(sjf);
	        
	       
	    }
	    
	    public static void srt()
	    {
	        CPUScheduler srt = new ShortestRemainingTime();
	        srt.add(new P("P1", 1, 21, 0));
	        srt.add(new P("P2", 2, 3, 0));
	        srt.add(new P("P3", 2, 6, 0));
	        srt.add(new P("P4", 4, 2, 0));
	 
	        


	        srt.process();
	        display(srt);
	    }
	    
	    public static void np()
	    {
	        CPUScheduler np = new NonPreemptivePriority();
	        np.add(new P("P1", 0, 3, 2));
	        np.add(new P("P2", 2, 5, 6));
	        np.add(new P("P3", 1, 4, 3));
	        np.add(new P("P4", 4, 2, 5));
	        np.add(new P("P5", 6, 9, 7));
	        np.add(new P("P6", 5, 4, 4));
	        np.add(new P("P6", 7, 10, 10));
	        np.process();
	        display(np);
	    }
	    public static void pp()
	    {
	        CPUScheduler pp = new PreemptivePriority();
	        pp.add(new P("P1", 0, 1, 2));
	        pp.add(new P("P2", 1, 7, 6));
	        pp.add(new P("P3", 2, 3, 3));
	        pp.add(new P("P4", 3, 6, 5));
	        pp.add(new P("P5", 4, 5, 4));
	        pp.add(new P("P6", 5, 15, 10));
	        pp.add(new P("P7", 6, 8, 9));
	        pp.process();
	      
	        display(pp);
	}
	
	
	  
	   
	   public static void rr()
	    {
	        CPUScheduler rr = new RoundRobin();
	        rr.setTQ(2);
	        rr.add(new P("P1", 0, 8, 4));
	        rr.add(new P("P2", 1, 1, 5));
	        rr.add(new P("P3", 2, 3, 2));
	        rr.add(new P("P4", 3, 2, 1));
	        rr.add(new P("P5", 4, 6, 6));
	        rr.add(new P("P6", 6, 6, 3));
	      
	        rr.process();
	        display(rr);
	    }
	    public static void display(CPUScheduler o)
	    {

	        System.out.println("Process\tAT\tBT\tWT\tTT");

	        for (P p : o.getProcesses())
	        {
	            System.out.println(p.getName() + "\t" + p.getAT() + "\t" + p.getBT() + "\t" + p.getWT() + "\t" + p.getTT());
	        }
	        
	        System.out.println();
	        

	        for (int i = 0; i < o.getLog().size(); i++)
	        {
	            List<Event> log = o.getLog();
	            System.out.print(log.get(i).getST() + "(" + log.get(i).getName() + ")");
	            
	            if (i == o.getLog().size() - 1)
	            {
	                System.out.print(log.get(i).getFT());
	            }
	        }
	        
	        System.out.println("\n\nAverage WT: " + o.gawt() + "\nAverage TT: " + o.gatt() +"\n");
	    }
}
