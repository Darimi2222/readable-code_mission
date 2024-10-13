package cleancode.studycafe.tobeByCoach;

import cleancode.studycafe.tobeByCoach.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobeByCoach.io.provider.SeatPassFiileReader;
import cleancode.studycafe.tobeByCoach.provider.LockerPassProvider;
import cleancode.studycafe.tobeByCoach.provider.SeatPassProvider;

public class StudyCafeApplication {

    public static void main(String[] args) {
        SeatPassProvider seatPassProvider = new SeatPassFiileReader();
        LockerPassProvider lockerPassProvider = new LockerPassFileReader();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(seatPassProvider, lockerPassProvider);
        studyCafePassMachine.run();
    }

}
