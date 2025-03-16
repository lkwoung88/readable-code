package cleancode.studycafe.my.model.pass.seat;

import cleancode.studycafe.my.model.Charge;
import cleancode.studycafe.my.model.StudyCafePassType;
import cleancode.studycafe.my.model.pass.StudyCafePass;

public class StudyCafeSeatPass implements StudyCafePass {

    private final StudyCafePassType passType;
    private final Charge charge;

    private StudyCafeSeatPass(StudyCafePassType passType, Charge charge) {
        this.passType = passType;
        this.charge = charge;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, Charge charge) {
        return new StudyCafeSeatPass(passType, charge);
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
