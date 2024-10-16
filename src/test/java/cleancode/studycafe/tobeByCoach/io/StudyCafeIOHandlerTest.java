package cleancode.studycafe.tobeByCoach.io;

import cleancode.studycafe.tobeByCoach.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobeByCoach.model.pass.StudyCafePassType;
import cleancode.studycafe.tobeByCoach.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobeByCoach.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudyCafeIOHandlerTest {
    private StudyCafeIOHandler ioHandler;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = out;

    @BeforeEach
    void setUp() {
        ioHandler = new StudyCafeIOHandler();
        setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void rollback() {
        setOut(originalOut);
    }

    @DisplayName("웰컴 메세지가 정상적으로 출력된다.")
    @Test
    void showWelcomeMessage() {
        //given & when
        ioHandler.showWelcomeMessage();

        //then
        assertEquals("*** 프리미엄 스터디카페 ***", getLastConsoleOutput());

    }

    @DisplayName("안내 메세지가 정상적으로 출력된다.")
    @Test
    void showAnnouncement() {
        //given & when
        ioHandler.showAnnouncement();

        //then
        assertEquals("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)"
                        + lineSeparator()
                        + "* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)"
                , getLastConsoleOutput());

    }

    @DisplayName("주문 내역에 대한 요약이 정상적으로 출력된다.")
    @Test
    void showPassOrderSummary() {
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 10, 12000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 10, 5000);

        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        //when
        ioHandler.showPassOrderSummary(order);

        //then
        assertEquals("이용 내역"
                        + lineSeparator()
                        + "이용권: "
                        + "10주권 - 12000원"
                        + lineSeparator()
                        + "사물함: "
                        + "10주권 - 5000원"
                        + lineSeparator()
                        + "이벤트 할인 금액: "
                        + "1200원"
                        + lineSeparator()
                        + "총 결제 금액: "
                        + "15800원"
                , getLastConsoleOutput()
        );

    }

    @DisplayName("사물함이 없고 할인액이 없는 주문 내역에 대한 요약이 정상적으로 출력된다.")
    @Test
    void showPassOrderSummaryWithoutLockerAndDiscountPrice() {
        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4000, 0);

        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

        //when
        ioHandler.showPassOrderSummary(order);

        //then
        assertEquals("이용 내역"
                        + lineSeparator()
                        + "이용권: "
                        + "2시간권 - 4000원"
                        + lineSeparator()
                        + "총 결제 금액: "
                        + "4000원"
                , getLastConsoleOutput()
        );

    }

    private String getLastConsoleOutput() {
        return outputStream.toString().trim();
    }

}