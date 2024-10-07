package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.file.FileReadHandler;
import cleancode.studycafe.tobe.io.file.LockerTicketFileParserHandler;
import cleancode.studycafe.tobe.io.file.PassFileParserHandler;
import cleancode.studycafe.tobe.model.Order;
import cleancode.studycafe.tobe.model.StudyCafeLockerTicket;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    public static final String PASS_LIST_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";
    public static final String LOCKER_TICKET_LIST_PATH = "src/main/resources/cleancode/studycafe/locker.csv";

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final FileReadHandler fileHandler;
    private final PassFileParserHandler passFileParserHandler;
    private final LockerTicketFileParserHandler lockerTicketFileParserHandler;

    public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler, FileReadHandler fileHandler, PassFileParserHandler passFileParserHandler, LockerTicketFileParserHandler lockerTicketFileParserHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.fileHandler = fileHandler;
        this.passFileParserHandler = passFileParserHandler;
        this.lockerTicketFileParserHandler = lockerTicketFileParserHandler;
    }

    public void run() {
        while(true) {
            try {
                outputHandler.showWelcomeMessage();
                outputHandler.showAnnouncement();

                outputHandler.askPassTypeSelection();
                StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

                orderPassBasedOn(studyCafePassType);
                break;
            } catch (AppException e) {
                outputHandler.showSimpleMessage(e.getMessage());
            } catch (Exception e) {
                outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
            }
        }
    }

    private void orderPassBasedOn(StudyCafePassType studyCafePassType) {
        List<String> passListString = fileHandler.readFile(PASS_LIST_PATH);
        List<StudyCafePass> studyCafePasses = passFileParserHandler.parse(passListString);

        if (doesUserChooseHourlyPass(studyCafePassType)) {
            Order orderHourlyPass = getFinalSelectedHourlyPass(studyCafePasses);
            outputHandler.showPassOrderSummary(orderHourlyPass);

            return;
        }
        if (doesUserChooseWeeklyPass(studyCafePassType)) {
            Order orderWeeklyPass = getFinalSelectedWeeklyPass(studyCafePasses);
            outputHandler.showPassOrderSummary(orderWeeklyPass);

            return;
        }
        if (doesUserChooseFixedPass(studyCafePassType)) {
            StudyCafePass selectedPass = getFinalSelectedFixedPass(studyCafePasses);

            List<String> lockerTicketListString = fileHandler.readFile(LOCKER_TICKET_LIST_PATH);
            List<StudyCafeLockerTicket> lockerTickets = lockerTicketFileParserHandler.parse(lockerTicketListString);
            StudyCafeLockerTicket lockerTicket = getLockerTicket(lockerTickets, selectedPass);

            if (doseNotLockerTicketExist(lockerTicket)) {
                return;
            }

            Order orderFixedPass = Order.ofUsedLocker(selectedPass, lockerTicket);

            outputHandler.askLockerTicket(lockerTicket);

            boolean lockerUsedOrNot = doesUserUseLocker();

            if (lockerUsedOrNot) {
                outputHandler.showPassOrderSummary(orderFixedPass);
            }
        }
    }

    private boolean doesUserUseLocker() {
        boolean lockerUsedOrNot;
        lockerUsedOrNot = inputHandler.getSelectLockerTicket();
        return lockerUsedOrNot;
    }

    private static List<StudyCafePass> getHourlyPasses(List<StudyCafePass> studyCafePasses) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> doesUserChooseHourlyPass(studyCafePass.getPassType()))
                .toList();
    }

    private static List<StudyCafePass> getWeeklyPasses(List<StudyCafePass> studyCafePasses) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> doesUserChooseWeeklyPass(studyCafePass.getPassType()))
                .toList();
    }

    private static List<StudyCafePass> getFixedPasses(List<StudyCafePass> studyCafePasses) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> doesUserChooseFixedPass(studyCafePass.getPassType()))
                .toList();
    }

    private static StudyCafeLockerTicket getLockerTicket(List<StudyCafeLockerTicket> lockerTickets, StudyCafePass selectedPass) {

        return lockerTickets.stream()
                .filter(selectedPass::typeAndDurationIsSame)
                .findFirst()
                .orElse(null);
    }

    private static boolean doesUserChooseHourlyPass(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.HOURLY;
    }

    private static boolean doesUserChooseWeeklyPass(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.WEEKLY;
    }

    private static boolean doesUserChooseFixedPass(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.FIXED;
    }

    private static boolean doesLockerTicketExist(StudyCafeLockerTicket lockerTicket) {
        return lockerTicket != null;
    }

    private static boolean doseNotLockerTicketExist(StudyCafeLockerTicket lockerTicket) {
        return !doesLockerTicketExist(lockerTicket);
    }

    private Order getFinalSelectedHourlyPass(List<StudyCafePass> studyCafePasses) {
        List<StudyCafePass> hourlyPasses = getHourlyPasses(studyCafePasses);
        outputHandler.showPassListForSelection(hourlyPasses);
        StudyCafePass selectPass = inputHandler.getSelectPass(hourlyPasses);
        return Order.of(selectPass);
    }

    private Order getFinalSelectedWeeklyPass(List<StudyCafePass> studyCafePasses) {
        List<StudyCafePass> weeklyPasses = getWeeklyPasses(studyCafePasses);
        outputHandler.showPassListForSelection(weeklyPasses);
        StudyCafePass selectPass = inputHandler.getSelectPass(weeklyPasses);
        return Order.of(selectPass);
    }

    private StudyCafePass getFinalSelectedFixedPass(List<StudyCafePass> studyCafePasses) {
        List<StudyCafePass> fixedPasses = getFixedPasses(studyCafePasses);
        outputHandler.showPassListForSelection(fixedPasses);
        return inputHandler.getSelectPass(fixedPasses);
    }
}
