/**
 * An experiment controller class reads the input file and run for different trails, and finally print out to collect required data.
 *
 * @author (Yuqi wang)
 * @version (11/9/2020)
 */
import java.util.*;
import java.io.File;

public class ExperimentController
{
    //used to read information from given file
    Scanner scanner;
    //create event object which can be added to the event set
    public Event e;
    //an event set used to store list of events that will pending in order of time
    public PriorityQueue<Event> eventSet;
    /**
     * Constructor for experiment controller
     */
    public ExperimentController()
    {
        eventSet=new PriorityQueue<Event>();
    }

    public static void main(String[] arg) { 
        ExperimentController controller = new ExperimentController();
        controller.run();
    } 

    /**
     * A run method - run the coffee shop simulation for different trails by varying the number of cashiers hired in the shop 
     */
    public void run() {
        try {   
            //vary number of s, which represents number of cashier hired in the shop from 10 to 1
            for(int s=10;s>0;s--){
                scanner = new Scanner(new File("input.txt"));
                //read the first line, which is the range of profit that a cashier can earn
                String prof=scanner.nextLine();
                String[] p=prof.split(" ");
                double p1=Double.parseDouble(p[0]);
                double p2=Double.parseDouble(p[1]);
                //read the second line, which is a fixed cost of hiring a cashier

                String cost=scanner.nextLine();
                double c=Double.parseDouble(cost);
                //read the third line, which is the range of average serving time of each customer

                String servt=scanner.nextLine();
                String[] st=servt.split(" ");
                int st1=Integer.parseInt(st[0]);
                int st2=Integer.parseInt(st[1]);
                //the input file contains the arrival time of each customer since the fourth line
                //if the file has next line, create the new arrival event     
                while(scanner.hasNextLine()){
                    String arrtime=scanner.nextLine();
                    String[] time=arrtime.split(":");
                    //turned the 24- hour format time into seconds
                    Integer caltime=Integer.parseInt(time[0])*3600+Integer.parseInt(time[1])*60+Integer.parseInt(time[2]);
                    e=new Arrive(caltime);
                    eventSet.add(e);
                }
                //int s=1;

                System.out.println("When there are "+s+" cashier staffed in the coffee shop: ");
                //System.out.println("The total profit of the shop is "+ cshop.tprofit);
                //System.out.println("The total cost of the shop is "+ cshop.tcost);
                Coffeeshop cshop = new Coffeeshop();
                //run the method of the coffee shop class by entering the arguments of input
                cshop.run(eventSet,s,p1,p2,c,st1,st2);
                System.out.println("The daily net profit of the coffee shop is "+ cshop.netprofit+"$");
                System.out.println("The number of overflow customer is "+ cshop.numberofoverflow);
                System.out.println("The overflow rate is "+ (float)cshop.numberofoverflow/cshop.totalnumarrive *100 +"%");
                int total=0;
                int max = 0;
                //get the max waiting time from the waiting time arraylist by using max
                for(int i = 0; i < cshop.waittime.size(); i++)
                {
                    total += cshop.waittime.get(i);
                    if (cshop.waittime.get(i)>max){
                        max = cshop.waittime.get(i);
                    }
                }
                //calculate the average time
                int avg = total / cshop.waittime.size();
                System.out.println("The average waiting time is: " + avg+" seconds");
                System.out.println("The maximum waiting time is: " + max+" seconds\n");
            }
        }
        catch (Exception error) {
            System.out.println(error);
        }
    }
}
