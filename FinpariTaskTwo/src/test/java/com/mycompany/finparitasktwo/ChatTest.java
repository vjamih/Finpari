package com.mycompany.finparitasktwo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChatTest {

    static Server server = new Server();

    static Сlient bohdan = new Сlient("Bohdan");
    static Сlient alex = new Сlient("Alex");
    static Сlient roma = new Сlient("Roma");

    @BeforeClass
    public static void setUpClass() {
        server.addClient(bohdan);
        server.addClient(alex);
        server.addClient(roma);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ChatTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        bohdan.sendPublicMessage("Hi everybody!!");
        alex.sendPublicMessage("Hello! How are you?");
        bohdan.sendPublicMessage("I'm fine!");
        alex.sendPublicMessage("Let's meet in a bar at this weekend.");
        roma.sendPublicMessage("It is a great idea!");

        bohdan.sendMessage(alex, "Hi, I need your help!");
        alex.sendMessage(bohdan, "I'll help you with pleasure!");
        bohdan.sendMessage(alex, "Math is so hard, can you do my homework?");
        alex.sendMessage(bohdan, "OK, but you must promise me to understand all.");
        bohdan.sendMessage(alex, "Yes, I promise you");

        bohdan.sendMessage(roma, "Hi, Roma! What are your plans for the evening?");
        roma.sendMessage(bohdan, "Hi, I'm free.");
        bohdan.sendMessage(roma, "Let's to take a walk with girls tonight.");
        roma.sendMessage(bohdan, "Gladly! At what time?");
        bohdan.sendMessage(roma, "I'll pick you up at eight o'clock.");
        roma.sendMessage(bohdan, "See you soon!");
        
        bohdan.sendMessage(bohdan, "This is my chat.");
    }

    @AfterClass
    public static void tearDownClass() {
        server.dead();
    }

    @Test
    public void bohdanContacts() {
        String expResult = "0. Alex" + "\n"
                + "1. Roma" + "\n";
        String result = "";
        List<Сlient> clientsList = bohdan.getClientsList();
        for (int i = 0; i < clientsList.size(); i++) {
            result += i + ". " + clientsList.get(i).getName() + "\n";
        }
        assertEquals(expResult, result);
    }

    @Test
    public void alexContacts() {
        String expResult = "0. Bohdan" + "\n"
                + "1. Roma" + "\n";
        String result = "";
        List<Сlient> clientsList = alex.getClientsList();
        for (int i = 0; i < clientsList.size(); i++) {
            result += i + ". " + clientsList.get(i).getName() + "\n";
        }
        assertEquals(expResult, result);
    }

    @Test
    public void romaContacts() {
        String expResult = "0. Bohdan" + "\n"
                + "1. Alex" + "\n";
        String result = "";
        List<Сlient> clientsList = roma.getClientsList();
        for (int i = 0; i < clientsList.size(); i++) {
            result += i + ". " + clientsList.get(i).getName() + "\n";
        }
        assertEquals(expResult, result);
    }

    @Test
    public void bohdanPublicChat() {
        String result = bohdan.getPublicChat();
        String expResult = "Public Chat" + "\n"
                + "Bohdan: Hi everybody!!" + "\n"
                + "Alex: Hello! How are you?" + "\n"
                + "Bohdan: I'm fine!" + "\n"
                + "Alex: Let's meet in a bar at this weekend." + "\n"
                + "Roma: It is a great idea!" + "\n";
        assertEquals(expResult, result);
    }

    @Test
    public void alexPublicChat() {
        String result = alex.getPublicChat();
        String expResult = "Public Chat" + "\n"
                + "Bohdan: Hi everybody!!" + "\n"
                + "Alex: Hello! How are you?" + "\n"
                + "Bohdan: I'm fine!" + "\n"
                + "Alex: Let's meet in a bar at this weekend." + "\n"
                + "Roma: It is a great idea!" + "\n";
        assertEquals(expResult, result);
    }

    @Test
    public void romaPublicChat() {
        String result = roma.getPublicChat();
        String expResult = "Public Chat" + "\n"
                + "Bohdan: Hi everybody!!" + "\n"
                + "Alex: Hello! How are you?" + "\n"
                + "Bohdan: I'm fine!" + "\n"
                + "Alex: Let's meet in a bar at this weekend." + "\n"
                + "Roma: It is a great idea!" + "\n";
        assertEquals(expResult, result);
    }

    @Test
    public void bohdanPrivateChatWithAlex() {
        String result = bohdan.getChatsWith(alex);
        String expResult = "Chat with " + "Alex" + "\n"
                + "Bohdan: Hi, I need your help!" + "\n"
                + "Alex: I'll help you with pleasure!" + "\n"
                + "Bohdan: Math is so hard, can you do my homework?" + "\n"
                + "Alex: OK, but you must promise me to understand all." + "\n"
                + "Bohdan: Yes, I promise you" + "\n";
        assertEquals(expResult, result);
    }

    @Test
    public void alexPrivateChatWithBohdan() {
        String result = alex.getChatsWith(bohdan);
        String expResult = "Chat with " + "Bohdan" + "\n"
                + "Bohdan: Hi, I need your help!" + "\n"
                + "Alex: I'll help you with pleasure!" + "\n"
                + "Bohdan: Math is so hard, can you do my homework?" + "\n"
                + "Alex: OK, but you must promise me to understand all." + "\n"
                + "Bohdan: Yes, I promise you" + "\n";
        assertEquals(expResult, result);
    }

    @Test
    public void bohdanPrivateChatWithRoma() {
        String result = bohdan.getChatsWith(roma);
        String expResult = "Chat with " + "Roma" + "\n"
                + "Bohdan: Hi, Roma! What are your plans for the evening?" + "\n"
                + "Roma: Hi, I'm free." + "\n"
                + "Bohdan: Let's to take a walk with girls tonight." + "\n"
                + "Roma: Gladly! At what time?" + "\n"
                + "Bohdan: I'll pick you up at eight o'clock." + "\n"
                + "Roma: See you soon!" + "\n";
        assertEquals(expResult, result);
    }
    
    @Test
    public void romaPrivateChatWithBohdan() {
        String result = roma.getChatsWith(bohdan);
        String expResult = "Chat with " + "Bohdan" + "\n"
                + "Bohdan: Hi, Roma! What are your plans for the evening?" + "\n"
                + "Roma: Hi, I'm free." + "\n"
                + "Bohdan: Let's to take a walk with girls tonight." + "\n"
                + "Roma: Gladly! At what time?" + "\n"
                + "Bohdan: I'll pick you up at eight o'clock." + "\n"
                + "Roma: See you soon!" + "\n";
        assertEquals(expResult, result);
    }
    
    @Test
    public void alexPrivateChatWithRoma() {
        String result = alex.getChatsWith(roma);
        String expResult = "Chat with " + "Roma" + "\n";
        assertEquals(expResult, result);
    }
    
    @Test
    public void romaPrivateChatWithAlex() {
        String result = roma.getChatsWith(alex);
        String expResult = "Chat with " + "Alex" + "\n";
        assertEquals(expResult, result);
    }
    
    @Test
    public void bohdanPrivateChatWithHimself() {
        String result = bohdan.getChatsWith(bohdan);
        System.out.println(result);
        String expResult = "Chat with " + "Bohdan" + "\n" +
                "Bohdan: This is my chat."  + "\n";
        System.out.println(expResult);
        assertEquals(expResult, result);
    }
}
