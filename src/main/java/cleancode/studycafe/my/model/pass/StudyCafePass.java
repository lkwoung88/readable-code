package cleancode.studycafe.my.model.pass;

import cleancode.studycafe.my.model.Charge;
import cleancode.studycafe.my.model.StudyCafePassType;

public class StudyCafePass {

    private final StudyCafePassType passType;
    private final Charge charge;

    private StudyCafePass(StudyCafePassType passType, Charge charge) {
        this.passType = passType;
        this.charge = charge;
    }

    public static StudyCafePass of(StudyCafePassType passType, Charge charge) {
        return new StudyCafePass(passType, charge);
    }

    public StudyCafePassType getPassType() {
        return passType;
    }

    public int getDuration() {
        return charge.getDuration();
    }

    public int getPrice() {
        return charge.getPrice();
    }

    public double getDiscountRate() {
        return charge.getDiscountRate();
    }

    public String display() {
        return passType.format(this.charge);
    }
}
