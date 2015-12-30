package ist.enesuysal.thesis;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Message {

    int messageInt;
    String messageString;
    Boolean messageBool;
    Object messageObject;
    byte[] messageSerilized;

    public Message(byte message) {
        messageSerilized = new byte[message];
    }
    public Message(int message) {
        messageSerilized = CentralSerializer.intToByteArray(message, messageSerilized);
    }

    public Message(String message) {
        messageSerilized = CentralSerializer.stringToByteArray(message, messageSerilized);
    }

    public Message(boolean message) {
        messageSerilized = CentralSerializer.boolToByteArray(message, messageSerilized);
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
