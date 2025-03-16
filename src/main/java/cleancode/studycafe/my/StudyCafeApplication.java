package cleancode.studycafe.my;

import cleancode.studycafe.my.io.StudyCafeIOHandler;
import cleancode.studycafe.my.io.provider.StudyCafeLockerPassFileHandler;
import cleancode.studycafe.my.io.provider.StudyCafeSeatPassFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {

        StudyCafeIOHandler ioHandler = new StudyCafeIOHandler();
        StudyCafeLockerPassFileHandler studyCafeLockerPassFileHandler = new StudyCafeLockerPassFileHandler();
        StudyCafeSeatPassFileHandler studyCafeFileHandler = new StudyCafeSeatPassFileHandler();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(ioHandler, studyCafeLockerPassFileHandler, studyCafeFileHandler);
        studyCafePassMachine.run();
    }

}
