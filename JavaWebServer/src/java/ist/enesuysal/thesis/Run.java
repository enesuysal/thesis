package ist.enesuysal.thesis;

 
 
import ist.enesuysal.thesis.Tests.TestSerial;
 

public class Run {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, Exception {
        
        TestSerial test = new TestSerial();
        Message msg = new Message(test);
        String msgToSend =  msg.Seriliaze();
//        WebService service  = new WebService();
//        WebServiceSoap port = service .getWebServiceSoap12();
//        System.out.println(port.getResult(msgToSend));
    }

}
