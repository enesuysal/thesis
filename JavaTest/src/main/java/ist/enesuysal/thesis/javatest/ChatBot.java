/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis.javatest;

/**
 *
 * @author enesuysal
 */
/**
 * ChatBot.java
 * http://programmingforliving.com
 */
 

import java.io.StringReader;
import java.net.URI;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * ChatBot
 * @author Jiji_Sasidharan
 */
public class ChatBot {

    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        final ChatClientEndpoint clientEndPoint = new ChatClientEndpoint(new URI("ws://enes:8080/JavaWebServerWebSocket-1.0-SNAPSHOT/javawsendpoint"));
        clientEndPoint.addMessageHandler(new ChatClientEndpoint.MessageHandler() {
                    public void handleMessage(String message) {
                        System.out.println(message);
                    }
                });
     
            clientEndPoint.sendMessage("AwAAAAAAAAAAAAAABgAAAAZEZW5lbWU=");
            Thread.sleep(30000);
       
    }

  
}