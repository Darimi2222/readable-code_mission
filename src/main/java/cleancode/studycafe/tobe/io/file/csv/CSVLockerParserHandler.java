package cleancode.studycafe.tobe.io.file.csv;

import cleancode.studycafe.tobe.io.file.LockerFileParserHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerTicket;
import cleancode.studycafe.tobe.model.StudyCafePassType;

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

            StudyCafeLockerTicket LockerTicket = StudyCafeLockerTicket.of(studyCafePassType, duration, price);
            lockerTickets.add(LockerTicket);
        }
        return lockerTickets;
    }

}
