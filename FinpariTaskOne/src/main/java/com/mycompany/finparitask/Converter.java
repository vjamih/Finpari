package com.mycompany.finparitask;

import java.util.Map;

public class Converter {

    public String convert(JsAbstract jsAbstract) {
        return ("{" + convertExtra(jsAbstract) + "}");
    }

    private String convertExtra(JsAbstract jsAbstract) {
        if (jsAbstract == null) {
            return "";
        }
        String jsString = "";
        switch (jsAbstract.getClass().getSimpleName()) {
            case ("JsNumber"):
                jsString = convertExtraForNumber(jsAbstract);
                break;
            case ("JsBoolean"):
                jsString = convertExtraForBoolean(jsAbstract);
                break;
            case ("JsString"):
                jsString = convertExtraForString(jsAbstract);
                break;
            case ("JsArray"):
                jsString = convertExtraForArray(jsAbstract);
                break;
            case ("JsObject"):
                jsString = convertExtraForObject(jsAbstract);
                break;
        }
        return jsString;
    }

    private String convertExtraForNumber(JsAbstract jsAbstract) {
        return "" + ((JsNumber) jsAbstract).getValue();
    }

    private String convertExtraForBoolean(JsAbstract jsAbstract) {
        return "" + ((JsBoolean) jsAbstract).isValue();
    }

    private String convertExtraForString(JsAbstract jsAbstract) {
        return "" + ((JsString) jsAbstract).getValue();
    }

    private String convertExtraForArray(JsAbstract jsAbstract) {
        String jsString = "[";
        for (JsAbstract currentJsAbstract : ((JsArray) jsAbstract).getElements()) {
            jsString += convertExtra(currentJsAbstract) + ",";
        }
        jsString = jsString.substring(0, jsString.length() - 1);
        jsString += "]";
        return jsString;
    }

    private String convertExtraForObject(JsAbstract jsAbstract) {
        String jsString = "{";
        for (Map.Entry<String, JsAbstract> entry : ((JsObject) jsAbstract).getFields().entrySet()) {
            jsString += entry.getKey() + ":" + convertExtra(entry.getValue()) + ",";
        }
        jsString = jsString.substring(0, jsString.length() - 1);
        jsString += "}";
        return jsString;
    }

}
