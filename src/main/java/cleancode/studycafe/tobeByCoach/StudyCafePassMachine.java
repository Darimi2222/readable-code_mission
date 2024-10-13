package cleancode.studycafe.tobeByCoach;

import cleancode.studycafe.tobeByCoach.exception.AppException;
import cleancode.studycafe.tobeByCoach.io.StudyCafeIOHandler;
import cleancode.studycafe.tobeByCoach.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobeByCoach.model.pass.*;
import cleancode.studycafe.tobeByCoach.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobeByCoach.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobeByCoach.provider.LockerPassProvider;
import cleancode.studycafe.tobeByCoach.provider.SeatPassProvider;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {
    private final StudyCafeIOHandler ioHandler = new StudyCafeIOHandler();
    private final SeatPassProvider seatPassProvider;
    private final LockerPassProvider lockerPassProvider;

    public StudyCafePassMachine(SeatPassProvider seatPassProvider, LockerPassProvider lockerPassProvider) {
        this.seatPassProvider = seatPassProvider;
        this.lockerPassProvider = lockerPassProvider;
    }

    public void run() {
        try {
            ioHandler.showWelcomeMessage();
            ioHandler.showAnnouncement();

            StudyCafeSeatPass selectedPass = selectPass();

            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);
            StudyCafePassOrder passOrder = StudyCafePassOrder.of(
                    selectedPass, optionalLockerPass.orElse(null)
            );

            ioHandler.showPassOrderSummary(passOrder);

        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeSeatPass selectPass() {
        StudyCafePassType passType = ioHandler.askPassTypeSelecting();

        List<StudyCafeSeatPass> passCandidates = findPassCandidatesBy(passType);

        return ioHandler.askPassSelecting(passCandidates);
    }

    private List<StudyCafeSeatPass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
        StudyCafeSeatPasses allPasses = seatPassProvider.getSeatPasses();
        return allPasses.findPassBy(studyCafePassType);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedPass) {
        if (selectedPass.cannotUserLocker()){
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> lockerPassCandidate = findLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate.isPresent()) {
            StudyCafeLockerPass lockerPass = lockerPassCandidate.get();

            boolean isLockerSelected = ioHandler.askLockerPass(lockerPass);

            if (isLockerSelected){
                return Optional.of(lockerPass);
            }
        }

        return Optional.empty();
    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidateBy(StudyCafeSeatPass pass) {
        StudyCafeLockerPasses allLockerPasses = lockerPassProvider.getLockerPasses();

        return allLockerPasses.findLockerPassBy(pass);
    }

}
