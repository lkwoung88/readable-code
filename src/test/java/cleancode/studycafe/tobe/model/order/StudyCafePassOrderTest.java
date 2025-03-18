package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafePassOrderTest {

    @Test
    @DisplayName("좌석과 사물함 패스의 총 가격을 계산한다.")
    void getTotalPriceFromSeatAndLocker() {

        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);

        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(235000);
    }

    @Test
    @DisplayName("좌석 패스의 총 가격을 계산한다.")
    void getTotalPriceFromSeat() {

        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);

        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(225000);
    }
}
