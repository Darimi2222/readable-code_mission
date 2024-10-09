package cleancode.studycafe.tobe.order.io;

import cleancode.studycafe.tobe.order.model.StudyCafePass;
import cleancode.studycafe.tobe.order.model.StudyCafePassType;

import java.util.List;

public interface InputHandler {

    StudyCafePassType getPassTypeFromUser();

    StudyCafePass getSelectingPassFromUser(List<StudyCafePass> passes);

    boolean getSelectingLockerTicketFromUser();

}
