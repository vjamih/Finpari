package com.mycompany.finparitasktwo;

import java.util.List;
import java.util.Scanner;

public class Session {

    private final Сlient client;
    
    public Session(Сlient client, Server server) {
        this.client = client;
        server.addClient(client);
    }

    public void run() throws InterruptedException {
        while (true) {
            Thread.sleep(100);
            System.out.println(client.getName() + ", введите ключевое слово, чтобы выбрать действие:" + "\n"
                    + "Показать список контактов: contacts" + "\n"
                    + "Отправить сообщение в публичный чат: public chat" + "\n"
                    + "Отправить сообщение в личный чат: private chat" + "\n"
                    + "Закрыть сессию: close"
            );
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if ("close".equals(command)) {
                System.out.println(client.getPublicChat());
                List<Сlient> clientsList = client.getClientsList();
                for (Сlient kl : clientsList){
                    System.out.println("");
                    System.out.println(client.getChatsWith(kl));
                }
                break;
            }
            boolean correctCommand = false;
            while (!correctCommand) {
                correctCommand = true;
                switch (command) {
                    case ("contacts"):
                        List<Сlient> clientsList = client.getClientsList();
                        for (int i = 0; i < clientsList.size(); i++) {
                            System.out.println(i + ". " + clientsList.get(i).getName());
                        }
                        break;
                    case ("private chat"):
                        System.out.println("Укажите номер собеседника в Вашем списке контактов и напишите сообщение:");
                        int numberFromContats = scanner.nextInt();
                        String message = scanner.nextLine();
                        client.sendMessage(client.getClientsList().get(numberFromContats), message);
                        break;
                    case ("public chat"):
                        System.out.println("Напишите сообщение:");
                        String publicMessage = scanner.nextLine();
                        client.sendPublicMessage(publicMessage);
                        break;
                    default:
                        System.out.println("Введите еще раз:");
                        command = scanner.nextLine();
                        correctCommand = false;
                }
            }
            System.out.println("");
        }
    }
    
}
