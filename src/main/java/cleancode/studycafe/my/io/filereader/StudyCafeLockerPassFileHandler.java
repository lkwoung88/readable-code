package cleancode.studycafe.my.io.filereader;

import cleancode.studycafe.my.model.Charge;
import cleancode.studycafe.my.model.StudyCafePassType;
import cleancode.studycafe.my.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.my.model.pass.locker.StudyCafeLockerPasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeLockerPassFileHandler {

    private static final String STUDYCAFE_LOCKER_CSV = "src/main/resources/cleancode/studycafe/locker.csv";

    public StudyCafeLockerPasses readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(STUDYCAFE_LOCKER_CSV));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, Charge.of(duration, price, 0));
                lockerPasses.add(lockerPass);
            }

            return StudyCafeLockerPasses.of(lockerPasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }
}
