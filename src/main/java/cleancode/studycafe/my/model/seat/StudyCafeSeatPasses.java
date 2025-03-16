package cleancode.studycafe.my.model.seat;

import cleancode.studycafe.my.model.StudyCafePassType;

import java.util.List;

public class StudyCafeSeatPasses {

    List<StudyCafeSeatPass> studyCafeSeatPasses;

    private StudyCafeSeatPasses(List<StudyCafeSeatPass> studyCafeSeatPasses) {
        this.studyCafeSeatPasses = studyCafeSeatPasses;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> studyCafeSeatPasses) {
        return new StudyCafeSeatPasses(studyCafeSeatPasses);
    }

    public List<StudyCafeSeatPass> getList(StudyCafePassType passType) {
        return this.studyCafeSeatPasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType().equals(passType))
            .toList();
    }
}
