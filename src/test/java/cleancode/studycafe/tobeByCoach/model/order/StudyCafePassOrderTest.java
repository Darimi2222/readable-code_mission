package cleancode.studycafe.tobeByCoach.model.order;

import cleancode.studycafe.tobeByCoach.model.pass.StudyCafePassType;
import cleancode.studycafe.tobeByCoach.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobeByCoach.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudyCafePassOrderTest {

    @DisplayName("주문 금액의 할인 액수를 계산해 반환한다.")
    @Test
    void calculateDiscountPrice(){
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 10 ,12000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 10, 5000);

        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        //when
        int discountPrice = order.getDiscountPrice();

        //then
        assertEquals(1200, discountPrice);
    }

    @DisplayName("주문한 패스권과 사물함 이용에 대한 총 액수를 계산해 반환한다.")
    @Test
    void calculateTotalPriceWithLocker(){
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 10 ,12000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 10, 5000);

        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        //when
        int totalPrice = order.getTotalPrice();

        //then
        assertEquals(15800, totalPrice);

    }

    @DisplayName("사물함을 이용하지 않는 패스권 주문 시의 총 액수를 계산해 반환한다.")
    @Test
    void calculateTotalPriceWithoutLocker(){
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 10 ,12000, 0.1);

        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

        //when
        int totalPrice = order.getTotalPrice();

        //then
        assertEquals(10800, totalPrice);

    }



}