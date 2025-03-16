package cleancode.studycafe.my;

import cleancode.studycafe.my.io.StudyCafeIOHandler;
import cleancode.studycafe.my.io.filereader.StudyCafeLockerPassFileHandler;
import cleancode.studycafe.my.io.filereader.StudyCafePassFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {

        StudyCafeIOHandler ioHandler = new StudyCafeIOHandler();
        StudyCafeLockerPassFileHandler studyCafeLockerPassFileHandler = new StudyCafeLockerPassFileHandler();
        StudyCafePassFileHandler studyCafeFileHandler = new StudyCafePassFileHandler();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(ioHandler, studyCafeLockerPassFileHandler, studyCafeFileHandler);
        studyCafePassMachine.run();
    }

}
