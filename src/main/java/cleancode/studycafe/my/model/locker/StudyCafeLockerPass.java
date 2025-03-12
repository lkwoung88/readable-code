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
        if (passType == StudyCafePassType.HOURLY) {
            return String.format("%s시간권 - %d원", charge.getDuration(), charge.getPrice());
        }
        if (passType == StudyCafePassType.WEEKLY) {
            return String.format("%s주권 - %d원", charge.getDuration(), charge.getPrice());
        }
        if (passType == StudyCafePassType.FIXED) {
            return String.format("%s주권 - %d원", charge.getDuration(), charge.getPrice());
        }
        return "";
    }

}
