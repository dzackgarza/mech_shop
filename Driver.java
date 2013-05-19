import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
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
	
	public static final int ENGINE_OIL = 0;
	public static final int RADIATOR_FLUSH = 1;
	public static final int TIRE_SERVICE = 2;
	public static final int BRAKE_SERVICE = 3;
	
	/**
	 * Entry point into the program
	 * @param args
	 * 	None needed
	 */
	public static void main(String[] args) {
        new Driver().start();
    }
	
	/**
	 * Constructor for Driver objects. At the moment, simply instantiates a
	 * new driver and calls its start() method.
	 */
    public Driver() {
    	myShop = new MechanicShop();
        services = new ArrayList<Service>();    
    }
    
    /**
     * Handles the assignment objectives:
     * 
        //	Step 2: Traverse the list, print the service types in this format:

			// Service Type: Brake at index 0

			// Service Type: Tire at Index 1....

			// This step should be done in a method

		//	Step 3: Remove  All brake services from the Arraylist 

		//	Step 4: Do Step 2

		//	Step 5: Replace  All Tires services and replace with Radiator services.

		//	Step 6: Do Step 2

		//	Step 7 : Insert A brake Service at 4th index

		//	Step 8:  Do Step 2
     */
    private void start() {
    	printHeader();
        populateServiceList();
        checkForCoupons();       
        printFinalTally();					// Step 2
        
        deleteServices(BRAKE_SERVICE);		// Step 3
    	printHeader();	
        printServiceTypes();				// Step 4
        
        replaceServices(TIRE_SERVICE, RADIATOR_FLUSH);	// Step 5
    	printHeader();
        printServiceTypes();				// Step 6
        
        insertService(BRAKE_SERVICE, 4);	// Step 7
        printHeader();
        printServiceTypes();				// Step 8
    }
    /**
     * Inserts a new service into the service list.
     * 
     * @param serviceToInsert
     * 	Code for the service to insert. (See static final ints of Driver class)
     * @param location
     * 	Index in which to place the new service.
     */
    private static void insertService(int serviceToInsert, int location) {
    	services.add(location, getService(serviceToInsert));
    }
    
    /**
     * Replaces all of one type of service with another type.
     * 
     * @param serviceToRemove
     * 	Code for service to remove.
     * @param serviceToSub
     * 	Code for service that will replace the removed service.
     */
    public static void replaceServices(int serviceToRemove, int serviceToSub){
    	ListIterator<Service> i = services.listIterator();
    	
    	while(i.hasNext()) {
    		Service nextService = i.next();
    		
    		switch(serviceToRemove) {
	    		case ENGINE_OIL:
	    			if(nextService instanceof EngineOil) {
	    	            i.set(getService(serviceToSub));
	    				continue;
	        		}
	    			else break;
	    		case RADIATOR_FLUSH:
	    			if(nextService instanceof RadiatorFlush) {
	    				i.set(getService(serviceToSub));
	    				continue;
	        		}
	    			else break;
	    		case TIRE_SERVICE:
	    			if(nextService instanceof TireService) {
	    				i.set(getService(serviceToSub));
	    				continue;
	        		}
	    			else break;
	    		case BRAKE_SERVICE:
	    			if(nextService instanceof BrakeService) {
	    				i.set(getService(serviceToSub));
	    				continue;
	        		}
	    			else break;
	    		default: break;
    		}
    	}
    }
    
    /**
     * Deletes all of one type of service.
     * @param serviceToRemove
     * 	Code for service type to be deleted.
     */
    public static void deleteServices(int serviceToRemove){
    	Iterator<Service> i = services.iterator();
    	
    	while(i.hasNext()) {
    		Service nextService = i.next();
    		
    		switch(serviceToRemove) {
	    		case ENGINE_OIL:
	    			if(nextService instanceof EngineOil) {
	    	            i.remove();
	    				continue;
	        		}
	    			else break;
	    		case RADIATOR_FLUSH:
	    			if(nextService instanceof RadiatorFlush) {
	    	            i.remove();
	    				continue;
	        		}
	    			else break;
	    		case TIRE_SERVICE:
	    			if(nextService instanceof TireService) {
	    	            i.remove();
	    				continue;
	        		}
	    			else break;
	    		case BRAKE_SERVICE:
	    			if(nextService instanceof BrakeService) {
	    	            i.remove();
	    				continue;
	        		}
	    			else break;
	    		default: break;
    		}
    	}
    }
    
    /**
     * Iterates the service array and passes each service along to a print formatter.
     * Call this anytime you want to display a full list of all services and associated data.
     */
    public static void printServiceTypes() {
    	for (Service i : services) {
    		printIndividualServiceOutput(i);
    	}
    }
    
   /**
    * Initializes the service list with randomized services.
    * Takes care of assigning a random mechanic, service date, and associated car, and prints the results.
    */
    private static void populateServiceList() {
    	Service tempRandomService;
    	
	    for (int i = 0; i < MAX_SERVICES; i++) {
	        
	        // Add random Services
	    	tempRandomService = getService(new Random().nextInt(4));
	    	
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
	    
    /**
     * A simple header to identify columns when printing.
     */
    private static void printHeader() {
        System.out.printf("No.\t Service\t \t Mechanic\t\t Fees\t\t VIN\n");
    }
    
    /**
     * Pass this method a service to print all of its associated data.
     * @param p
     * 	Service to be printed.
     * 
     * #TODO: Maybe make the service's toString take care of this..?
     */
    private static void printIndividualServiceOutput(Service p) {
    	System.out.printf("%2d\t %-12s\t %-15s\t $%3d.00\t %8s\n", 
                services.indexOf(p), 
                p.toString(), 
                p.mechanicAssignedToService.toString(),
                p.getPrice(),
                p.associatedSedan.toString()
                );
    }
    
    /**
     * Iterates over the service list and applies any relevant coupons.
     */
    private static void checkForCoupons() {
    	for (Service j : services) {
            // Check service data against expiration date
            ServiceCoupon checker = new ServiceCoupon();
            checker.checkCoupon(j, services.indexOf(j));
        }
    }
    
    /**
     * Prints static total related to the entire shop, such as total services and net prices.
     */
    private static void printFinalTally() {
    	System.out.printf("\nEngine Oil:\t %2d\t Total: $%3d.00 \n", numberOfEngineServices, totalEnginePrice);
        System.out.printf("Radiator Flush:\t %2d\t Total: $%3d.00 \n",numberOfRadiatorServices, totalRadiatorPrice);
        System.out.printf("Tire Service:\t %2d\t Total: $%3d.00 \n",numberOfTireServices, totalTirePrice);
        System.out.printf("Brake Service:\t %2d\t Total: $%3d.00 \n",numberOfBrakeServices, totalBrakePrice);
    }
    
    /**
     * Generates random VIN numbers for cars.
     * @return
     * 	Returns a string conforming to the following rules: 
     * 	First 4 characters - Letters between A and Z
     * 	Last 4 characters - Numbers between 0 and 9
     */
    private static String getRandomVIN() {
    	String randomVINstring = "";
        for (int j = 0; j < 4; j++) {
            char c = (char)(new Random().nextInt(26) + 'A');
            randomVINstring += c;
        }
        // Only adds on 4 digit numbers
        return (randomVINstring += new Random().nextInt(9999-1000+1)+1000);
    }
    
   /**
    * 
    * @return
    * 	Returns a random number of miles bounded by MAX_MILES, defined in the Driver class.
    */
    private static int getRandomMiles() {
    	return new Random().nextInt(MAX_MILES);
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
     * Wrapper for generating services. Also handles incrementing net totals.
     * #TODO: This probably shouldn't handle net totals. :)
     * 
     * @param whichService
     * 	Code for the service you need, or a random number between 0 and 4
     * @return
     * 	Returns a new service object.
     */
    private static Service getService(int whichService) {
    	
    	Service randomService = null;
        switch (whichService) {
            case ENGINE_OIL:
                randomService = new EngineOil();
                totalEnginePrice += randomService.getPrice();
                numberOfEngineServices++;
                break;
            case RADIATOR_FLUSH:
            	randomService = new RadiatorFlush();
                totalRadiatorPrice += randomService.getPrice();
                numberOfRadiatorServices++;
                break;
            case TIRE_SERVICE:
            	randomService = new TireService();
                totalTirePrice += randomService.getPrice();
                numberOfTireServices++;
                break;
            case BRAKE_SERVICE:
            	randomService = new BrakeService();
                totalBrakePrice += randomService.getPrice();
                numberOfBrakeServices++;
                break;
            default: break; 
        }
        return randomService;
    }
}
