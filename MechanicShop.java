import java.io.Serializable;
import java.util.Random;

public class MechanicShop implements Serializable {

	private static final long serialVersionUID = 7346201421473837071L;
	private static final int NumberOfMechanicsInShop = 4;
    Mechanic[] EmployedMechanics;


    public MechanicShop() {
        EmployedMechanics = new Mechanic[NumberOfMechanicsInShop];
        // Define the mechanics who work in this shop
        EmployedMechanics[0] = new Mechanic("Bryan", "Lastname");
        EmployedMechanics[1] = new Mechanic("Mike", "Lastname2");
        EmployedMechanics[2] = new Mechanic("Tom", "Lastname3");
        EmployedMechanics[3] = new Mechanic("Scott", "Lastname4");
    }

    public Mechanic getRandomMechanic() {
        Random randomNumber = new Random();
        int randomMech = randomNumber.nextInt(4);
        Mechanic newMech = EmployedMechanics[randomMech];
        return newMech;
    }
}
