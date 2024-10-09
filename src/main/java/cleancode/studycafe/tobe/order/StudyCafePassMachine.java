package cleancode.studycafe.tobe.order;

import cleancode.studycafe.tobe.order.config.FilePathConfig;
import cleancode.studycafe.tobe.order.config.StudyCafeHandlerFactory;
import cleancode.studycafe.tobe.order.exception.AppException;
import cleancode.studycafe.tobe.order.io.InputHandler;
import cleancode.studycafe.tobe.order.io.OutputHandler;
import cleancode.studycafe.tobe.order.io.file.FileReadHandler;
import cleancode.studycafe.tobe.order.io.file.LockerFileParserHandler;
import cleancode.studycafe.tobe.order.io.file.PassFileParserHandler;
import cleancode.studycafe.tobe.order.model.*;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final FileReadHandler fileHandler;
    private final PassFileParserHandler passFileParserHandler;
    private final LockerFileParserHandler lockerFileParserHandler;
    private OrderProcessStatus orderProcessStatus;

    public StudyCafePassMachine() {
        this.inputHandler = StudyCafeHandlerFactory.createInputHandler();
        this.outputHandler = StudyCafeHandlerFactory.createOutputHandler();
        this.fileHandler = StudyCafeHandlerFactory.createFileReadHandler();
        this.passFileParserHandler = StudyCafeHandlerFactory.createPassFileParserHandler();
        this.lockerFileParserHandler = StudyCafeHandlerFactory.createLockerFileParserHandler();
        this.orderProcessStatus = OrderProcessStatus.IN_PROGRESS;
    }

    public void run() {
        while (isProgress()) {
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

    private boolean isProgress() {
        return orderProcessStatus == OrderProcessStatus.IN_PROGRESS;
    }

    private StudyCafePassType getStudyCafePassType() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeFromUser();
    }

    private Order processForOrderPassBasedOn(StudyCafePassType passType) {
        List<StudyCafePass> studyCafePasses = readPassFile();

        StudyCafePass selectedPass = selectPass(studyCafePasses, passType);

        if(passType.doesNotUseLocker()){
            return Order.of(selectedPass, null);
        }
        return processForOrderLocker(selectedPass);
    }

    private List<StudyCafePass> readPassFile() {
        List<String> passListString = fileHandler.readFile(FilePathConfig.PASS_LIST_PATH);
        return passFileParserHandler.parse(passListString);
    }

    private StudyCafePass selectPass(List<StudyCafePass> studyCafePasses, StudyCafePassType passType){
        List<StudyCafePass> filteredPassesByType = getPassesByType(studyCafePasses, passType);

        outputHandler.showPassListForSelection(filteredPassesByType);
        return inputHandler.getSelectingPassFromUser(filteredPassesByType);
    }

    private List<StudyCafePass> getPassesByType(List<StudyCafePass> studyCafePasses, StudyCafePassType passType) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> doesUserChoose(passType, studyCafePass.getPassType()))
                .toList();
    }

    private static boolean doesUserChoose(StudyCafePassType passType, StudyCafePassType userSelectPass) {
        return passType == userSelectPass;
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

    private static boolean doseNotLockerTicketExist(StudyCafeLockerTicket lockerTicket) {
        return !doesLockerTicketExist(lockerTicket);
    }

    private static boolean doesLockerTicketExist(StudyCafeLockerTicket lockerTicket) {
        return lockerTicket != null;
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

    private boolean doesUserUseLocker() {
        return inputHandler.getSelectingLockerTicketFromUser();
    }

    private void completeOrder(Order order){
        outputHandler.showPassOrderSummary(order);

        orderProcessStatus = OrderProcessStatus.COMPLETE;
    }

}
