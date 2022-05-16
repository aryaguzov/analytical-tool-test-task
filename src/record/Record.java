package record;

import util.RecordUtil;

import java.util.List;

public class Record {

    private final List<String> lines;

    public Record(String fileName) {
        this.lines = RecordUtil.readData(fileName);
    }

    public List<String> getLines() {
        return lines;
    }
}
