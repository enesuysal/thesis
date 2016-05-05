package ist.enesuysal.thesis;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        messageSerilized = CentralSerializer.serializePrimitive(byte.class, message, messageSerilized);
    }
    public Message(int message) {
        messageSerilized = CentralSerializer.serializePrimitive(int.class, message, messageSerilized);//convertToByteArray(message,messageSerilized);
       
    }
    public Message(Integer message) {
        messageSerilized = CentralSerializer.serializePrimitive(Integer.class, message, messageSerilized);
    }
    public Message(String message) {
        messageSerilized = CentralSerializer.serializePrimitive(String.class, message, messageSerilized);
    }
     public Message(Boolean message) {
        messageSerilized = CentralSerializer.serializePrimitive(Boolean.class, message, messageSerilized);
    }
     public Message(Character message) {
        messageSerilized = CentralSerializer.serializePrimitive(Character.class, message, messageSerilized);
    }
     public Message(Short message) {
        messageSerilized = CentralSerializer.serializePrimitive(Short.class, message, messageSerilized);
    }
     public Message(Float message) {
        messageSerilized = CentralSerializer.serializePrimitive(Float.class, message, messageSerilized);
    }
     public Message(Long message) {
        messageSerilized = CentralSerializer.serializePrimitive(Long.class, message, messageSerilized);
    }
     public Message(Double message) {
        messageSerilized = CentralSerializer.serializePrimitive(Double.class, message, messageSerilized);
    }
     public Message(Byte message) {
        messageSerilized = CentralSerializer.serializePrimitive(Byte.class, message, messageSerilized);
    }
    public Message(boolean message) {
        messageSerilized = CentralSerializer.serializePrimitive(boolean.class, message, messageSerilized);
    }
    
    public Message(char message) {
        messageSerilized = CentralSerializer.serializePrimitive(char.class, message, messageSerilized);
    }
     public Message(long message) {
        messageSerilized = CentralSerializer.serializePrimitive(long.class,message,messageSerilized);
    }
      public Message(short message) {
        messageSerilized = CentralSerializer.serializePrimitive(short.class,message,messageSerilized);
    }
     public Message(float message) {
        messageSerilized = CentralSerializer.serializePrimitive(float.class,message,messageSerilized);
    }
    public Message(double message) {
        messageSerilized = CentralSerializer.serializePrimitive(double.class,message,messageSerilized);
    }
    
    public Message(Object message) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method m = message.getClass().getMethod("Serialize", new Class[] {});
        messageSerilized = (byte[])m.invoke(message, new Object[] {});
    }
   

    public String Seriliaze() {
        byte[] arrayByte = null;
        BASE64Encoder encoder = new BASE64Encoder();
        String temp_inBase64 = encoder.encode(messageSerilized);
        System.out.println(Arrays.toString(messageSerilized));
        return temp_inBase64;
    }

    
}
