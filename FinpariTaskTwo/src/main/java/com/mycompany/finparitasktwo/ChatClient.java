package com.mycompany.finparitasktwo;

import akka.actor.*;
import java.util.List;

public class ChatClient extends UntypedActor {

    private Сlient client;

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof Сlient) {
            Сlient client = (Сlient) o;
            this.client = client;
        }
        if (o instanceof Message) {
            Message message = (Message) o;
            client.updateChats(message);
        }
        if (o instanceof PublicMessage) {
            PublicMessage publicMessage = (PublicMessage) o;
            client.updatePublicChats(publicMessage);
        }
        if (o instanceof List) {
            List clientsList = (List) o;
            client.setKlientsList(clientsList);
        }
    }
}
