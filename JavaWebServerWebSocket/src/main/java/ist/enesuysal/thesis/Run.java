package ist.enesuysal.thesis;

 
 
import ist.enesuysal.thesis.Tests.TestSerial;
//import org.tempuri.WebService;
//import org.tempuri.WebServiceSoap;
 

public class Run {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, Exception {
        
        String test = "Deneme";
        Message msg = new Message(test);
        String msgToSend =  msg.Seriliaze();
//        WebService service  = new WebService();
//        WebServiceSoap port = service .getWebServiceSoap12();
//        System.out.println(port.getResult(msgToSend));
    }

}
