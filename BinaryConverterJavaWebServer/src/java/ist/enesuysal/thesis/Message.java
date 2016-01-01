package ist.enesuysal.thesis;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Message {

    int messageInt;
    String messageString;
    Boolean messageBool;
    Object messageObject;
    byte[] messageSerilized= new byte[0];

    public Message(byte message) {
        messageSerilized = new byte[message];
    }
    public Message(int message) {
        messageSerilized = CentralSerializer.serializePrimitive(int.class, message, messageSerilized);//convertToByteArray(message,messageSerilized);
       
    }

    public Message(String message) {
        messageSerilized = CentralSerializer.convertToByteArray(message, messageSerilized);
    }

    public Message(boolean message) {
        messageSerilized = CentralSerializer.convertToByteArray(message, messageSerilized);
    }
    
    public Message(char message) {
        messageSerilized = CentralSerializer.convertToByteArray(message,messageSerilized);
    }
     public Message(long message) {
        messageSerilized = CentralSerializer.convertToByteArray(message,messageSerilized);
    }
      public Message(short message) {
        messageSerilized = CentralSerializer.convertToByteArray(message,messageSerilized);
    }
     public Message(float message) {
        messageSerilized = CentralSerializer.convertToByteArray(message,messageSerilized);
    }
     public Message(double message) {
        messageSerilized = CentralSerializer.convertToByteArray(message,messageSerilized);
    }
    public Message(Object message) throws IllegalArgumentException, IllegalAccessException, Exception {
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
