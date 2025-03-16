package cleancode.studycafe.my;

import cleancode.studycafe.my.exception.AppException;
import cleancode.studycafe.my.io.InputHandler;
import cleancode.studycafe.my.io.OutputHandler;
import cleancode.studycafe.my.io.filereader.StudyCafeLockerPassFileHandler;
import cleancode.studycafe.my.io.filereader.StudyCafePassFileHandler;
import cleancode.studycafe.my.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.my.model.StudyCafePassType;
import cleancode.studycafe.my.model.locker.StudyCafeLockerPasses;
import cleancode.studycafe.my.model.seat.StudyCafeSeatPass;
import cleancode.studycafe.my.model.seat.StudyCafeSeatPasses;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

    private StudyCafeSeatPasses studyCafeSeatPasses;
    private StudyCafeLockerPasses lockerPasses;

    public void initialize(){
        StudyCafeLockerPassFileHandler studyCafeLockerPassFileHandler = new StudyCafeLockerPassFileHandler();
        StudyCafePassFileHandler studyCafeFileHandler = new StudyCafePassFileHandler();

        this.studyCafeSeatPasses = studyCafeFileHandler.readStudyCafePasses();
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

            List<StudyCafeSeatPass> cafePasses = studyCafeSeatPasses.getList(studyCafePassType);

            outputHandler.showPassListForSelection(cafePasses);

            int selectPassIndex = inputHandler.getSelectPassIndex();
            StudyCafeSeatPass selectedPass = cafePasses.get(selectPassIndex);

            Optional<StudyCafeLockerPass> lockerPass = lockerPasses.getLockerPass(selectedPass);

            lockerPass.ifPresentOrElse(
                pass -> {
                    outputHandler.askLockerPass(lockerPass.get());
                    boolean lockerSelection = inputHandler.getLockerSelection();
                    outputHandler.showPassOrderSummary(selectedPass,
                        lockerSelection? lockerPass.get() : null);
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
