package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.Order;
import cleancode.studycafe.tobe.model.StudyCafeLockerTicket;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public interface OutputHandler {

    public void showWelcomeMessage();
    public void showAnnouncement();
    public void askPassTypeSelection();

    public void showPassListForSelection(List<StudyCafePass> passes);

    public void askLockerTicket(StudyCafeLockerTicket LockerTicket);

    public void showPassOrderSummary(Order order);

    public void showSimpleMessage(String message);
}
