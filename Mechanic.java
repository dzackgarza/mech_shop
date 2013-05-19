public class Mechanic
{
    String firstName;
    String lastName;
    public Mechanic(String first, String last)
    {
        this.firstName = first;
        this.lastName = last;
    }
    
    public String toString() {
        return (lastName.toString() + ", " + firstName.toString());
    }

}
