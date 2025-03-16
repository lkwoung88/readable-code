package cleancode.studycafe.my;

import cleancode.studycafe.my.exception.AppException;
import cleancode.studycafe.my.io.StudyCafeIOHandler;
import cleancode.studycafe.my.io.provider.StudyCafeLockerPassFileHandler;
import cleancode.studycafe.my.io.provider.StudyCafeSeatPassFileHandler;
import cleancode.studycafe.my.model.order.StudyCafePassOrder;
import cleancode.studycafe.my.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.my.model.StudyCafePassType;
import cleancode.studycafe.my.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.my.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.my.model.pass.seat.StudyCafeSeatPasses;
import cleancode.studycafe.my.provider.LockerPassProvider;
import cleancode.studycafe.my.provider.SeatPassProvider;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final StudyCafeIOHandler ioHandler;
    private StudyCafeSeatPasses studyCafeSeatPasses;
    private StudyCafeLockerPasses lockerPasses;

    private final LockerPassProvider studyCafeLockerPassFileHandler;
    private final SeatPassProvider studyCafeFileHandler;

    public StudyCafePassMachine(StudyCafeIOHandler ioHandler, LockerPassProvider lockerPassProvider, SeatPassProvider seatPassProvider) {
        this.ioHandler = ioHandler;
        this.studyCafeLockerPassFileHandler = lockerPassProvider;
        this.studyCafeFileHandler = seatPassProvider;
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
        this.studyCafeSeatPasses = studyCafeFileHandler.getSeatPasses();
        this.lockerPasses = studyCafeLockerPassFileHandler.getLockerPasses();
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
