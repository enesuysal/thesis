package ist.enesuysal.thesis;

import ist.enesuysal.thesis.Tests.TestSerial;

public class Run {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, Exception {

        // Create Message 
        Message msg = new Message(new TestSerial());
        String msgToSend = msg.Seriliaze();
        
        //Receive Messaged
        Receiver.GetMessage(msgToSend);

    }

}
