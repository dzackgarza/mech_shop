import java.io.Serializable;


public class RadiatorFlush extends Service implements Serializable {

	private static final long serialVersionUID = 7304227906575115537L;
	private static final int ServicePrice = 150;

    public RadiatorFlush() {
        super(ServicePrice);
    }

    public String toString() {
        return "Radiator Flush";
    }


}
