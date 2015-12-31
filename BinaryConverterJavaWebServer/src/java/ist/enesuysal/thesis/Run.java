package ist.enesuysal.thesis;

import DOM.ComplianceTest;
import ist.enesuysal.thesis.Tests.TestSerial;

public class Run {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, Exception {
        int test = 5;
        // Create Message 
        Message msg = new Message(test);
        String msgToSend = msg.Seriliaze();
        
        //Receive Message
        ComplianceTest.GetMessage(msgToSend);

    }

}
