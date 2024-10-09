package cleancode.studycafe.tobe.order.io.file;

import cleancode.studycafe.tobe.order.model.StudyCafeLockerTicket;

import java.util.List;

public interface LockerFileParserHandler {

    List<StudyCafeLockerTicket> parse(List<String> lines);

}
