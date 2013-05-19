
public class TireService extends Service
{

    private static final int ServicePrice = 350;

    public TireService()
    {
        super(ServicePrice);
    }


    public String toString() {
        return "Tire"; }
}
