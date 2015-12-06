/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author enesuysal
 */
public class Message {
    int messageInt;
    String messageString;
    Boolean messageBool;
    Object messageObject;
    byte[] messageSerilized;
    public Message(int message){
        this.messageInt = message;
        messageSerilized = CentralSerializer.intToByteArray(messageInt, messageSerilized);
    }
    public Message (String message){
        this.messageString = message;
        messageSerilized = CentralSerializer.stringToByteArray(message, messageSerilized);
    }
     public Message (Boolean message){
        this.messageBool = message;
        messageSerilized =CentralSerializer.boolToByteArray(message, messageSerilized);
    }
      public Message (Object message){
        this.messageObject = message;
        try {
            messageSerilized = CentralSerializer.objectToByteArray(message, messageSerilized);
        } catch (IOException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public String Seriliaze(){
      byte[] arrayByte = null;
     
       BASE64Encoder encoder = new BASE64Encoder();
       String temp_inBase64 =  encoder.encode(messageSerilized);
       return temp_inBase64;
   } 
}
