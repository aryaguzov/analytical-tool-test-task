import record.Record;
import util.RecordUtil;

public class Main {
    public static void main(String[] args) {
        Record record = new Record("input.txt");
        RecordUtil.filterData(record);
    }
}
