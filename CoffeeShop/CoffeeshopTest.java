
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.File;

/**
 * The test class CoffeeshopTest.

 * @author (Yuqi wang)
 * @version (11/7/2020)
 */
public class CoffeeshopTest
{
    /**
     * Default constructor for test class CoffeeshopTest
     */
    public CoffeeshopTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void test()
    {
        PriorityQueue<Event> eventSet=new PriorityQueue<Event>();
        Coffeeshop  cs = new Coffeeshop();
        //manully add the arrival events
        Event a1=new Arrive(6*3600);
        eventSet.add(a1);
        Event a2=new Arrive(7*3600);
        eventSet.add(a2);
        //set number of cashier hired to be 0, thus overflow number will be the total number of events, which is 2
        cs.run(eventSet,0,0,0,0,0,0);
        //no net profit because total profit and total cost are all 0
        assertEquals(cs.netprofit,(float)0,0.0001);
        assertEquals(cs.tprofit,(float)0,0.0001);
        assertEquals(cs.netprofit,(float)0,0.0001);
        assertEquals(cs.numberofoverflow,2);

    }
}
