package record;

import java.time.LocalDate;

public class RecordWithD extends AbstractRecord {

    public RecordWithD(String line) {
        super(line);
    }

    public LocalDate getDateFrom() {
        return LocalDate.parse(splitAndReturnDate(0), formatter);
    }

    public LocalDate getDateTo() {
        return checkDateLength() ? LocalDate.parse(splitAndReturnDate(1), formatter) : null;
    }

    private String splitAndReturnDate(int elementId) {
        return allRecords[4].split("-")[elementId];
    }

    private boolean checkDateLength() {
        return allRecords[4].split("-").length == 2;
    }
}
