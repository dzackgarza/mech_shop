import java.io.Serializable;


public class TireService extends Service implements Serializable
{

	private static final long serialVersionUID = -6362950071811493354L;
	private static final int ServicePrice = 350;

    public TireService()
    {
        super(ServicePrice);
    }


    public String toString() {
        return "Tire"; }
}
