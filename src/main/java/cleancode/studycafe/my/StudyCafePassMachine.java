package cleancode.studycafe.my;

import cleancode.studycafe.my.exception.AppException;
import cleancode.studycafe.my.io.InputHandler;
import cleancode.studycafe.my.io.OutputHandler;
import cleancode.studycafe.my.io.StudyCafeIOHandler;
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

    private final StudyCafeIOHandler ioHandler;
    private StudyCafeSeatPasses studyCafeSeatPasses;
    private StudyCafeLockerPasses lockerPasses;

    private final StudyCafeLockerPassFileHandler studyCafeLockerPassFileHandler;
    private final StudyCafePassFileHandler studyCafeFileHandler;

    public StudyCafePassMachine(StudyCafeIOHandler ioHandler, StudyCafeLockerPassFileHandler studyCafeLockerPassFileHandler, StudyCafePassFileHandler studyCafeFileHandler) {
        this.ioHandler = ioHandler;
        this.studyCafeLockerPassFileHandler = studyCafeLockerPassFileHandler;
        this.studyCafeFileHandler = studyCafeFileHandler;
    }

    public void run() {
        try {
            initialize();
            showWelcomeAnnounce();

            StudyCafePassType studyCafePassType = selectPassType();
            StudyCafeSeatPass selectedPass = selectSeatPass(studyCafePassType);
            Optional<StudyCafeLockerPass> lockerPass = selectLockerPass(selectedPass);
            StudyCafePassOrder passOrder = StudyCafePassOrder.of(selectedPass, lockerPass.orElse(null));

            showTotalPrice(passOrder);

        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void initialize() {
        this.studyCafeSeatPasses = studyCafeFileHandler.readStudyCafePasses();
        this.lockerPasses = studyCafeLockerPassFileHandler.readLockerPasses();
    }

    private StudyCafePassType selectPassType() {
        String passTypeSelectingUserAction = ioHandler.askPassTypeSelecting();
        StudyCafePassType studyCafePassType = StudyCafePassType.fromSelection(passTypeSelectingUserAction);
        return studyCafePassType;
    }

    private StudyCafeSeatPass selectSeatPass(StudyCafePassType studyCafePassType) {
        List<StudyCafeSeatPass> seatPasses = studyCafeSeatPasses.getList(studyCafePassType);

        int selectPassIndex = ioHandler.askSeatPassSelecting(seatPasses);
        StudyCafeSeatPass selectedPass = seatPasses.get(selectPassIndex);
        return selectedPass;
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedPass) {
        Optional<StudyCafeLockerPass> lockerPass = lockerPasses.getLockerPass(selectedPass);

        if (lockerPass.isPresent()) {
            boolean lockerSelection = ioHandler.askLockerPass(lockerPass.get());
            return lockerSelection ? Optional.of(lockerPass.get()) : Optional.empty();
        }

        return Optional.empty();
    }

    private void showWelcomeAnnounce() {
        ioHandler.showWelcomeMessage();
        ioHandler.showAnnouncement();
    }

    private void showTotalPrice(StudyCafePassOrder passOrder) {
        ioHandler.showPassOrderSummary(passOrder);
    }

}
