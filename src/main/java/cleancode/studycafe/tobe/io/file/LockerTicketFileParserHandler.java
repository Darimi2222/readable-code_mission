package cleancode.studycafe.tobe.io.file;

import cleancode.studycafe.tobe.model.StudyCafeLockerTicket;
import cleancode.studycafe.tobe.model.StudyCafePass;

import java.util.List;

public interface LockerTicketFileParserHandler {

    List<StudyCafeLockerTicket> parse(List<String> lines);

}
