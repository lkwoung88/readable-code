package cleancode.studycafe.my.model.locker;

import cleancode.studycafe.my.model.Charge;
import cleancode.studycafe.my.model.StudyCafePassType;

public class StudyCafeLockerPass {

    private final StudyCafePassType passType;
    private final Charge charge;

    private StudyCafeLockerPass(StudyCafePassType passType, Charge charge) {
        this.passType = passType;
        this.charge = charge;
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, Charge charge) {
        return new StudyCafeLockerPass(passType, charge);
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

    public String display() {
        return passType.format(this.charge);
    }

}
