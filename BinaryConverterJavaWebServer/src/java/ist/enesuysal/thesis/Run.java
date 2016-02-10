package ist.enesuysal.thesis;

import DOM.ComplianceTest;
import ist.enesuysal.thesis.Tests.Test1;
import ist.enesuysal.thesis.Tests.TestSerial;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Run {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, Exception {
        
        TestSerial tt = new TestSerial();
        Message msg = new Message(tt);
        String msgToSend =  msg.Seriliaze();
        WebService service  = new WebService();
        WebServiceSoap port = service .getWebServiceSoap12();
        System.out.println(port.getResult(msgToSend));
    }

}
