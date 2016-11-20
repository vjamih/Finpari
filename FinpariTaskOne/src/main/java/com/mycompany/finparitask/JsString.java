package com.mycompany.finparitask;

public class JsString implements JsAbstract {

    private String value;

    public JsString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
