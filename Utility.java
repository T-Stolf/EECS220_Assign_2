import java.util.ArrayList;
import java.util.List;

public class Utility
{
    public static List<P> deepCopy(List<P> oldList)
    {
        List<P> newList = new ArrayList();
        
        for (P p : oldList)
        {
            newList.add(new P(p.getName(), p.getAT(), p.getBT(), p.getPriority()));
        }
        
        return newList;
    }
}