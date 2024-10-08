package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.FilePathConfig;
import cleancode.studycafe.tobe.config.StudyCafeHandlerFactory;
import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.file.FileReadHandler;
import cleancode.studycafe.tobe.io.file.LockerFileParserHandler;
import cleancode.studycafe.tobe.io.file.PassFileParserHandler;
import cleancode.studycafe.tobe.model.*;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final FileReadHandler fileHandler;
    private final PassFileParserHandler passFileParserHandler;
    private final LockerFileParserHandler lockerFileParserHandler;

    public StudyCafePassMachine() {
        this.inputHandler = StudyCafeHandlerFactory.createInputHandler();
        this.outputHandler = StudyCafeHandlerFactory.createOutputHandler();
        this.fileHandler = StudyCafeHandlerFactory.createFileReadHandler();
        this.passFileParserHandler = StudyCafeHandlerFactory.createPassFileParserHandler();
        this.lockerFileParserHandler = StudyCafeHandlerFactory.createLockerFileParserHandler();
    }

    public void run() {
        while (true) {
            try {
                outputHandler.showWelcomeMessage();
                outputHandler.showAnnouncement();

                StudyCafePassType studyCafePassType = getStudyCafePassType();

                Order order = processForOrderPassBasedOn(studyCafePassType);
                completeOrder(order);
                break;
            } catch (AppException e) {
                outputHandler.showSimpleMessage(e.getMessage());
            } catch (Exception e) {
                outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
            }
        }
    }

    private StudyCafePassType getStudyCafePassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeFromUser();
    }

    private Order processForOrderPassBasedOn(StudyCafePassType passType) {
        List<StudyCafePass> studyCafePasses = readPassFile();

        StudyCafePass selectedPass = getPass(studyCafePasses, passType);

        if(passType.getLockerRule() == LockerRule.INACTIVE){
            return Order.of(selectedPass, null);
        }
        return processForOrderLocker(selectedPass);
    }

    private Order processForOrderLocker(StudyCafePass selectedPass) {
        StudyCafeLockerTicket lockerTicket = getLockerTicket(selectedPass);

        if (doseNotLockerTicketExist(lockerTicket)){
            return Order.of(selectedPass, null);
        }

        outputHandler.askLockerTicket(lockerTicket);
        if (doesUserUseLocker()){
             return Order.of(selectedPass, lockerTicket);
        }
        return Order.of(selectedPass, null);
    }

    private void completeOrder(Order order){
        outputHandler.showPassOrderSummary(order);
    }

    private List<StudyCafePass> readPassFile() {
        List<String> passListString = fileHandler.readFile(FilePathConfig.PASS_LIST_PATH);
        return passFileParserHandler.parse(passListString);
    }

    private StudyCafePass getPass(List<StudyCafePass> studyCafePasses, StudyCafePassType passType){
        List<StudyCafePass> filteredPassesByType = getPassesByType(studyCafePasses, passType);

        outputHandler.showPassListForSelection(filteredPassesByType);
        return inputHandler.getSelectingPassFromUser(filteredPassesByType);
    }

    private boolean doesUserUseLocker() {
        return inputHandler.getSelectingLockerTicketFromUser();
    }

    private List<StudyCafePass> getPassesByType(List<StudyCafePass> studyCafePasses, StudyCafePassType passType) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> doesUserChoose(passType, studyCafePass.getPassType()))
                .toList();
    }

    private StudyCafeLockerTicket getLockerTicket(StudyCafePass selectedPass) {
        List<StudyCafeLockerTicket> lockerTickets = readLockerFile();

        return lockerTickets.stream()
                .filter(selectedPass::typeAndDurationIsSame)
                .findFirst()
                .orElse(null);
    }

    private List<StudyCafeLockerTicket> readLockerFile() {
        List<String> lockerListString = fileHandler.readFile(FilePathConfig.LOCKER_LIST_PATH);
        return lockerFileParserHandler.parse(lockerListString);
    }

    private static boolean doesUserChoose(StudyCafePassType passType, StudyCafePassType userSelectPass) {
        return passType == userSelectPass;
    }

    private static boolean doesLockerTicketExist(StudyCafeLockerTicket lockerTicket) {
        return lockerTicket != null;
    }

    private static boolean doseNotLockerTicketExist(StudyCafeLockerTicket lockerTicket) {
        return !doesLockerTicketExist(lockerTicket);
    }

}
