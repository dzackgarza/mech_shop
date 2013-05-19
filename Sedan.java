public class Sedan
{
    private String VIN;
    private int Miles;
    
    public Sedan(String vinNumber, int CurrentMiles)
    {
        this.VIN = vinNumber;
        this.Miles = CurrentMiles;
    }
    public Sedan() {}
    
    public String toString() {
        return this.VIN;
    }
}
