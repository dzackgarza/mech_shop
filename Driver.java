import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

public class Driver implements Serializable
{

	private static final long serialVersionUID = -8213360752748373437L;
	
	static int totalEnginePrice;
    static int numberOfEngineServices;

    static int totalRadiatorPrice;
    static int numberOfRadiatorServices;

    static int totalTirePrice;
    static int numberOfTireServices;
    
    static int totalBrakePrice;
    static int numberOfBrakeServices;
    ArrayList<Service> services;
    MechanicShop myShop;
    
    static final int MAX_SERVICES = 100;
    
	public static final int ENGINE_OIL = 0;
	public static final int RADIATOR_FLUSH = 1;
	public static final int TIRE_SERVICE = 2;
	public static final int BRAKE_SERVICE = 3;
	
	public static Driver g;
	public static final String SERIAL_FILE = "Mechanic.dzg";
	
	/**
	 * Entry point into the program
	 * @param args
	 * 	None needed
	 */
	public static void main(String[] args) {
       
        //Uncomment each section to run different parts
		
        /* Project 1:
		/* Randomly populate service array, performs list operations such as
		/* inserting, replacing, and deleting. See g.start()
		 */
		// g = new Driver();
        // g.start();
        
        
        /* Project 2:
         * Creates a Driver object, populates it, and then serializes it into 
         * an output file ("Mechanic.dzg")
         */
		
		/** Part 1: Serialize **/
		// g = new Driver();
        // g.start();
        // g.serialize();
        
		/**
		 * Reads object from file and prints list of services.
		 * Run this after writing serialized Driver to output file.
		 */
        /** Part 2: Deserialize **/
        // g = deserialize();
        // g.alt_start();
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
    @SuppressWarnings("unused")
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
	 * Prints service list from a serialized object. Must be different because many
	 * values are not serialized.
	 * Alternatively, this can be used at any time to print a simplified service list.
	 */
	public void alt_start() {
		for (Service p : g.services) {
			System.out.printf("Index: %2d\t Service:%-12s\t \n", 
	                services.indexOf(p), 
	                p.toString() 
	                );
		}
	}
	
	/**
	 * Serialize the current Driver class and write it to a file.
	 */
	public void serialize() {
		FileOutputStream fileSer = null;
        ObjectOutputStream out = null;
		try 
		{
			fileSer = new FileOutputStream(SERIAL_FILE);
			out = new ObjectOutputStream(fileSer);
			out.writeObject(g);
			out.close();
			fileSer.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Deserializes a Driver object from a file
	 * @return
	 * 	Returns a Driver object with applicable fields recalled from file
	 */
	public static Driver deserialize () {
		Driver h = null;
		ObjectInputStream filein = null;
		FileInputStream fileDeSer = null;
		
		try 
		{
			fileDeSer = new FileInputStream(SERIAL_FILE);
			filein = new ObjectInputStream(fileDeSer);
			h = (Driver) filein.readObject();
			fileDeSer.close();
			filein.close();
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return h;

	}
	
	/**
	 * Constructor for Driver objects. At the moment, simply instantiates a
	 * new driver, an associated shop, and list to hold all services.
	 */
    public Driver() {
    	this.myShop = new MechanicShop();
        this.services = new ArrayList<Service>();    
    }
    
    /**
     * Inserts a new service into the service list.
     * 
     * @param serviceToInsert
     * 	Code for the service to insert. (See static final ints of Driver class)
     * @param location
     * 	Index in which to place the new service.
     */
    private void insertService(int serviceToInsert, int location) {
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
    public void replaceServices(int serviceToRemove, int serviceToSub){
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
    public void deleteServices(int serviceToRemove){
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
    public void printServiceTypes() {
    	for (Service i : services) {
    		printIndividualServiceOutput(i);
    	}
    }
    
   /**
    * Initializes the service list with randomized services.
    * Takes care of assigning a random mechanic, service date, and associated car, and prints the results.
    */
    private void populateServiceList() {
    	Service tempRandomService;
    	
	    for (int i = 0; i < MAX_SERVICES; i++) {
	        
	        // Add random Services
	    	tempRandomService = getService(new Random().nextInt(4));
	    	
	    	// Add it to the list
	    	services.add(tempRandomService);
	    	
	    	tempRandomService.initialize(myShop);
	
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
    private void printIndividualServiceOutput(Service p) {
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
    private void checkForCoupons() {
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
     * Wrapper for generating services. Also handles incrementing net totals.
     * #TODO: This probably shouldn't handle net totals. :)
     * 
     * @param whichService
     * 	Code for the service you need, or a random number between 0 and 4
     * @return
     * 	Returns a new service object.
     */
    private Service getService(int whichService) {
    	
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
        randomService.initialize(myShop);
        return randomService;
    }
}
