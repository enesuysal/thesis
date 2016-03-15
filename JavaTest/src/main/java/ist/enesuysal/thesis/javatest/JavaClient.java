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
 

import ist.enesuysal.thesis.Message;
import java.io.StringReader;
import java.net.URI;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * ChatBot
 * @author Jiji_Sasidharan
 */
public class JavaClient {

    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        final ChatClientEndpoint clientEndPoint = new ChatClientEndpoint(new URI("ws://javatomcatthesis.azurewebsites.net/JavaWebServerWebSocket-1.0/javawsendpoint"));
        clientEndPoint.addMessageHandler(new ChatClientEndpoint.MessageHandler() {
                    public void handleMessage(String message) {
                        System.out.println(message);
                    }
                });
          String test = "TEst1";
          Message msg = new Message(test);
          byte[] msgToSend =  msg.SeriliazeBinary();
            clientEndPoint.sendMessage(msgToSend);
            Thread.sleep(30000);
       
    }

  
}