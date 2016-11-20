package com.mycompany.finparitasktwo;

import akka.actor.ActorRef;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Сlient implements Serializable {

    private final String name;
    private ActorRef actorRef;

    private ActorRef serverActorRef;

    private Map<Сlient, String> chats = new HashMap<>();
    private String publicChat = "Public Chat" + "\n";

    private List<Сlient> clientsList = new ArrayList<>();

    private boolean getNewClientsList = false;
    
    public Сlient(String name) {
        this.name = name;
    }

    public void sendMessage(Сlient client, String text) {
        Message message = new Message(this, client, text);
        serverActorRef.tell(message, actorRef);
    }

    public void sendPublicMessage(String text) {
        PublicMessage publicMessage = new PublicMessage(this, text);
        serverActorRef.tell(publicMessage, actorRef);
    }

    public String getChatsWith(Сlient client) {
        if (chats.containsKey(client)) {
            return chats.get(client);
        }
        return null;
    }

    public String getPublicChat() {
        return publicChat;
    }

    public String getName() {
        return name;
    }

    public List<Сlient> getClientsList() {
        requestListOfClients();
        for (Сlient client : clientsList) {
            if (!chats.containsKey(client)) {
                chats.put(client, "Chat with " + client.getName() + "\n");
            }
        }
        clientsList.remove(this);
        return clientsList;
    }

    void updateChats(Message message) {
        if (message.getSender() == this) {
            if (this.getChatsWith(message.getAddressee()) == null) {
                chats.put(message.getAddressee(), "Chat with " + message.getAddressee().getName() + "\n" + message.getSender().getName() + ": " + message.getText() + "\n");
            } else {
                chats.put(message.getAddressee(), chats.get(message.getAddressee()) + message.getSender().getName() + ": " + message.getText() + "\n");
            }
        }else{
            if (this.getChatsWith(message.getSender()) == null) {
            chats.put(message.getSender(), "Chat with " + message.getSender().getName() + "\n" + message.getSender().getName() + ": " + message.getText() + "\n");
        } else {
            chats.put(message.getSender(), chats.get(message.getSender()) + message.getSender().getName() + ": " + message.getText() + "\n");
        }
        }

        
    }

    void updatePublicChats(PublicMessage publicMessage) {
        publicChat += publicMessage.getSender().getName() + ": " + publicMessage.getText() + "\n";
    }

    private void requestListOfClients() {
        getNewClientsList = false;
        serverActorRef.tell(clientsList, actorRef);
        while (!getNewClientsList) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Сlient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    ActorRef getActorRef() {
        return actorRef;
    }

    void setActorRef(ActorRef actorRef) {
        this.actorRef = actorRef;
    }

    void setServerActorRef(ActorRef serverActorRef) {
        this.serverActorRef = serverActorRef;
    }

    void setKlientsList(List<Сlient> clientsList) {
        this.getNewClientsList = true;
        this.clientsList = clientsList;
    }

    public ActorRef getServerActorRef() {
        return serverActorRef;
    }
}
