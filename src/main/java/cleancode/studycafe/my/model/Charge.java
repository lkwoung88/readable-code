package cleancode.studycafe.my.model;

public class Charge {

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
}
