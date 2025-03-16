package cleancode.studycafe.my;

import cleancode.studycafe.my.exception.AppException;
import cleancode.studycafe.my.io.InputHandler;
import cleancode.studycafe.my.io.OutputHandler;
import cleancode.studycafe.my.io.filereader.StudyCafeLockerPassFileHandler;
import cleancode.studycafe.my.io.filereader.StudyCafePassFileHandler;
import cleancode.studycafe.my.model.order.StudyCafePassOrder;
import cleancode.studycafe.my.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.my.model.StudyCafePassType;
import cleancode.studycafe.my.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.my.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.my.model.pass.seat.StudyCafeSeatPasses;

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

            showWelcomeAnnounce();

            StudyCafePassType studyCafePassType = selectPassType();
            StudyCafeSeatPass selectedPass = selectSeatPass(studyCafePassType);
            Optional<StudyCafeLockerPass> lockerPass = selectLockerPass(selectedPass);
            StudyCafePassOrder passOrder = StudyCafePassOrder.of(selectedPass, lockerPass.orElse(null));

            showTotalPrice(passOrder);

        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePassType selectPassType() {
        outputHandler.askPassTypeSelection();
        String passTypeSelectingUserAction = inputHandler.getPassTypeSelectingUserAction();
        StudyCafePassType studyCafePassType = StudyCafePassType.fromSelection(passTypeSelectingUserAction);
        return studyCafePassType;
    }

    private StudyCafeSeatPass selectSeatPass(StudyCafePassType studyCafePassType) {
        List<StudyCafeSeatPass> seatPasses = studyCafeSeatPasses.getList(studyCafePassType);

        outputHandler.showPassListForSelection(seatPasses);

        int selectPassIndex = inputHandler.getSelectPassIndex();
        StudyCafeSeatPass selectedPass = seatPasses.get(selectPassIndex);
        return selectedPass;
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedPass) {
        Optional<StudyCafeLockerPass> lockerPass = lockerPasses.getLockerPass(selectedPass);

        if (lockerPass.isPresent()) {
            outputHandler.askLockerPass(lockerPass.get());
            boolean lockerSelection = inputHandler.getLockerSelection();
            return lockerSelection ? Optional.of(lockerPass.get()) : Optional.empty();
        }

        return Optional.empty();
    }

    private void showWelcomeAnnounce() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
    }

    private void showTotalPrice(StudyCafePassOrder passOrder) {
        outputHandler.showPassOrderSummary(passOrder);
    }

}
