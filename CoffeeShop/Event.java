/**
 * An abstract event class generally represents two type of events-arrive and depart.
 *
 * @author (Yuqi wang)
 * @version (11/3/2020)
 */
public abstract class Event implements Comparable<Event>
{
//the two attribute that a event can have is time and type(depart / arrive)
    public Integer time;
    public String type="";

    /**
     * Constructor for objects of class Event
     */
    public Event( )
    {

    }
//making the events can be sorted when putting into the priority queue by making their time comparable
    /**
     * A compareTo method - compare the time of event obejcts
     * @param  e  a event of Event class
     * @return   int to show which one has the priority
     */
    public int compareTo(Event e){
        return this.time.compareTo(e.time);
    }

}
