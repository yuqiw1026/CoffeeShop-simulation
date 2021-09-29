/**
 * An arrive class stores objects of arrive event
 *
 * @author (Yuqi wang)
 * @version (11/3/2020)
 */
public class Arrive extends Event
{
    /**
     * Constructor for objects of class arrive
     */
    public Arrive(int t)
    {
        type="arrive";
        time=t;
    }

}
