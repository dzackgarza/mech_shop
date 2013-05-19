import java.util.Random;

public class MechanicShop
{
    private static final int NumberOfMechanicsInShop = 4;
    Mechanic[] EmployedMechanics;


    public MechanicShop()
    {
        this.EmployedMechanics = new Mechanic[NumberOfMechanicsInShop];
        // Define the mechanics who work in this shop
        this.EmployedMechanics[0] = new Mechanic("Bryan", "Lastname");
        this.EmployedMechanics[1] = new Mechanic("Mike", "Lastname2");
        this.EmployedMechanics[2] = new Mechanic("Tom", "Lastname3");
        this.EmployedMechanics[3] = new Mechanic("Scott", "Lastname4");
        
    }

    public Mechanic getRandomMechanic() {
        Random randomNumber = new Random();
        int randomMech = randomNumber.nextInt(4);
        return EmployedMechanics[randomMech];
    }
}
