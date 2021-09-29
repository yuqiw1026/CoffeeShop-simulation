/**
 * A customer class stores objects of customer.
 *
 * @author (Yuqi wang)
 * @version (11/3/2020)
 */
import java.util.*;

public class Customer
{
    public int avgservingtime;
    public  Random r; 
    public int arrivetime;
    public int departtime;
    public int waittime;

    /**
     * Constructor for objects of class Customer
     */

    public Customer(int st1, int st2, Random random)
    {
        avgservingtime = (int)(random.nextDouble()*(st2+1-st1)+st1);
    }

    /**
     * A waittime method - calculate the waiting time of each customer.

     * @return - the calculated waiting time of each customer.
     */
    public int waittime(){
        waittime=departtime - arrivetime - avgservingtime;
        return waittime;
    }
}
