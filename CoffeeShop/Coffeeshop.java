
/**
 * A coffee shop class simulate the operation process, including mimic the operation time of the coffee shop, customer wait in line, the overflow condition of the coffee shop  
 *
 * @author (Yuqi wang)
 * @version (11/3/2020)
 */
import java.util.*;
import java.io.File;

public class Coffeeshop {
    //initialize the openning and close time of the coffee shop
    public int opentime=6*3600;
    public int closetime=21*3600;
    //This will be used to create new event later(depart event)
    public Event e;

    public Queue<Customer> waitingline; 
    public ArrayList<Integer> waittime;

    public Random r; 
    //the total profit earned and total cost 
    public double tprofit;
    public double tcost;

    public int availableCashier; 
    public int numberofoverflow;
    public Cashier ca;
    public int numberofcashier;
    //the net profit is calculated at the end by subtracting the total cost from total profit
    public float netprofit;
    //used to calculate the average waiting time and rate of overflow
    public int totalnumarrive;

    /**
     * Constructor for objects of class Coffeeshop
     */
    public Coffeeshop( ) { 
        tprofit=0;
        numberofoverflow=0;
        totalnumarrive=0;
        waittime=new ArrayList<Integer>();
        waitingline = new LinkedList<Customer>();
    }

    /**
     * An run method - Run the simulation of coffee shop
     * @param  eventset- a list of event that will pending in order of time
     * @param  numberofcashier- number of cashier staffed at the shop
     * @param  p1,p2- upper bound and lower bound of profit earned by each cashie
     * @param  c- the cost paid to each cashie for a whole day
     * @param  st1,st2- upper bound and lower bound of serving time of each customer
     */
    // Run the simulation.
    public void run(PriorityQueue<Event> eventSet,int s, double p1, double p2,double c, int st1, int st2){
        //total number of customer enter the shop is number of arrival events in the input file
        totalnumarrive = eventSet.size();

        Random random=new Random(System.currentTimeMillis());
        //initially the available cashier is equal to number of cashier hired, and then will be updated when customer are being served
        numberofcashier = s;
        availableCashier = numberofcashier;

        ca = new Cashier(c);

        //the next event will be pending if there are still events in the set
        while( !eventSet.isEmpty())
        {
            //remove the first event in the pqueue and stored in a temp variable
            e=eventSet.remove();
            //check if the event is happed with in the operation time of the coffee shop
            if(e.time>=opentime&&e.time<=closetime){
                //catergory the event(whether is arrive or depart)
                if(e.type.equals("arrive")){

                    Customer cu=new Customer(st1,st2,random);
                    cu.arrivetime=e.time;
                    //check whether there is an overflow
                    if(waitingline.size()>=8*numberofcashier){
                        //if so turned away the customer
                        numberofoverflow++;
                        //System.out.println(1);
                    }
                    //no overflow, so customer can join the waiting line
                    else{
                        waitingline.add(cu);
                        //check whether the customer can be served
                        if (availableCashier > 0 ){
                            waitingline.remove();
                            //update the total profit
                            tprofit+=ca.serve(p1,p2,random);
                            availableCashier--;
                            int departtime=e.time+cu.avgservingtime;
                            cu.departtime=departtime;
                            //after the custiomer's order is finished, the customer can depart
                            //add the depart event into the set
                            e=new Depart(departtime);
                            waittime.add(cu.waittime());
                            eventSet.add(e);
                        }

                    }
                }
                else{ 
                    if (waitingline.isEmpty()){
                        availableCashier++;
                    } 
                    //if there is customer in waiting line, served he/she immediately after the former customer depart
                    else {
                        //get the new customer out of the waiting line and serve he/she
                        Customer cu = waitingline.remove();
                        tprofit+=ca.serve(p1,p2,random);
                        int departtime=e.time+cu.avgservingtime;
                        //the departure time of the new customer
                        cu.departtime=departtime;
                        waittime.add(cu.waittime());
                        e=new Depart(departtime);
                        eventSet.add(e);
                    }
                }
            }
            //calculate the total cost of the day,which is equal to number of cashier hired times the fixed salary
            tcost=numberofcashier*ca.cost;
            //calculate the net profit cost of the day
            netprofit=(float)(tprofit-tcost);

        }

    }
}

