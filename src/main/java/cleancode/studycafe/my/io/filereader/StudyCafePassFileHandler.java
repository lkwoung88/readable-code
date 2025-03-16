package cleancode.studycafe.my.io.filereader;

import cleancode.studycafe.my.model.Charge;
import cleancode.studycafe.my.model.StudyCafePassType;
import cleancode.studycafe.my.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.my.model.pass.seat.StudyCafeSeatPasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafePassFileHandler {

    private static final String STUDYCAFE_PASS_LIST_CSV = "src/main/resources/cleancode/studycafe/pass-list.csv";

    public StudyCafeSeatPasses readStudyCafePasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(STUDYCAFE_PASS_LIST_CSV));
            List<StudyCafeSeatPass> studyCafeSeatPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(studyCafePassType, Charge.of(duration, price, discountRate));
                studyCafeSeatPasses.add(studyCafeSeatPass);
            }

            return StudyCafeSeatPasses.of(studyCafeSeatPasses);
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

}
