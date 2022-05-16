package record;

import java.time.format.DateTimeFormatter;

public abstract class AbstractRecord {

    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    protected String[] allRecords;

    public AbstractRecord(String line) {
        this.allRecords = line.split(" ");
    }

    public String[] getService() {
        return allRecords[1].split("\\.");
    }

    public String[] getQuestion() {
        return allRecords[2].split("\\.");
    }

    public ResponseType getResponse() {
        return ResponseType.convert(allRecords[3]);
    }
}
