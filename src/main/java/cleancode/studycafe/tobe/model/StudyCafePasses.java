package cleancode.studycafe.tobe.model;

import java.util.List;

public class StudyCafePasses {

    private final List<StudyCafePass> passes;

    private StudyCafePasses(List<StudyCafePass> passes) {
        this.passes = passes;
    }

    public static StudyCafePasses of(List<StudyCafePass> passes) {
        return new StudyCafePasses(passes);
    }

    public List<StudyCafePass> findPassesBy(StudyCafePassType passType) {
        return passes.stream()
            .filter(studyCafePass -> studyCafePass.isSamePassType(passType))
            .toList();
    }
}
