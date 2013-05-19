import java.io.Serializable;


public class EngineOil extends Service implements Serializable {

	private static final long serialVersionUID = -4070321490741767533L;
	private static final int ServicePrice = 70;

    public EngineOil() {
        super(ServicePrice);
    }
    
    public String toString() {
        return "Engine Oil";
    }
    
}
