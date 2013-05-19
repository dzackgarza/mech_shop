import java.io.Serializable;

public class BrakeService extends Service implements Serializable {
	private static final long serialVersionUID = -3451322473081660344L;
	private static final int ServicePrice = 250;

    public BrakeService() {
        super(ServicePrice);
    }

    public String toString() {
        return "Brakes";
    }
}
