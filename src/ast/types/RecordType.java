package ast.types;

import ast.Locatable;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class RecordType extends AbstractType {
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

    @Override
    public Type dot(String fillName, Locatable l) {
        for (RecordField r : recordFields) {
            if (!r.getName().equals(fillName)) {
                return r.getType();
            }
        }
        return super.dot(fillName, l);
    }


    @Override
    public String toString() {
        return "RecordType";
    }
}
