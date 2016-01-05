package ist.enesuysal.thesis;

import DOM.ComplianceTest;
import ist.enesuysal.thesis.Tests.Test1;
import ist.enesuysal.thesis.Tests.TestSerial;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Run {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, Exception {
        
        byte test1 = 0x15;
        int test2 = 0;
        String test3 = "Deneme";
        boolean test4 = true;
        char test5 = 'a';
        long test6 = 4666;
        short test7 = 4666;
        float test8 = 4666;
        double test9 = 34.4;
        Byte test1_1 = 0x2;
        Integer test2_1 = 10;
        Boolean test4_1 = true;
        Character test5_1 = 'a';
        Long test6_1 = 12345678910L;
        Short test7_1 = 4666;
        Float test8_1 = 3.6f;
        Double test9_1 = 34.4;
        
        TestSerial tt = new TestSerial();
        Message msg = new Message(test1_1);
        String msgToSend = msg.Seriliaze();
        ComplianceTest complience = new ComplianceTest();
        complience.GetMessage(msgToSend);

        //System.out.println( Arrays.toString(tt.Serialize()));
    }

}
