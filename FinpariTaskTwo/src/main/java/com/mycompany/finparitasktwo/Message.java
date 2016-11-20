package com.mycompany.finparitasktwo;

import java.io.Serializable;

public class Message implements Serializable {

    private final Сlient sender;
    private final Сlient addressee;
    private final String text;

    public Message(Сlient sender, Сlient addressee, String text) {
        this.sender = sender;
        this.addressee = addressee;
        this.text = text;
    }

    public Сlient getSender() {
        return sender;
    }

    public Сlient getAddressee() {
        return addressee;
    }

    public String getText() {
        return text;
    }
}
