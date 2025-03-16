package cleancode.studycafe.my.model.locker;

import cleancode.studycafe.my.model.seat.StudyCafeSeatPass;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> studyCafeLockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        this.studyCafeLockerPasses = studyCafeLockerPasses;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        return new StudyCafeLockerPasses(studyCafeLockerPasses);
    }

    public Optional<StudyCafeLockerPass> getLockerPass(StudyCafeSeatPass selectedPass) {
        return this.studyCafeLockerPasses.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getCharge().getDuration() == selectedPass.getCharge().getDuration()
            )
            .findFirst();
    }
}
