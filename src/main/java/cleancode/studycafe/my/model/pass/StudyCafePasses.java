package cleancode.studycafe.my.model.pass;

import cleancode.studycafe.my.model.Charge;
import cleancode.studycafe.my.model.StudyCafePassType;

import java.util.List;

public class StudyCafePasses {

    List<StudyCafePass> studyCafePasses;

    private StudyCafePasses(List<StudyCafePass> studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    public static StudyCafePasses of(List<StudyCafePass> studyCafePasses) {
        return new StudyCafePasses(studyCafePasses);
    }

    public List<StudyCafePass> getList(StudyCafePassType passType) {
        return this.studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType().equals(passType))
            .toList();
    }
}
