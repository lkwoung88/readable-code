package cleancode.studycafe.my.model;

public class Charge {

    public static final Charge EMPTY_CHARGE = Charge.of(0, 0, 0.0);

    private final int duration;
    private final int price;
    private final double discountRate;

    private Charge(int duration, int price, double discountRate) {
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static Charge of(int duration, int price, double discountRate) {
        return new Charge(duration, price, discountRate);
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public int getCharge() {
        return (int) (price * (1 - discountRate));
    }

    public int getDiscountPrice() {
        return (int) (price * discountRate);
    }
}
