package com.mycompany.finparitasktwo;

import akka.actor.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer extends UntypedActor {

    private List<Сlient> clientsList = new ArrayList<>();

    private List<Message> messageDataBase = new ArrayList<>();
    private List<PublicMessage> publicMessageDataBase = new ArrayList<>();

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof Message) {
            Message message = (Message) o;
            messageDataBase.add(message);
            (message.getSender().getActorRef()).tell(message, message.getSender().getActorRef());
            if (message.getSender() != message.getAddressee()) {
                (message.getAddressee().getActorRef()).tell(message, message.getSender().getActorRef());
            }
        }
        if (o instanceof PublicMessage) {
            PublicMessage publicMessage = (PublicMessage) o;
            publicMessageDataBase.add(publicMessage);
            for (Сlient client : clientsList) {
                (client.getActorRef()).tell(publicMessage, publicMessage.getSender().getActorRef());
            }
        }
        if (o instanceof List) {
            List list = new ArrayList(clientsList);
            getSender().tell(list, getSelf());
        }
        if (o instanceof Сlient) {
            Сlient client = (Сlient) o;
            client.setActorRef(getContext().actorOf(Props.create(ChatClient.class)));
            client.getActorRef().tell(client, getSelf());
            client.setServerActorRef(getSelf());
            clientsList.add(client);
        }
    }

}
