import java.io.Serializable;

@SuppressWarnings("unused")
public abstract class Coupon implements Serializable {
	
	private static final long serialVersionUID = 7833065549659455645L;
	
	
	private int year;
    private int month;
    private int day;

    public void setYear(int setYear){
        year = setYear;
    }
    public void setMonth(int setMonth){
        month = setMonth;
    }
    public void setDay(int setDay){
        day = setDay;
    }
}
