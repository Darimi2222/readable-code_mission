package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.StudyCafeLockerTicket;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.Scanner;

public interface InputHandler {

    StudyCafePassType getPassTypeSelectingUserAction();

    StudyCafePass getSelectPass(List<StudyCafePass> passes);

    public boolean getSelectLockerTicket();

}
