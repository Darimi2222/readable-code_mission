package cleancode.studycafe.tobe.order.io;

import cleancode.studycafe.tobe.order.model.Order;
import cleancode.studycafe.tobe.order.model.StudyCafeLockerTicket;
import cleancode.studycafe.tobe.order.model.StudyCafePass;

import java.util.List;

public interface OutputHandler {

    void showWelcomeMessage();
    void showAnnouncement();
    void askPassTypeSelection();

    void showPassListForSelection(List<StudyCafePass> passes);

    void askLockerTicket(StudyCafeLockerTicket lockerTicket);

    void showPassOrderSummary(Order order);

    void showSimpleMessage(String message);
}
