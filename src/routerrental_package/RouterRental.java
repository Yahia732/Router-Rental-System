package routerrental_package;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;


public class RouterRental {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Customer customer []=new Customer [4];
       customer [0]=new Resident();
       customer [1]=new Resident();
       customer[2]=new Foreigers();
       customer[3]=new Foreigers();
             ArrayList<Router> routers=new ArrayList<Router>();
         
       Router router = null;
       Scanner in=new Scanner (System.in);
       while(true)
       {
          
       System.out.println("Enter 1 to show availible Routers");
       System.out.println("Enter 2 Rent Router");
       System.out.println("Enter 3 to show one customer with different invoices");      
       System.out.println("Enter 4 cancel rent ");
       System.out.println("Enter 5 extend duration");
       System.out.println("Enter 6 to Change Router Model");
        System.out.println("Enter 7 to repot an issue");
        System.out.println("Enter 8 to Quit");
       
           int select=in.nextInt();
          if(select==1){

       router=new Router(121425,"VDSL",8);
       routers.add(router);
       router=new Router(0114527,"Wireless",4);
       routers.add(router);
      
       //router=new Router();
      // router.addRouter("VDSL", 8);
       routers.add(router);
       router=new Router();
       router.addRouter(2222222,"ADSL", 7);
       routers.add(router);
       router =new Router();
       router.addRouter(1234744,"USB",5);
       routers.add(router);
      
       System.out.println(" availible Routers");
       for(Router routeer:routers){
           routeer.display();
           
       }
          }
    else if(select==2){
   
     customer[0].rentrouter(routers.get(0),1,4);
              
      for(int i=0;i<80;i++)
          System.out.print((i==79)?"\n":"-");
    }
       /**
        * one customer with different invoices
        */ 
      else if(select==3){
    
       customer[0].rentrouter(routers.get(1), 5, 4);
         customer[0].invoice.show();
              
          for(int i=0;i<80;i++)
          System.out.print((i==79)?"\n":"-");
      }
         /** 
          * cancel rent
          
          */
      else if(select==4){
  
         customer[1].rentrouter(routers.get(3), 2, 4);
         customer[1].invoice.show();
         customer[1].cancelrent(customer[1].res);
     
         for(int i=0;i<80;i++)
           System.out.print((i==79)?"\n":"-");
      }
          /**Exception handling
           * 
          */
       /* 
        customer[1].rentrouter(routers.get(5), 1, 4);
     customer[1].invoice.show();
     customer[1].cancelrent(customer[1].res);
        for(int i=0;i<80;i++)
          System.out.print((i==79)?"\n":"-");
        */ 
         /**
          * extend duration
          */
      else if(select==5){
   
         customer[2].rentrouter(routers.get(1), 1, 3);
         customer[2].Extendduration(customer[2].res, 1);
          customer[2].invoice.show();
             int i=0;
             while(i<80){
                 
              System.out.print((i==79)?"\n":"-");
               i++;
            }
      }
      else if(select==6){
         customer[3].rentrouter(routers.get(1), 1, 3);
         customer[3].changemodel(router);
         customer[3].invoice.show();
      }
   

 
             
      else if(select==7) {   
       
             
       reports r=new issue();
       r.issues();
      }
          else
          break;
        
       
       
   
       }
       
            
    }
    
}
/**
 * interface
 */
interface Display
{
  public void display();
   
}
interface Getmodel
{
   public String getModel(); 
}
/**
 * router class
 */

  class Router implements Display,Getmodel{
      /**
    * different access modifiers
   */
    public int serialnumber;
    private static int counter=0;
    private String model;
    private int numofports;
//constructor
    public Router() {
      
        counter++;
        serialnumber=counter;
        
    }

    public Router(int serialnumber, String model, int numofports) {
        this.serialnumber = serialnumber;
        this.model = model;
        this.numofports = numofports;
    }
    
    
    /**
     * final method 
     */

    public final void addRouter(String model, int numofports) {
        this.model = model;
        this.numofports = numofports;
    }
    //overloading
    public final void addRouter(int serialnumber,String model, int numofports) {
        this.serialnumber=serialnumber;
        this.model = model;
        this.numofports = numofports;
    }
/**
 * method to display router properties
 */
    public void display(){
         System.out.println("--------------------------------------------------------------------------------------------\n");
        System.out.println("Serial Number:"+""+serialnumber);   
      
        System.out.println("Model:"+""+model);

        System.out.println("Number of ports:"+""+numofports);
         System.out.println("--------------------------------------------------------------------------------------------\n");
 
        
     
    }
  

    

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNumofports() {
        return numofports;
    }

    public void setNumofports(int numofports) {
        this.numofports = numofports;
    }
/**
 * static method
 */
    public static int getCounter() {
        return counter;
    }
    
    
    
}
/**
 * interface
 */
interface enddate
{
        public void setEnddate(int duration);
}

/**
 * reservation class
 */
 class Reservation implements enddate{
     /**
    * different access modifiers
   */
    public int reID;
    private static int number=0;
    private Router router;
    private Date reservationdate;
    private Date startdate;
    private Date enddate;
    private int duedate;
//constructor
    public Reservation() {
        
        reservationdate=new Date();
        startdate=new Date();
        enddate=new Date();
        number++;
        reID=number;
        
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(int afterday) {
        if(startdate.getDate()+afterday>30){
           startdate.setMonth(startdate.getMonth()+1);
           startdate.setDate(startdate.getDate()+afterday%30);
        }
        else
            startdate.setDate(startdate.getDate()+afterday);
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(int duration) {
       if(startdate.after(enddate)){
            if(enddate.getDate() + duration >30) {
                enddate.setMonth(startdate.getMonth()+1);
                enddate.setDate((duration + startdate.getDate())%30 );
            }
            else {
                enddate.setDate(duration + startdate.getDate() );
            }
        }
        else {
            if(enddate.getDate() + duration > 30){
                enddate.setMonth(enddate.getMonth()+1);
                enddate.setDate((duration + enddate.getDate())%30 );
            }
            else {
                
                enddate.setDate(duration + enddate.getDate());
            }
        }
        
            

    
}
    //method to calcluate date
    private void calculateduedate(){
        Date currentDate=new Date();
        if(currentDate.getDate()>enddate.getDate()){
            duedate=30-(currentDate.getDate()-enddate.getDate());
            
        }
        else
            duedate=enddate.getDate()-currentDate.getDate();
        
    }

    public int getDuedate() {
        calculateduedate();
        return duedate;
    }

    public Date getReservationdate() {
        return reservationdate;
    }
    
    
    
    
}
/**
 * interface
 */
interface rentingfees
{
     public float getRentingfees();
}
/**
 * invoice class
 */
 class Invoice implements rentingfees{
     /**
    * different access modifiers
   */
    public Reservation res;
    public static boolean isdiscount;
    public static float discount=0f;
    private int customerID;
    private float rentingfees;

    public Invoice() {
        customerID++;
    }

   
    
/**
 * calculated data member
 */
    public Invoice(Reservation res) {
        this.res = res;
        isdiscount=true;
        
    }

 
/**
 * calculated data member
 */
    public float getRentingfees() {
        for(int i=0;i<1;i++){
        if(res.getRouter().getModel().equals("VDSL")){
            rentingfees=20*(res.getDuedate());
            
        }
        else if(res.getRouter().getModel().equals("ADSL")){
            rentingfees=15*(res.getDuedate());
        }
          else if(res.getRouter().getModel().equals("Wireless")){
            rentingfees=12*(res.getDuedate());
        }
         else if(res.getRouter().getModel().equals("USB")){
            rentingfees=11*(res.getDuedate());
        }

        else
            rentingfees=10*(res.getDuedate());
        if(isdiscount=true)
            rentingfees-=(rentingfees*discount/100.0);
        else
            System.out.println("No discoutn");
        }
        return rentingfees;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    public void show(){
        getRentingfees();
        
        System.out.printf("#%d:\n Customer Number:%d\n Router model:%s\n Number of ports:%d\n StartDate:%s\n"+"Enddate:%s\n"+"Rentingfess:%f pounds\n",res.reID,customerID,res.getRouter().getModel(),res.getRouter().getNumofports(),res.getStartdate().toString(),res.getEnddate().toString(),rentingfees);
    }
   
}
/**
 * inheritance tree&exteption handling
 */
  class exception extends Exception{
    public exception(String message){
        super(message);
    }
    }
/**
 * interface
 */
interface extendduartion
{
    public void Extendduration(Reservation rese,int newduration);
}
    
/**
 * abstract class
 */
abstract class Customer implements extendduartion{
    /**
    * different access modifiers
   */
    protected Reservation res;
    protected Invoice invoice;
    protected ArrayList<Invoice>invoices;
    protected int customerID;
    protected int counter=0;
//constructor
    public Customer() {
        invoices= new ArrayList<Invoice>();
        counter++;
        customerID=counter;
    }

    public int getCustomerID() {
        return customerID;
    }
    /**
     * abstract method

     */
    public abstract void rentrouter(Router router,int startafter,int duration);
    public void cancelrent(Reservation rese){
        boolean rent=false;
        try{
        Date currentdate=new Date();
        if(res.getStartdate().getDate()-currentdate.getDate()<=1)
            if(res.getStartdate().getDate()-currentdate.getDate()<0&&
                    (30%(currentdate.getDate()-res.getStartdate().getDate())<=1)){
                rent=false;
                throw new exception("you can cancel rent only before startdate by 2 days");
            }
        
        }
        /**
         * exception handling
         */
        catch(exception ex){
            System.out.print("Error"+ex.getMessage());
        }
        if(rent=true){
            for(Invoice invoice:invoices)
                if(invoice.res==rese){
                    invoices.remove(invoice);
                    
                    break;
                }
            System.out.println("Rent has been canceled");
        }
    }
    //method to extend the duration
  public void Extendduration(Reservation rese,int newduration) {
      for(Invoice invoice:invoices){
          if(invoice.res.equals(rese))
              invoice.res.setEnddate(newduration);
      }
      System.out.print("Duration has been extened");
  }
  //method to change router model
    public void changemodel(Router router){
        rentrouter(router,res.getStartdate().getDate()-res.getReservationdate().getDate(),res.getDuedate());
    System.out.println("Router model has been changed");
    }
}
interface RentRouter
{
    public void rentrouter(Router router,int startafter,int duration);
}

 /**
  * inheritance
  
  */
 class Resident extends Customer implements RentRouter
{
     
     private final float discount;

    public Resident() {
        discount =25f;
        Invoice.discount=25f;
    }

    public float getDiscount() {
        return discount;
    }
    
    @Override
    public void rentrouter(Router router,int startafter,int duration){
        res=new Reservation();
        res.setRouter(router);
        res.setStartdate(startafter);
        res.setEnddate(duration);
        invoice=new Invoice(res);
        invoices.add(invoice);
        invoice.isdiscount=true;
        System.out.println("Router has been rent and you have 25% discount");
        
    } 
     
     
 }

/**
 * inheritance tree
 */
  class Foreigers extends Customer {
     @Override
    public void rentrouter(Router router,int startafter,int duration){
         res=new Reservation();
        res.setRouter(router);
        res.setStartdate(startafter);
        res.setEnddate(duration);
        invoice=new Invoice(res);
        invoices.add(invoice);
        invoice.isdiscount=false;
        Extendduration(res,2);
        System.out.println("Router has been rent ");
    }
}
/**
 * interface
 */interface reports{
    public void issues();
}
/**
 * issue class to deliver feedback from user
 */
class issue implements reports
{
    Scanner in=new Scanner(System.in);
    String iss;
    public  void issues(){
        System.out.println("If you any problems please mention it ");
        iss=in.next();
        System.out.println("Your Feedback has been deliverd");
    }
    
}
    
 
    


    


 


