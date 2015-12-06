using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CSharpWebServer
{
    public class Message {
    int messageInt;
    String messageString;
    Boolean messageBool;
    Object messageObject;
    public byte[] messageSerilized;
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
        messageSerilized = CentralSerializer.objectToByteArray(message, messageSerilized);
       
    }
   public string Seriliaze(){
      string temp_inBase64 = Convert.ToBase64String(messageSerilized);
       return temp_inBase64;
   } 
}
}
