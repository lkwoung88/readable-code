package cleancode.studycafe.my.model.locker;

import cleancode.studycafe.my.model.pass.StudyCafePass;

import java.util.List;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> studyCafeLockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        this.studyCafeLockerPasses = studyCafeLockerPasses;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        return new StudyCafeLockerPasses(studyCafeLockerPasses);
    }

    public StudyCafeLockerPass getLockerPass(StudyCafePass selectedPass) {
        return this.studyCafeLockerPasses.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst()
            .orElse(null);
    }
}
