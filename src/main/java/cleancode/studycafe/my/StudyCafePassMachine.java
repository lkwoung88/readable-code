package cleancode.studycafe.my;

import cleancode.studycafe.my.exception.AppException;
import cleancode.studycafe.my.io.InputHandler;
import cleancode.studycafe.my.io.OutputHandler;
import cleancode.studycafe.my.io.filereader.StudyCafeLockerPassFileHandler;
import cleancode.studycafe.my.io.filereader.StudyCafePassFileHandler;
import cleancode.studycafe.my.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.my.model.StudyCafePassType;
import cleancode.studycafe.my.model.locker.StudyCafeLockerPasses;
import cleancode.studycafe.my.model.pass.StudyCafePass;
import cleancode.studycafe.my.model.pass.StudyCafePasses;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

    private StudyCafePasses studyCafePasses;
    private StudyCafeLockerPasses lockerPasses;

    public void initialize(){
        StudyCafeLockerPassFileHandler studyCafeLockerPassFileHandler = new StudyCafeLockerPassFileHandler();
        StudyCafePassFileHandler studyCafeFileHandler = new StudyCafePassFileHandler();

        this.studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        this.lockerPasses = studyCafeLockerPassFileHandler.readLockerPasses();
    }

    public void run() {
        try {
            this.initialize();

            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            String passTypeSelectingUserAction = inputHandler.getPassTypeSelectingUserAction();
            StudyCafePassType studyCafePassType = StudyCafePassType.fromSelection(passTypeSelectingUserAction);

            List<StudyCafePass> cafePasses = studyCafePasses.getList(studyCafePassType);

            outputHandler.showPassListForSelection(cafePasses);

            int selectPassIndex = inputHandler.getSelectPassIndex();
            StudyCafePass selectedPass = cafePasses.get(selectPassIndex);

            Optional<StudyCafeLockerPass> lockerPass = lockerPasses.getLockerPass(selectedPass);

            lockerPass.ifPresentOrElse(
                pass -> {
                    outputHandler.askLockerPass(lockerPass.get());
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass.get());
                },
                () -> outputHandler.showPassOrderSummary(selectedPass)
            );

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}
