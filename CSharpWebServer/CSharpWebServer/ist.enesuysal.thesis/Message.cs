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
        public Message(string message)
        {
            messageSerilized = CentralSerializer.serializePrimitive(typeof(string), message, messageSerilized);
        }
      
        public Message(int message)
        {
             messageSerilized = CentralSerializer.serializePrimitive(typeof(int), message, messageSerilized);//convertToByteArray(message,messageSerilized);

        }
        public Message(bool message)
        {
            messageSerilized = CentralSerializer.serializePrimitive(typeof(bool), message, messageSerilized);
        }

        public Message(char message)
        {
            messageSerilized = CentralSerializer.serializePrimitive(typeof(char), message, messageSerilized);
        }
        public Message(long message)
        {
            messageSerilized = CentralSerializer.serializePrimitive(typeof(long), message, messageSerilized);
        }
        public Message(short message)
        {
            messageSerilized = CentralSerializer.serializePrimitive(typeof(short), message, messageSerilized);
        }
        public Message(float message)
        {
            messageSerilized = CentralSerializer.serializePrimitive(typeof(float), message, messageSerilized);
        }
        public Message(double message)
        {
            messageSerilized = CentralSerializer.serializePrimitive(typeof(double), message, messageSerilized);
        }

        public string Seriliaze()
        {
            string temp_inBase64 = Convert.ToBase64String(messageSerilized);
            return temp_inBase64;
        } 
    }
}
