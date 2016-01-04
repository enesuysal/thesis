package ist.enesuysal.thesis;

import DOM.ComplianceTest;
import ist.enesuysal.thesis.Tests.Test1;
import ist.enesuysal.thesis.Tests.TestSerial;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Run {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, Exception {
        int test = 5;
        boolean test2 = false;
        long test3 = 0;
        String test4 = "Deneme";
        Boolean test5 = true;
        TestSerial tt = new TestSerial();
        Message msg = new Message(test2);
        String msgToSend = msg.Seriliaze();
        ComplianceTest complience = new ComplianceTest();
        complience.GetMessage(msgToSend);

        //System.out.println( Arrays.toString(tt.Serialize()));
    }

}
