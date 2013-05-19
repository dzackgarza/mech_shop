

public class BrakeService extends Service
{
    private static final int ServicePrice = 250;

    public BrakeService()
    {
        super(ServicePrice);
    }

    public String toString() {
        return "Brakes";
    }
}
