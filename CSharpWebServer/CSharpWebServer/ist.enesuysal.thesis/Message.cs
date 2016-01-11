using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CSharpWebServer.ist.enesuysal.thesis
{
    public class Message
    {
        int messageInt;
        String messageString;
        Boolean messageBool;
        Object messageObject;
        byte[] messageSerilized = new byte[0];
        public Message(byte message)
        {
            messageSerilized = CentralSerializer.serializePrimitive(typeof(byte), message, messageSerilized);
        }
        public Message(int message)
        {
            // messageSerilized = CentralSerializer.serializePrimitive(int.class, message, messageSerilized);//convertToByteArray(message,messageSerilized);

        }
        public Message(bool message)
        {
            // messageSerilized = CentralSerializer.serializePrimitive(boolean.class, message, messageSerilized);
        }

        public Message(char message)
        {
            // messageSerilized = CentralSerializer.serializePrimitive(char.class, message, messageSerilized);
        }
        public Message(long message)
        {
            //messageSerilized = CentralSerializer.serializePrimitive(long.class,message,messageSerilized);
        }
        public Message(short message)
        {
            //messageSerilized = CentralSerializer.serializePrimitive(short.class,message,messageSerilized);
        }
        public Message(float message)
        {
            //messageSerilized = CentralSerializer.serializePrimitive(float.class,message,messageSerilized);
        }
        public Message(double message)
        {
            //messageSerilized = CentralSerializer.serializePrimitive(double.class,message,messageSerilized);
        }
    }
}
