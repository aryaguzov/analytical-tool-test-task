package record;

import java.time.LocalDate;

public class RecordWithC extends AbstractRecord {

    public RecordWithC(String line) {
        super(line);
    }

    public LocalDate getDate() {
        return LocalDate.parse(allRecords[4], formatter);
    }

    public int getWaitingTime() {
        return Integer.parseInt(allRecords[5]);
    }
}
