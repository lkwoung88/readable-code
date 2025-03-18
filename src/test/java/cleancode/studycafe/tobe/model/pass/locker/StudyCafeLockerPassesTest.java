package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafeLockerPassesTest {

    @Test
    @DisplayName("특정 좌석 패스와 같은 기간의 사물함 패스를 찾는다.")
    void findLockerPassBy_PositiveCase() {
        // given
        LockerPassFileReader lockerPassFileReader = new LockerPassFileReader();
        StudyCafeLockerPasses lockerPasses = lockerPassFileReader.getLockerPasses();

        // when
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);
        Optional<StudyCafeLockerPass> lockerPassBy = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(lockerPassBy).isPresent();
        assertThat(lockerPassBy.get().getPassType()).isEqualTo(StudyCafePassType.FIXED);
        assertThat(lockerPassBy.get().getDuration()).isEqualTo(4);
    }

    @Test
    @DisplayName("특정 좌석 패스와 같은 기간의 사물함 패스가 없는 경우 빈 Optional을 반환한다.")
    void findLockerPassBy_NegativeCase() {
        // given
        LockerPassFileReader lockerPassFileReader = new LockerPassFileReader();
        StudyCafeLockerPasses lockerPasses = lockerPassFileReader.getLockerPasses();

        // when
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 150000, 0.1);
        Optional<StudyCafeLockerPass> lockerPassBy = lockerPasses.findLockerPassBy(seatPass);

        // then
        assertThat(lockerPassBy).isEmpty();
    }
}
