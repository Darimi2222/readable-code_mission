package cleancode.studycafe.tobe.order.io.file.csv;

import cleancode.studycafe.tobe.order.io.file.LockerFileParserHandler;
import cleancode.studycafe.tobe.order.model.StudyCafeLockerTicket;
import cleancode.studycafe.tobe.order.model.StudyCafePassType;

import java.util.ArrayList;
import java.util.List;

public class CSVLockerParserHandler implements LockerFileParserHandler {

    @Override
    public List<StudyCafeLockerTicket> parse(List<String> lines) {
        List<StudyCafeLockerTicket> lockerTickets = new ArrayList<>();
        for (String line : lines) {
            String[] values = line.split(",");
            StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
            int duration = Integer.parseInt(values[1]);
            int price = Integer.parseInt(values[2]);

            StudyCafeLockerTicket lockerTicket = StudyCafeLockerTicket.of(studyCafePassType, duration, price);
            lockerTickets.add(lockerTicket);
        }
        return lockerTickets;
    }

}
