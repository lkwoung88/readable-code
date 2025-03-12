package cleancode.studycafe.my;

import cleancode.studycafe.my.exception.AppException;
import cleancode.studycafe.my.io.InputHandler;
import cleancode.studycafe.my.io.OutputHandler;
import cleancode.studycafe.my.io.StudyCafeFileHandler;
import cleancode.studycafe.my.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.my.model.StudyCafePassType;
import cleancode.studycafe.my.model.pass.StudyCafePass;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

    public void run() {
        try {
            StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
            List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
            List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();

            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            String passTypeSelectingUserAction = inputHandler.getPassTypeSelectingUserAction();
            StudyCafePassType studyCafePassType = StudyCafePassType.fromSelection(passTypeSelectingUserAction);

            List<StudyCafePass> cafePasses = getList(studyCafePasses, studyCafePassType);

            outputHandler.showPassListForSelection(cafePasses);

            int selectPassIndex = inputHandler.getSelectPassIndex();
            StudyCafePass selectedPass = cafePasses.get(selectPassIndex);

            StudyCafeLockerPass lockerPass = getLockerPass(lockerPasses, selectedPass);

            boolean lockerSelection = false;

            if (lockerPass != null) {
                outputHandler.askLockerPass(lockerPass);
                lockerSelection = inputHandler.getLockerSelection();
            }

            if (lockerSelection) {
                outputHandler.showPassOrderSummary(selectedPass, lockerPass);
            } else {
                outputHandler.showPassOrderSummary(selectedPass, null);
            }

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private static StudyCafeLockerPass getLockerPass(List<StudyCafeLockerPass> lockerPasses, StudyCafePass selectedPass) {
        return lockerPasses.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst()
            .orElse(null);
    }

    private static List<StudyCafePass> getList(List<StudyCafePass> studyCafePasses, StudyCafePassType passType) {
        return studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType().equals(passType))
            .toList();
    }

}
