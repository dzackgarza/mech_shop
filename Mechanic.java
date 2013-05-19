import java.io.Serializable;

public class Mechanic implements Serializable {
	
	private static final long serialVersionUID = -3410668598898124343L;
	private String firstName;
    private String lastName;
    
    public Mechanic(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }
    
    public String toString() {
        return (lastName.toString() + ", " + firstName.toString());
    }

}
