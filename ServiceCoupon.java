import java.util.*;

public class ServiceCoupon extends Coupon
{
    Random randomNumber = new Random();
    final Calendar expiryDate;
    int discount = 0; // As in integer percentage
    public ServiceCoupon()
    {
        int expiryYear = 2013;
        int expiryMonth = randomNumber.nextInt((12-1)+1)+1; // 1-12
        int expiryDay = randomNumber.nextInt(29); // 0-28
        final Calendar expiry =  new GregorianCalendar(expiryYear, expiryMonth, expiryDay);
        this.expiryDate = expiry;
        this.setYear(expiryYear);
        this.setMonth(expiryMonth);
        this.setDay(expiryDay);
        
    }

    public void checkCoupon(Service service, int position) {
        Calendar today = Calendar.getInstance();
        long todaysTime = this.expiryDate.getTimeInMillis();
        long serviceTime = service.serviceDate.getTimeInMillis();
        long oneDay = 1000 * 60 * 60 * 24;
        int difference = (int) ((todaysTime - serviceTime) / oneDay);
        
        if (service instanceof EngineOil) {     // 5 Months
            if (difference >= 150) { 
                System.out.println("-----------------------------");
                System.out.println("Service #: " + position);
                 System.out.println("Date of last service: " 
                    + service.serviceDate.get(service.serviceDate.MONTH) + "/"
                    + service.serviceDate.get(service.serviceDate.DAY_OF_MONTH) + "/"
                    + service.serviceDate.get(service.serviceDate.YEAR));
                System.out.println("Days since last service: " + difference);
                System.out.println("Days needed to qualify for coupon: " + 150);
                service.coupon = new EngineServiceCoupon(5); }
        }
        if (service instanceof RadiatorFlush) { // 5 Years
            if (difference >= 1825) { 
                System.out.println("-----------------------------");
                System.out.println("Service #: " + position);
                System.out.println("Date of last service: " 
                    + service.serviceDate.get(service.serviceDate.MONTH) + "/"
                    + service.serviceDate.get(service.serviceDate.DAY_OF_MONTH) + "/"
                    + service.serviceDate.get(service.serviceDate.YEAR));
                System.out.println("Days since last service: " + difference);
                System.out.println("Days needed to qualify for coupon: " + 1825);
                service.coupon = new RadiatorFlushCoupon(15); }
        }
        if (service instanceof TireService) {   // 3 Years
            if (difference >= 1095) { 
                System.out.println("-----------------------------");
                System.out.println("Service #: " + position);
                System.out.println("Date of last service: " 
                    + service.serviceDate.get(service.serviceDate.MONTH) + "/"
                    + service.serviceDate.get(service.serviceDate.DAY_OF_MONTH) + "/"
                    + service.serviceDate.get(service.serviceDate.YEAR));
                System.out.println("Days since last service: " + difference);
                System.out.println("Days needed to qualify for coupon: " + 1095);
                service.coupon = new TireServiceCoupon(); }
        }
        if (service instanceof BrakeService) {  // 3 Years
            if (difference >= 1095) { 
                System.out.println("-----------------------------");
                System.out.println("Service #: " + position);
                 System.out.println("Date of last service: " 
                    + service.serviceDate.get(service.serviceDate.MONTH) + "/"
                    + service.serviceDate.get(service.serviceDate.DAY_OF_MONTH) + "/"
                    + service.serviceDate.get(service.serviceDate.YEAR));
                System.out.println("Days since last service: " + difference);
                System.out.println("Days needed to qualify for coupon: " + 1095);
                service.coupon = new BrakeServiceCoupon(30); }
        }
    }

}
