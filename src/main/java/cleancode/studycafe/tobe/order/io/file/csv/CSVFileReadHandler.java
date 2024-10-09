package cleancode.studycafe.tobe.order.io.file.csv;

import cleancode.studycafe.tobe.order.exception.AppException;
import cleancode.studycafe.tobe.order.io.file.FileReadHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVFileReadHandler implements FileReadHandler {

    @Override
    public List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            throw new AppException("파일을 읽는데 실패했습니다.");
        }
    }

 }
