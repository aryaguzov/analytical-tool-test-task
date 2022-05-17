package util;

import record.RecordWithC;
import record.RecordWithD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class FilterUtil {

    private FilterUtil() {
    }

    public static void filterAndOutput(List<RecordWithC> records, RecordWithD recordWithD) {
        List<RecordWithC> detectedRecords = new ArrayList<>();
        records.forEach(recordWithC -> {
            if (filterByAll(recordWithC, recordWithD)) {
                detectedRecords.add(recordWithC);
            }
        });

        outputAverageWaitingTime(detectedRecords);
        outputEmpty(detectedRecords);
    }

    private static boolean filterByAll(RecordWithC recordWithC, RecordWithD recordWithD) {
        return filterByService(recordWithC, recordWithD) && filterByQuestion(recordWithC, recordWithD)
                && filterByResponse(recordWithC, recordWithD) && filterByDate(recordWithC, recordWithD);
    }

    private static boolean filterByService(RecordWithC recordWithC, RecordWithD recordWithD) {
        if (recordWithD.getService()[0].equals("*")) {
            return true;
        } else if (recordWithD.getService().length == 2) {
            return isEqual(recordWithD.getService(), recordWithC.getService());
        } else {
            return isEqual(recordWithD.getService()[0], (recordWithC.getService()[0]));
        }
    }

    private static boolean filterByQuestion(RecordWithC recordWithC, RecordWithD recordWithD) {
        if (recordWithD.getQuestion()[0].equals("*")) {
            return true;
        } else if (checkLength(recordWithD, 3)) {
            return isEqual(recordWithD.getQuestion(), recordWithC.getQuestion());
        } else if (checkLength(recordWithD, 2)) {
            return isEqual(recordWithD.getQuestion(), limitQuestion(recordWithC));
        } else {
            return isEqual(recordWithD.getQuestion()[0], (recordWithC.getQuestion()[0]));
        }
    }

    private static boolean filterByResponse(RecordWithC recordWithC, RecordWithD recordWithD) {
        return isEqual(recordWithD.getResponse(), (recordWithC.getResponse()));
    }

    private static boolean filterByDate(RecordWithC recordWithC, RecordWithD recordWithD) {
        if (recordWithD.getDateTo() == null) {
            return isEqual(recordWithC.getDate(), (recordWithD.getDateFrom()));
        } else {
            return (recordWithC.getDate().isAfter(recordWithD.getDateFrom())
                    || recordWithC.getDate().equals(recordWithD.getDateFrom()))
                    && (recordWithC.getDate().isBefore(recordWithD.getDateTo())
                    || recordWithC.getDate().equals(recordWithD.getDateTo()));
        }
    }

    private static void outputAverageWaitingTime(List<RecordWithC> detectedRecords) {
        OptionalDouble averageWaitingTime = detectedRecords.stream()
                .mapToInt(RecordWithC::getWaitingTime)
                .average();

        if (averageWaitingTime.isPresent()) {
            System.out.println(averageWaitingTime.getAsDouble());
        }
    }

    private static void outputEmpty(List<RecordWithC> detectedRecords) {
        if (detectedRecords.isEmpty()) {
            System.out.println("-");
        }
    }

    private static boolean checkLength(RecordWithD recordWithD, int length) {
        return recordWithD.getQuestion().length == length;
    }

    private static boolean isEqual(String[] type, String[] type2) {
        return Arrays.equals(type, type2);
    }

    private static boolean isEqual(Object type, Object type2) {
        return type.equals(type2);
    }

    private static String[] limitQuestion(RecordWithC recordWithC) {
        return Arrays.stream(recordWithC.getQuestion())
                .limit(2)
                .toArray(String[]::new);
    }
}
