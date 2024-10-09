package cleancode.studycafe.tobe.order.io;

import cleancode.studycafe.tobe.order.exception.AppException;
import cleancode.studycafe.tobe.order.model.StudyCafePass;
import cleancode.studycafe.tobe.order.model.StudyCafePassType;

import java.util.List;
import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler{
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public StudyCafePassType getPassTypeFromUser() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return StudyCafePassType.FIXED;
        }

        throw new AppException("잘못된 입력입니다.");
    }

    @Override
    public StudyCafePass getSelectingPassFromUser(List<StudyCafePass> passes) {
        String userInput = SCANNER.nextLine();

        int selectedIndex = Integer.parseInt(userInput) - 1;
        if(selectedIndex < 0 || selectedIndex > passes.size() - 1){
            throw new AppException("잘못된 입력입니다.");
        }

        return passes.get(selectedIndex);
    }

    @Override
    public boolean getSelectingLockerTicketFromUser() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return true;
        }
        if ("2".equals(userInput)) {
            return false;
        }
        throw new AppException("잘못된 입력입니다.");
    }

}
