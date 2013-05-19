import java.io.Serializable;

public class Sedan implements Serializable {
	
	private static final long serialVersionUID = -4501603416807819419L;
	private String VIN;
    private int Miles;
    
    /**
     * Default constructor for car objects
     * @param vinNumber
     * @param CurrentMiles
     */
    public Sedan(String vinNumber, int CurrentMiles)
    {
        VIN = vinNumber;
        Miles = CurrentMiles;
    }
    
    public String toString() {
        return this.VIN;
    }
}
