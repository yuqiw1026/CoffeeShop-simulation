/**
 * An cashier class stores objects of cashier.
 *
 * @author (Yuqi wang)
 * @version (11/3/2020)
 */
import java.util.*;

public class Cashier
{
    public double profit;
    public double cost;

    /**
     * Constructor for objects of class Cashier
     */
    public Cashier(double c)
    {
        cost = c;
    }

    /**
     * A serve method - calculate the profit of serving customer.
     * @param  p1 - a double number which will read from an input file.
     * @param  p2 - a double number which will read from an input file.
     * @param  random - used the random number generator.

     * @return -returning the calculated profit.
     */

    public double serve(double p1, double p2, Random random){
        profit = random.nextDouble()*(p2-p1)+p1;
        return profit;

    }

}
