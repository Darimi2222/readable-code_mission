package cleancode.studycafe.tobe.order.io.file;

import cleancode.studycafe.tobe.order.model.StudyCafePass;

import java.util.List;

public interface PassFileParserHandler {

    List<StudyCafePass> parse(List<String> lines);

}
