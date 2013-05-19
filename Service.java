import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Random;
public class Service implements Serializable
{
   
	private static final long serialVersionUID = -389449182137778413L;
	
	Sedan associatedSedan;
    Mechanic mechanicAssignedToService;
    private int ServicePrice;
    GregorianCalendar serviceDate;
    ServiceCoupon coupon;

    public static final int COUPON_MIN_YEAR = 2007;
    public static final int COUPON_MAX_YEAR = 2012;
	public static final int DAYS_IN_MONTH = 29;
	public static final int MONTHS_IN_YEAR = 12;
	public static final int MAX_MILES = 300000;
	
    /**
     * Default constructor for service objects
     * @param priceOfService
     */
    public Service(int priceOfService) {
        this.ServicePrice = priceOfService;
    }

    public void setMechanic(Mechanic mechanicThatDoesService) {
        this.mechanicAssignedToService = mechanicThatDoesService;
    }

    public void setSedan(Sedan sedanToSet) {
        this.associatedSedan = sedanToSet;
    }
    
    public void setServiceDate(GregorianCalendar passedDate) {
        this.serviceDate = passedDate;
;    }

    public int getPrice() {
        return ServicePrice;
    }
    
    /**
     * Populates a service with all of the required fields
     * @param s
     * 	Service to initialize
     */
    public void initialize(MechanicShop m) {
    	setSedan(new Sedan(getRandomVIN(), getRandomMiles()));
    	setMechanic(m.getRandomMechanic());
    	setServiceDate(getRandomServiceDate());

    	
    }
    /**
     * Constructs a calendar object within certain confines defined in the Driver class.
     * @return
     * 	A calendar object within a specific timeframe.
     */
    private static GregorianCalendar getRandomServiceDate() {
    	
        // Randomly assign a service date (Random = [(max-min)+1]+min
        int serviceYear = new Random().nextInt((COUPON_MAX_YEAR-COUPON_MIN_YEAR)+1)+COUPON_MIN_YEAR; // 2007-2012
        int serviceMonth = new Random().nextInt((MONTHS_IN_YEAR-1)+1)+1; // 1-12
        int serviceDay = new Random().nextInt(DAYS_IN_MONTH); // 0-28
        return new GregorianCalendar(serviceYear, serviceMonth, serviceDay);
    }
    /**
     * 
     * @return
     * 	Returns a random number of miles bounded by MAX_MILES, defined in the Driver class.
     */
     public static int getRandomMiles() {
     	return new Random().nextInt(MAX_MILES);
     }
     
     /**
      * Generates random VIN numbers for cars.
      * @return
      * 	Returns a string conforming to the following rules: 
      * 	First 4 characters - Letters between A and Z
      * 	Last 4 characters - Numbers between 0 and 9
      */
     public static String getRandomVIN() {
     	String randomVINstring = "";
         for (int j = 0; j < 4; j++) {
             char c = (char)(new Random().nextInt(26) + 'A');
             randomVINstring += c;
         }
         // Only adds on 4 digit numbers
         return (randomVINstring += new Random().nextInt(9999-1000+1)+1000);
     }
    
}
