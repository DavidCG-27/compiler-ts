package ast.types;

import visitor.Visitor;

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

    public List<RecordField> getRecordFields() {
        return recordFields;
    }
    @Override
    public <RT, PT> RT accept(Visitor<RT, PT> v, PT p) {
        return v.visit(this,p);
    }
}
