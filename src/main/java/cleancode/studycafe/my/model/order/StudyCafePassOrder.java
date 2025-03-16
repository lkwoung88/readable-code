package cleancode.studycafe.my.model.order;

import cleancode.studycafe.my.model.Charge;
import cleancode.studycafe.my.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.my.model.pass.locker.StudyCafeLockerPass;

import java.util.Optional;

import static cleancode.studycafe.my.model.Charge.EMPTY_CHARGE;

public class StudyCafePassOrder {

    private final StudyCafeSeatPass seatPass;
    private final StudyCafeLockerPass lockerPass;

    private StudyCafePassOrder(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass) {
        this.seatPass = seatPass;
        this.lockerPass = lockerPass;
    }

    public static StudyCafePassOrder of(StudyCafeSeatPass seatPass, StudyCafeLockerPass lockerPass) {
        if (lockerPass == null) {
            lockerPass = StudyCafeLockerPass.of(null,  EMPTY_CHARGE);
        }
        return new StudyCafePassOrder(seatPass, lockerPass);
    }

    public StudyCafeSeatPass getSeatPass() {
        return seatPass;
    }

    public Optional<StudyCafeLockerPass> getLockerPass() {
        return Optional.ofNullable(lockerPass);
    }

    public int getDiscountPrice() {
        Charge seatCharge = seatPass.getCharge();
        return seatCharge.getDiscountPrice();
    }

    public int getTotalPrice() {
        Charge seatCharge = seatPass.getCharge();
        Charge lockerCharge = lockerPass.getCharge();
        return seatCharge.getCharge() + lockerCharge.getCharge();
    }
}
