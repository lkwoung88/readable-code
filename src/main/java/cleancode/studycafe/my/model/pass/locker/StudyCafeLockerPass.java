package cleancode.studycafe.my.model.pass.locker;

import cleancode.studycafe.my.model.Charge;
import cleancode.studycafe.my.model.StudyCafePassType;
import cleancode.studycafe.my.model.pass.StudyCafePass;

public class StudyCafeLockerPass implements StudyCafePass {

    private static final StudyCafeLockerPass EMPTY_LOCKER_PASS = new StudyCafeLockerPass(null, Charge.EMPTY_CHARGE);

    private final StudyCafePassType passType;
    private final Charge charge;

    private StudyCafeLockerPass(StudyCafePassType passType, Charge charge) {
        this.passType = passType;
        this.charge = charge;
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, Charge charge) {
        return new StudyCafeLockerPass(passType, charge);
    }

    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }

    @Override
    public Charge getCharge() {
        return charge;
    }
}
