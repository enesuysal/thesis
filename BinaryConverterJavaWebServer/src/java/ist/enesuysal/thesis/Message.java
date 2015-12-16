/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.enesuysal.thesis;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder;
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

    public Message() {

    }

    public Message(int message) {
        this.messageInt = message;
        messageSerilized = CentralSerializer.intToByteArray(messageInt, messageSerilized);
    }

    public Message(String message) {
        this.messageString = message;
        messageSerilized = CentralSerializer.stringToByteArray(message, messageSerilized);
    }

    public Message(Boolean message) {
        this.messageBool = message;
        messageSerilized = CentralSerializer.boolToByteArray(message, messageSerilized);
    }

    public Message(Object message) throws IllegalArgumentException, IllegalAccessException, Exception {
        this.messageObject = message;
        messageSerilized = CentralSerializer.objectToByteArray(message, messageSerilized);
    }

    public String Seriliaze() {
        byte[] arrayByte = null;

        BASE64Encoder encoder = new BASE64Encoder();
        String temp_inBase64 = encoder.encode(messageSerilized);
        return temp_inBase64;
    }

    public byte[] DeSeriliaze(String temp_inBase64) {
        byte[] arrayByte = null;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            arrayByte = decoder.decodeBuffer(temp_inBase64);
        } catch (IOException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayByte;
    }
}
