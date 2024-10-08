package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public interface InputHandler {

    StudyCafePassType getPassTypeFromUser();

    StudyCafePass getSelectingPassFromUser(List<StudyCafePass> passes);

    boolean getSelectingLockerTicketFromUser();

}
