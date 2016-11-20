package com.mycompany.finparitask;

public class JsNumber implements JsAbstract {

    private double value;

    public JsNumber(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
