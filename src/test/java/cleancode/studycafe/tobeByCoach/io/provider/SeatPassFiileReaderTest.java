package cleancode.studycafe.tobeByCoach.io.provider;

import cleancode.studycafe.tobeByCoach.model.pass.StudyCafePassType;
import cleancode.studycafe.tobeByCoach.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobeByCoach.model.pass.StudyCafeSeatPasses;
import cleancode.studycafe.tobeByCoach.provider.SeatPassProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatPassFiileReaderTest {

    SeatPassProvider seatPassProvider;
    
    @BeforeEach
    void setup(){
        seatPassProvider = new SeatPassFiileReader();
    }

    @DisplayName("pass-list.csv 파일로부터 읽은 시간제 패스권들을 확인한다.")
    @Test
    void getHourlySeatPassesFromCSVFile() {
        //given
        List<StudyCafeSeatPass> hourlySeatPassList = List.of(
                StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4000, 0.0),
                StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 4, 6500, 0.0),
                StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 6, 9000, 0.0),
                StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 8, 11000, 0.0),
                StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 10, 12000, 0.0),
                StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 12, 13000, 0.0)
        );

        //when
        StudyCafeSeatPasses getSeatPasses = seatPassProvider.getSeatPasses();
        List<StudyCafeSeatPass> getHourlySeatPasses = getSeatPasses.findPassBy(StudyCafePassType.HOURLY);

        //then
        assertEquals(hourlySeatPassList.size() ,  getHourlySeatPasses.size());
        for (int i = 0; i < hourlySeatPassList.size(); i++) {
            StudyCafeSeatPass providedPass = hourlySeatPassList.get(i);
            StudyCafeSeatPass getPass = getHourlySeatPasses.get(i);

            assertEquals(providedPass.getPassType(), getPass.getPassType());
            assertEquals(providedPass.getDuration(), getPass.getDuration());
            assertEquals(providedPass.getPrice(), getPass.getPrice());
            assertEquals(providedPass.getDiscountPrice(), getPass.getDiscountPrice());
        }
    }

    @DisplayName("pass-list.csv 파일로부터 읽은 주간제 패스권들을 확인한다.")
    @Test
    void getWeeklySeatPassesFromCSVFile() {
        //given
        List<StudyCafeSeatPass> weeklySeatPassList = List.of(
                StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 1, 60000, 0.0),
                StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 100000, 0.1),
                StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 3, 130000, 0.1),
                StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 150000, 0.1),
                StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 400000, 0.15)
        );

        //when
        StudyCafeSeatPasses getSeatPasses = seatPassProvider.getSeatPasses();
        List<StudyCafeSeatPass> getWeeklySeatPasses = getSeatPasses.findPassBy(StudyCafePassType.WEEKLY);

        //then
        assertEquals(weeklySeatPassList.size() ,  getWeeklySeatPasses.size());
        for (int i = 0; i < weeklySeatPassList.size(); i++) {
            StudyCafeSeatPass providedPass = weeklySeatPassList.get(i);
            StudyCafeSeatPass getPass = getWeeklySeatPasses.get(i);

            assertEquals(providedPass.getPassType(), getPass.getPassType());
            assertEquals(providedPass.getDuration(), getPass.getDuration());
            assertEquals(providedPass.getPrice(), getPass.getPrice());
            assertEquals(providedPass.getDiscountPrice(), getPass.getDiscountPrice());
        }
    }

    @DisplayName("pass-list.csv 파일로부터 읽은 고정석 패스권들을 확인한다.")
    @Test
    void getFixedSeatPassesFromCSVFile() {
        //given
        List<StudyCafeSeatPass> fixedSeatPassList = List.of(
                StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1),
                StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15)
        );

        //when
        StudyCafeSeatPasses getSeatPasses = seatPassProvider.getSeatPasses();
        List<StudyCafeSeatPass> getFixedSeatPasses = getSeatPasses.findPassBy(StudyCafePassType.FIXED);

        //then
        assertEquals(fixedSeatPassList.size() ,  getFixedSeatPasses.size());
        for (int i = 0; i < fixedSeatPassList.size(); i++) {
            StudyCafeSeatPass providedPass = fixedSeatPassList.get(i);
            StudyCafeSeatPass getPass = getFixedSeatPasses.get(i);

            assertEquals(providedPass.getPassType(), getPass.getPassType());
            assertEquals(providedPass.getDuration(), getPass.getDuration());
            assertEquals(providedPass.getPrice(), getPass.getPrice());
            assertEquals(providedPass.getDiscountPrice(), getPass.getDiscountPrice());
        }
    }

}