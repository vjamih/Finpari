package com.mycompany.finparitasktwo;

import java.io.Serializable;

public class PublicMessage implements Serializable {

    private final Сlient sender;
    private final String text;

    public PublicMessage(Сlient sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Сlient getSender() {
        return sender;
    }
}
