public class BrakeServiceCoupon extends ServiceCoupon {

	private static final long serialVersionUID = -5581912944164067535L;

	public BrakeServiceCoupon(int discountPercent) {
        super();
        System.out.println("You will receive " + discountPercent + " percent off of your next service.");
    }
}
