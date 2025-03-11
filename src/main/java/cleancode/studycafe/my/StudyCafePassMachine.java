package cleancode.studycafe.my;

import cleancode.studycafe.my.exception.AppException;
import cleancode.studycafe.my.io.InputHandler;
import cleancode.studycafe.my.io.OutputHandler;
import cleancode.studycafe.my.io.StudyCafeFileHandler;
import cleancode.studycafe.my.model.StudyCafeLockerPass;
import cleancode.studycafe.my.model.StudyCafePass;
import cleancode.studycafe.my.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

    public void run() {
        try {

            StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
            List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
            List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();

            while (true) {

                outputHandler.showWelcomeMessage();
                outputHandler.showAnnouncement();

                outputHandler.askPassTypeSelection();
                StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

                if (studyCafePassType == StudyCafePassType.HOURLY) {
                    List<StudyCafePass> hourlyPasses = getList(studyCafePasses, StudyCafePassType.HOURLY);

                    outputHandler.showPassListForSelection(hourlyPasses);

                    StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
                else if (studyCafePassType == StudyCafePassType.WEEKLY) {
                    List<StudyCafePass> weeklyPasses = getList(studyCafePasses, StudyCafePassType.WEEKLY);

                    outputHandler.showPassListForSelection(weeklyPasses);

                    StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
                else if (studyCafePassType == StudyCafePassType.FIXED) {
                    List<StudyCafePass> fixedPasses = getList(studyCafePasses, StudyCafePassType.FIXED);

                    outputHandler.showPassListForSelection(fixedPasses);

                    StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

                    StudyCafeLockerPass lockerPass = lockerPasses.stream()
                        .filter(option ->
                            option.getPassType() == selectedPass.getPassType()
                                && option.getDuration() == selectedPass.getDuration()
                        )
                        .findFirst()
                        .orElse(null);

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
                }

            }

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private static List<StudyCafePass> getList(List<StudyCafePass> studyCafePasses, StudyCafePassType passType) {
        return studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType().equals(passType))
            .toList();
    }

}
