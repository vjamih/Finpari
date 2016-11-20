package com.mycompany.finparitasktwo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private final ActorSystem system;
    private final ActorRef serverActorRef;

    public Server() {
        system = ActorSystem.create("Chat");
        serverActorRef = system.actorOf(Props.create(ChatServer.class));
    }

    public void addClient(Сlient client) {
        serverActorRef.tell(client, ActorRef.noSender());
    }

    public void dead() {
        system.shutdown();
    }

}
