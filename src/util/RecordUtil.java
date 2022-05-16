package util;

import record.Record;
import record.RecordWithC;
import record.RecordWithD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecordUtil {

    private RecordUtil() {
    }

    public static List<String> readData(String fileName) {
        List<String> lines = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String inputLine = bufferedReader.readLine();
            while (inputLine != null) {
                lines.add(inputLine);
                if (lines.size() == 1) {
                    checkTotalLinesLength(lines);
                }
                inputLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void filterData(Record records) {
        List<RecordWithC> recordsWithC = new ArrayList<>();

        records.getLines()
                .forEach(record -> {
                    if (record.charAt(0) == 'C') {
                        recordsWithC.add(new RecordWithC(record));
                    }

                    if (record.charAt(0) == 'D') {
                        RecordWithD recordWithD = new RecordWithD(record);
                        FilterUtil.filterAndOutput(recordsWithC, recordWithD);
                    }
                });
    }

    private static void checkTotalLinesLength(List<String> lines) {
        if (Integer.parseInt(lines.get(0)) > 100000) {
            throw new RuntimeException("Can't read a file which contains more than 100000 lines.");
        }
    }
}
