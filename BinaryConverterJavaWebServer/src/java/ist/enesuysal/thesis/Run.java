package ist.enesuysal.thesis;

import DOM.ComplianceTest;
import ist.enesuysal.thesis.Tests.TestSerial;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Run {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, Exception {
        int test = 5;
        boolean test2 = false;
        long test3 = 0;
        // Create Message 
        Message msg = new Message(test3);
        String msgToSend = msg.Seriliaze();
        
        ComplianceTest complience = new ComplianceTest();
        complience.GetMessage(msgToSend);
    }

}
