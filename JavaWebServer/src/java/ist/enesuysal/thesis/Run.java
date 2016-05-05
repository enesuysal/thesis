package ist.enesuysal.thesis;


import ist.enesuysal.thesis.Tests.TestSerial;
import ist.enesuysal.thesis.Tests.Weather;
import java.io.FileOutputStream;
import java.util.LinkedList;
import org.tempuri.WebService;
import org.tempuri.WebServiceSoap;
 

public class Run {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, Exception {       
        long start =System.currentTimeMillis();      
            Weather w = new Weather();
            w.country="Portugal";
            w.city="Lisbon";
          Message msg = new Message(w);
          String msgToSend =  msg.Seriliaze();
           WebService service  = new WebService();
           WebServiceSoap port = service .getWebServiceSoap12();
           System.out.println(port.getResult(msgToSend));
        long end = System.currentTimeMillis();
        double time = (double)(end - start);
        System.out.println(time);   
    }
    
    public static byte[] push(byte[] array, byte[] push) {
        byte[] longer = new byte[array.length + push.length];
        System.arraycopy(array, 0, longer, 0, array.length);
        System.arraycopy(push, 0, longer, array.length, push.length);

        return longer;
    }

}
