import java.util.*;
public class Service
{
    Sedan associatedSedan;
    Mechanic mechanicAssignedToService;
    private int ServicePrice;
    GregorianCalendar serviceDate;
    ServiceCoupon coupon;

    public Service()
    {
    }
    
    public Service(int priceOfService) {
        this.ServicePrice = priceOfService;
    }

    public void setMechanic(Mechanic mechanicThatDoesService) {
        this.mechanicAssignedToService = mechanicThatDoesService;
    }

    public void setSedan(Sedan sedanToSet) {
        this.associatedSedan = sedanToSet;
    }
    
    public void setServiceDate(GregorianCalendar passedDate) {
        this.serviceDate = passedDate;
;    }

    public int getPrice() {
        return ServicePrice;
    }
    
    
}
