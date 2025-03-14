package ast.types;

import java.util.ArrayList;
import java.util.List;

public class RecordType implements Type {
    private List<RecordField> recordFields;

    public RecordType(List<RecordField> recordFields) {
        if (recordFields == null) {
            throw new IllegalArgumentException("recordFields is null");
        }
        this.recordFields = new ArrayList<>(recordFields);
    }
}
