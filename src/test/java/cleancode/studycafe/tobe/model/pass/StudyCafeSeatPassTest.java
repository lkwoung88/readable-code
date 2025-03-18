package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StudyCafeSeatPassTest  {

    @Test
    @DisplayName("할인 정책에 따라 좌석의 할인 가격을 계산한다.")
    void getDiscountPrice_Positive() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);

        // when
        int discountPrice = seatPass.getDiscountPrice();

        // then
        assertThat(discountPrice).isEqualTo(25000);
    }

    @Test
    @DisplayName("할인 정책에 따라 좌석의 할인 가격을 계산한다.")
    void getDiscountPrice_Zero() {
        // given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4000, 0.0);

        // when
        int discountPrice = seatPass.getDiscountPrice();

        // then
        assertThat(discountPrice).isEqualTo(0);
    }
}
