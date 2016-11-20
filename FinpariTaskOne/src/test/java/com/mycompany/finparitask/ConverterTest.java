package com.mycompany.finparitask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {

    public ConverterTest() {
    }

    @Test
    public void testConvertForNull() {
        JsAbstract jsAbstract = null;
        Converter converter = new Converter();
        String expResult = "{}";
        String result = converter.convert(jsAbstract);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertForNumder() {
        JsAbstract jsAbstract = new JsNumber(23.42);
        Converter converter = new Converter();
        String expResult = "{23.42}";
        String result = converter.convert(jsAbstract);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertForBoolean() {
        JsAbstract jsAbstract = new JsBoolean(true);
        Converter converter = new Converter();
        String expResult = "{true}";
        String result = converter.convert(jsAbstract);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertForString() {
        JsAbstract jsAbstract = new JsString("Hello");
        Converter converter = new Converter();
        String expResult = "{Hello}";
        String result = converter.convert(jsAbstract);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertForArray() {
        List<JsAbstract> array = new ArrayList<>();
        array.add(new JsString("Name"));
        array.add(new JsNumber(22));
        array.add(new JsBoolean(true));
        JsAbstract jsAbstract = new JsArray(array);
        Converter converter = new Converter();
        String expResult = "{[Name,22.0,true]}";
        String result = converter.convert(jsAbstract);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertForObject() {
        Map<String, JsAbstract> object = new HashMap<>();
        object.put("Name", new JsString("Bohdan"));
        object.put("Age", new JsNumber(22));
        List<JsAbstract> array = new ArrayList<>();
        array.add(new JsString("Chess"));
        array.add(new JsString("Math"));
        object.put("Hobby", new JsArray(array));
        JsAbstract jsAbstract = new JsObject(object);
        Converter converter = new Converter();
        String expResult = "{{Name:Bohdan,Age:22.0,Hobby:[Chess,Math]}}";
        String result = converter.convert(jsAbstract);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertForAll() {
        Map<String, JsAbstract> object = new HashMap<>();
        object.put("Name", new JsString("Bohdan"));
        object.put("Age", new JsNumber(22));
        List<JsAbstract> array = new ArrayList<>();
        array.add(new JsString("Chess"));
        array.add(new JsString("Math"));
        Map<String, JsAbstract> favorite = new HashMap<>();
        favorite.put("Music", new JsString("Marilyn Manson"));
        favorite.put("Film", new JsString("Cloud Atlas"));
        array.add(new JsObject(favorite));
        object.put("Hobby", new JsArray(array));
        JsAbstract jsAbstract = new JsObject(object);
        Converter converter = new Converter();
        String expResult = "{{Name:Bohdan,Age:22.0,Hobby:[Chess,Math,{Music:Marilyn Manson,Film:Cloud Atlas}]}}";
        String result = converter.convert(jsAbstract);
        assertEquals(expResult, result);
    }

}
