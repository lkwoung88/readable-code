package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafeSeatPassesTest {

    @Test
    @DisplayName("특정 패스타입의 좌석 패스들을 찾는다.")
    void findPassesByPassType() {
        // given
        SeatPassFileReader seatPassFileReader = new SeatPassFileReader();
        StudyCafeSeatPasses seatPasses = seatPassFileReader.getSeatPasses();

        // when
        List<StudyCafeSeatPass> weeklyPasses = seatPasses.findPassesBy(StudyCafePassType.WEEKLY);

        // then
        weeklyPasses.forEach(studyCafePass -> assertThat(studyCafePass.getPassType())
            .isEqualTo(StudyCafePassType.WEEKLY));
    }

    @Test
    @DisplayName("존재하지 않는 패스권에 대해서는 빈 배열을 반환한다.")
    void findPassesBy() {
        // given
        SeatPassFileReader seatPassFileReader = new SeatPassFileReader();
        StudyCafeSeatPasses seatPasses = seatPassFileReader.getSeatPasses();

        // when
        List<StudyCafeSeatPass> weeklyPasses = seatPasses.findPassesBy(null);

        // then
        assertThat(weeklyPasses).isEmpty();
    }
}
