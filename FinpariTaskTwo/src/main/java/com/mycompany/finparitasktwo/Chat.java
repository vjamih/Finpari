package com.mycompany.finparitasktwo;

public class Chat {

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();

        Сlient bohdan = new Сlient("Bohdan");
        Сlient alex = new Сlient("Alex");
        Сlient roma = new Сlient("Roma");

        Session sessionBohdan = new Session(bohdan, server);
        Session sessionAlex = new Session(alex, server);
        Session sessionRoma = new Session(roma, server);

        sessionBohdan.run();
        sessionAlex.run();
        sessionRoma.run();

        server.dead();
    }

}
