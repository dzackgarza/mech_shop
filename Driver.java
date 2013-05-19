import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

public class Driver
{
    static int totalEnginePrice;
    static int numberOfEngineServices;

    static int totalRadiatorPrice;
    static int numberOfRadiatorServices;

    static int totalTirePrice;
    static int numberOfTireServices;
    
    static int totalBrakePrice;
    static int numberOfBrakeServices;
    static ArrayList<Service> services;
    static MechanicShop myShop;
    
    static final int MAX_SERVICES = 100;
    public static final int COUPON_MIN_YEAR = 2007;
    public static final int COUPON_MAX_YEAR = 2012;
	public static final int DAYS_IN_MONTH = 29;
	public static final int MONTHS_IN_YEAR = 12;
	public static final int MAX_MILES = 300000;
	
	public static void main(String[] args) {
        new Driver().start();
    }
	
    public Driver() {
    	myShop = new MechanicShop();
        services = new ArrayList<Service>();    
    }
    
    public void start() {
    	printHeader();
        populateServiceArray();
        checkForCoupons();       
        printFinalTally();
    }

    private static void populateServiceArray() {
    	Service tempRandomService;
    	
	    for (int i = 0; i < MAX_SERVICES; i++) {
	        
	        // Add random Services
	    	tempRandomService = getRandomService();
	    	
	    	// Add it to the list
	    	services.add(tempRandomService);
	    	
	        // Create and set a random Sedan
	    	tempRandomService.setSedan(new Sedan(getRandomVIN(), getRandomMiles()));
	
	        // Randomly assign a Mechanic
	    	tempRandomService.setMechanic(myShop.getRandomMechanic());
	        
	        // Randomly assign a service date
	    	tempRandomService.setServiceDate(getRandomServiceDate());
	
	        // Formatted Output
	        printIndividualServiceOutput(tempRandomService); 
	    }
    }
	    
    private static void printHeader() {
        System.out.printf("No.\t Service\t \t Mechanic\t\t Fees\t\t VIN\n");
    }
    
    private static void printIndividualServiceOutput(Service p) {
    	System.out.printf("%2d\t %-12s\t %-15s\t $%3d.00\t %8s\n", 
                services.indexOf(p), 
                p.toString(), 
                p.mechanicAssignedToService.toString(),
                p.getPrice(),
                p.associatedSedan.toString()
                );
    }
    
    private static void checkForCoupons() {
    	for (Service j : services) {
            // Check service data against expiration date
            ServiceCoupon checker = new ServiceCoupon();
            checker.checkCoupon(j, services.indexOf(j));
        }
    }
    
    private static void printFinalTally() {
    	System.out.printf("\nEngine Oil:\t %2d\t Total: $%3d.00 \n", numberOfEngineServices, totalEnginePrice);
        System.out.printf("Radiator Flush:\t %2d\t Total: $%3d.00 \n",numberOfRadiatorServices, totalRadiatorPrice);
        System.out.printf("Tire Service:\t %2d\t Total: $%3d.00 \n",numberOfTireServices, totalTirePrice);
        System.out.printf("Brake Service:\t %2d\t Total: $%3d.00 \n",numberOfBrakeServices, totalBrakePrice);
    }
    
    private static String getRandomVIN() {
    	String randomVINstring = "";
        for (int j = 0; j < 4; j++) {
            char c = (char)(new Random().nextInt(26) + 'A');
            randomVINstring += c;
        }
        // Only adds on 4 digit numbers
        return (randomVINstring += new Random().nextInt(9999-1000+1)+1000);
    }
    
    private static int getRandomMiles() {
    	return new Random().nextInt(MAX_MILES);
    }
    
    private static GregorianCalendar getRandomServiceDate() {
    	
    	
        // Randomly assign a service date (Random = [(max-min)+1]+min
        int serviceYear = new Random().nextInt((COUPON_MAX_YEAR-COUPON_MIN_YEAR)+1)+COUPON_MIN_YEAR; // 2007-2012
        int serviceMonth = new Random().nextInt((MONTHS_IN_YEAR-1)+1)+1; // 1-12
        int serviceDay = new Random().nextInt(DAYS_IN_MONTH); // 0-28
        return new GregorianCalendar(serviceYear, serviceMonth, serviceDay);
    }
        
    private static Service getRandomService() {
    	
    	Service randomService = null;
    	int randomIndex = new Random().nextInt(4);
        switch (randomIndex) {
            case 0:
                randomService = new EngineOil();
                totalEnginePrice += randomService.getPrice();
                numberOfEngineServices++;
                break;
            case 1:
            	randomService = new RadiatorFlush();
                totalRadiatorPrice += randomService.getPrice();
                numberOfRadiatorServices++;
                break;
            case 2:
            	randomService = new TireService();
                totalTirePrice += randomService.getPrice();
                numberOfTireServices++;
                break;
            case 3:
            	randomService = new BrakeService();
                totalBrakePrice += randomService.getPrice();
                numberOfBrakeServices++;
                break;
            default: break; 
        }
        return randomService;
    }
}
