package com.mycompany.finparitask;

import java.util.List;

public class JsArray implements JsAbstract {

    private List<JsAbstract> elements;

    public JsArray(List<JsAbstract> elements) {
        this.elements = elements;
    }

    public List<JsAbstract> getElements() {
        return elements;
    }
}
