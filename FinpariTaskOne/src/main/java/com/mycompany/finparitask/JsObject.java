package com.mycompany.finparitask;

import java.util.Map;

public class JsObject implements JsAbstract {

    private Map<String, JsAbstract> fields;

    public JsObject(Map<String, JsAbstract> fields) {
        this.fields = fields;
    }

    public Map<String, JsAbstract> getFields() {
        return fields;
    }
}
