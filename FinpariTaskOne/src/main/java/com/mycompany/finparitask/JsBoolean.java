package com.mycompany.finparitask;

public class JsBoolean implements JsAbstract {

    private boolean value;

    public JsBoolean(boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
